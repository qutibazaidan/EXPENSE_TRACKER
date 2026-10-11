import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ExpensesStorage storage =new ExpensesStorage("expenses.json");
        ExpenseOperations operations = new ExpenseOperations();
        operations.loadFrom(storage.loadExpenses());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Expense Tracker. Type 'help' for commands, 'exit' to quit.");

        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) {
                break;
            }

            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            if (line.equals("exit")) {
                break;
            }

            handleCommand(line.split("\\s+"), operations,storage);
        }

        scanner.close();
    }

    private static void handleCommand(String[] tokens, ExpenseOperations operations, ExpensesStorage storage) {
        String command = tokens[0];

        switch (command) {
            case "add" -> handleAdd(tokens, operations,storage);
            case "list" -> handleList(operations);
            case "delete" -> handleDelete(tokens, operations, storage);
            case "update" -> handleUpdate(tokens, operations, storage);
            case "summary" -> handleSummary(tokens, operations);
            case "help" -> printHelp();
            default -> System.out.println("Unknown command: " + command + ". Type 'help'.");
        }
    }

    private static void handleAdd(String[] tokens, ExpenseOperations operations, ExpensesStorage storage) {
        String description = getOption(tokens, "--description");
        String amountText = getOption(tokens, "--amount");

        if (description == null || description.isBlank() || amountText == null) {
            System.out.println("Usage: add --description <text> --amount <number>");
            return;
        }

        BigDecimal amount;
        try {
            amount = new BigDecimal(amountText);
        }
        catch (NumberFormatException e) {
            System.out.println("Amount must be a number, got" + amountText);
            return;
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        operations.addExpense(description, amount);
        storage.saveExpenses(operations.listExpenses());
        System.out.println("Expense added.");
    }

    private static void handleList(ExpenseOperations operations) {
        if (operations.listExpenses().isEmpty()) {
            System.out.println("No expenses yet.");
            return;
        }

        System.out.printf("%-4s %-12s %-20s %s%n", "ID", "Date", "Description", "Amount");
        for (Expense e : operations.listExpenses()) {
            System.out.printf(Locale.ROOT,"%-4d %-12s %-20s %s%n",
                    e.getId(), e.getDate(), e.getDescription(), e.getAmount());
        }
    }

    private static void handleDelete(String[] tokens, ExpenseOperations operations, ExpensesStorage storage) {
        String idText = getOption(tokens, "--id");
        if (idText == null) {
            System.out.println("Usage: delete --id <number>");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            System.out.println("Id must be a whole number, got: " + idText);
            return;
        }

        if (operations.deleteExpense(id)) {
            storage.saveExpenses(operations.listExpenses());
            System.out.println("Expense deleted.");
        } else {
            System.out.println("No expense with id " + id + ".");
        }
    }

    private static void handleUpdate(String[] tokens, ExpenseOperations operations, ExpensesStorage storage) {
        String idText = getOption(tokens, "--id");
        String description = getOption(tokens, "--description");
        String amountText = getOption(tokens, "--amount");

        if (idText == null || description == null || description.isBlank() || amountText == null) {
            System.out.println("Usage: update --id <number> --description <text> --amount <number>");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            System.out.println("Id must be a whole number, got: " + idText);
            return;
        }

        BigDecimal amount;
        try {
            amount = new BigDecimal(amountText);
        } catch (NumberFormatException e) {
            System.out.println("Amount must be a number, got: " + amountText);
            return;
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        if (operations.updateExpense(id, description, amount)) {
            storage.saveExpenses(operations.listExpenses());
            System.out.println("Expense updated.");
        } else {
            System.out.println("No expense with id " + id + ".");
        }
    }

    private static void handleSummary(String[] tokens, ExpenseOperations operations) {

        String monthText = getOption(tokens, "--month");

        if (monthText == null) {
            BigDecimal totalSpend = operations.getTotal();

            System.out.println("Total Spend : " + totalSpend);
            return;
        }

        int month;
        try {
            month = Integer.parseInt(monthText);

        }

        catch (NumberFormatException e) {
            System.out.println("Month must be a whole number from 1 to 12 , got: " + monthText);
            return;
        }

        if(month < 1 || month > 12) {
            System.out.println("Month must be a whole number, got: " + monthText);
            return;
        }

        BigDecimal monthly = operations.getTotalByMonth(month,LocalDate.now().getYear());
        System.out.println("Total for : "+monthText +"/"+LocalDate.now().getYear() +" "+ monthly);
    }

    private static void printHelp() {
        System.out.println("Commands:");
        System.out.println("  add --description <text> --amount <number>");
        System.out.println("  list");
        System.out.println("  delete --id <number>");
        System.out.println("  update --id <number>  --description <text> --amount <number>");
        System.out.println("  help");
        System.out.println("  exit");
    }

    // Returns the text after a flag, up to the next "--flag". Null if the flag is absent.
     static String getOption(String[] tokens, String flag) {
        StringBuilder value = new StringBuilder();
        boolean found = false;

        for (String token : tokens) {
            if (token.equals(flag)) {
                found = true;
                continue;
            }
            if (found) {
                if (token.startsWith("--")) {
                    break;
                }
                if (value.length() > 0) {
                    value.append(" ");
                }
                value.append(token);
            }
        }

        return found ? value.toString().trim() : null;
    }


}