Expense-Tracker
Hi. this is a CLI app to track Exepenses exercise provided by [rodmap.sh](https://roadmap.sh/projects/expense-tracker);

Features
add an expense with a description and amount.
update an expense.
delete an expense.
view all expenses.
Users can view a summary of expenses for a specific month (of current year).
installation
Clone this repository
git clone https://github.com/qutibazaidan/EXPENSE_TRACKER.git
cd EXPENSE_TRACKER/src
Execute

Usage
# Usage
add --description "Lunch" --amount 20

 add --description "Dinner" --amount 10
 
 list
# Expenses-Tracker
# id    Date       Description          amount
# 1     2023-05-26 buy grocery          $200
# 4     2023-10-29 Gas refill           $40


 delete --id 1
# Expense deleted successfully

 update --id 1 --description "Buy a grocery" --amount 250
# Expesense updated successfully

 update --id 1 --description "Buy a grocery"
# Expesense updated successfully

 update --id 1 --amount 250
# Expesense updated successfully
