# Personal Expense Tracker

A simple, terminal-based Java application to record and track daily expenses.  
It saves your expenses to a CSV file so you can open them in Excel or Google Sheets.

---

## Features
- Add expenses with date, category, amount, and description.
- View all recorded expenses in a clean table format.
- Calculate total spending for:
  - Today
  - This Week
  - This Month
- Data automatically saved in `expenses.csv` for persistence.

---

## Requirements
- Java 8 or higher
- Terminal/Command Prompt access

---

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/Personal_Expense_Tracker.git
   cd Personal_Expense_Tracker

2. Compile the program:
   ```bash
   javac Main.java

3. Create a runnable JAR:
   ```bash
   jar cfe ExpenseTracker.jar Main *.class

4.  Run the program:
    ```bash
    java -jar ExpenseTracker.jar

---

##  Usage

**Add Expense**
Enter date (or press Enter for today), category, amount, and description.

**View All Expenses**
Displays all saved expenses in table format.

**View Totals**
Choose between today, this week, or this month to see spending totals.

---
##  Example
=== Personal Expense Tracker ===
1. Add Expense
2. View All Expenses
3. View Totals
4. Exit
   
Choose option: 1

Enter date (yyyy-mm-dd) or press Enter for today:

Enter category: Food

Enter amount: $12.50

Enter description: Lunch at Subway

Expense added successfully!

##  File Storage

Expenses are saved in expenses.csv:

2025-08-15,Food,12.5,Lunch at Subway

2025-08-15,Transport,4.75,Bus fare

##  License
This project is licensed under the MIT License - see the LICENSE file for details.
