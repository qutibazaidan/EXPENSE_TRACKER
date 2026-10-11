# Expense Tracker

A simple Java command-line expense tracker for managing personal spending. The application lets you add, view, update, delete, and summarize expenses, and it stores data in a local JSON file so your records persist between runs.

## Features

- Add new expenses with a description and amount
- List all recorded expenses
- Update an existing expense by ID
- Delete expenses by ID
- View total spending
- View monthly totals for a specific month
- Persist data automatically to `expenses.json`

## Tech Stack

- Java 21
- Maven
- Jackson (JSON serialization/deserialization)

## Project Structure

```text
EXPENSE_TRACKER/
├── src/
│   └── main/
│       └── java/
│           ├── Expense.java
│           ├── ExpenseOperations.java
│           ├── ExpensesStorage.java
│           └── Main.java
├── expenses.json
├── pom.xml
├── .gitignore
├── README.md
└── .idea/
```

## Requirements

- JDK 21 or newer
- Apache Maven

## Setup

1. Clone the repository
2. Navigate to the project folder
3. Compile the project:

```bash
mvn clean compile
```

## Run the Application

```bash
java -cp target/classes Main
```

## Available Commands

```text
add --description "Groceries" --amount 45.99
list
delete --id 1
update --id 1 --description "Rent" --amount 900
summary
summary --month 10
help
exit
```

### Command Details

- `add`: Adds a new expense
- `list`: Shows all expenses in a table
- `delete`: Removes an expense using its ID
- `update`: Modifies an existing expense
- `summary`: Shows total spending for all records or for a selected month
- `help`: Displays available commands
- `exit`: Exits the app

## Data Storage

The app stores expenses in a file named `expenses.json` in the project root. Each entry contains:

- `id`
- `description`
- `amount`
- `date`

## Example

```text
Expense Tracker. Type 'help' for commands, 'exit' to quit.
> add --description "Coffee" --amount 5.50
Expense added.
> list
ID   Date        Description          Amount
1    2026-10-11  Coffee               5.50
> summary
Total Spend : 5.50
>
```

## Notes

This project is a small learning/demo application focused on Java fundamentals, object-oriented design, file persistence, and CLI interaction.

## License

This project does not currently include a license file.
