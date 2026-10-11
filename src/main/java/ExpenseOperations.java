import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpenseOperations {

    private List<Expense> expenses = new ArrayList<>();

    private int nextId = 1;

    public void addExpense(String description, BigDecimal amount) {
        Expense expense = new Expense(nextId, description, amount, LocalDate.now());
        expenses.add(expense);
        nextId++;
    }

    public List<Expense> listExpenses() {
        return Collections.unmodifiableList(expenses);
    }

    public boolean deleteExpense(int id)
    {
        return expenses.removeIf(expense -> expense.getId() == id);
    }

    public boolean updateExpense(int id, String description, BigDecimal amount) {

        for (int i = 0; i < expenses.size(); i++) {
            Expense existing = expenses.get(i);
            if (existing.getId() == id) {
                Expense updated = new Expense(existing.getId(), description, amount, existing.getDate());
                expenses.set(i, updated);
                return true;
            }
        }
        return false;
    }

    public BigDecimal getTotal() {
       return expenses.stream().map(Expense::getAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    public BigDecimal getTotalByMonth(int month,int year) {
        return expenses.stream().filter(e -> e.getDate().getMonthValue()==month && e.getDate().getYear()==year).map(Expense::getAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    public void loadFrom(List<Expense> loadedExpenses) {
        expenses.clear();
        expenses.addAll(loadedExpenses);

        nextId= expenses.stream().mapToInt(Expense::getId).max().orElse(0)+1;

    }
    }