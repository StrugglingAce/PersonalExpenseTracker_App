import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Expense {
    private LocalDate date;
    private String category;
    private double amount;
    private String description;

    public Expense(LocalDate date, String category, double amount, String description) {
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    public LocalDate getDate() { return date; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return String.format("%-12s %-15s $%-10.2f %-20s",
                date, category, amount, description);
    }

    public String toCSV() {
        return date + "," + category + "," + amount + "," + description;
    }

    public static Expense fromCSV(String line) {
        String[] parts = line.split(",");
        LocalDate date = LocalDate.parse(parts[0]);
        String category = parts[1];
        double amount = Double.parseDouble(parts[2]);
        String description = parts[3];
        return new Expense(date, category, amount, description);
    }
}

class ExpenseTracker {
    private List<Expense> expenses = new ArrayList<>();
    private final String fileName = "expenses.csv";

    public ExpenseTracker() {
        loadFromFile();
    }

    public void addExpense(Scanner scanner) {
        System.out.print("Enter date (yyyy-mm-dd) or press Enter for today: ");
        String dateInput = scanner.nextLine().trim();
        LocalDate date = dateInput.isEmpty() ? LocalDate.now() : LocalDate.parse(dateInput);

        System.out.print("Enter category: ");
        String category = scanner.nextLine().trim();

        System.out.print("Enter amount: $");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Enter description: ");
        String description = scanner.nextLine().trim();

        Expense expense = new Expense(date, category, amount, description);
        expenses.add(expense);
        saveToFile();
        System.out.println("Expense added successfully!");
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        System.out.printf("%-12s %-15s %-10s %-20s%n", "Date", "Category", "Amount", "Description");
        System.out.println("--------------------------------------------------------------");
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    public void viewTotals(Scanner scanner) {
        System.out.println("1. Today");
        System.out.println("2. This Week");
        System.out.println("3. This Month");
        System.out.print("Choose option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        double total = 0;
        LocalDate now = LocalDate.now();

        for (Expense e : expenses) {
            if (choice == 1 && e.getDate().equals(now)) {
                total += e.getAmount();
            } else if (choice == 2 && e.getDate().isAfter(now.minusDays(7))) {
                total += e.getAmount();
            } else if (choice == 3 && e.getDate().getMonth() == now.getMonth()
                    && e.getDate().getYear() == now.getYear()) {
                total += e.getAmount();
            }
        }
        System.out.printf("Total: $%.2f%n", total);
    }

    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Expense e : expenses) {
                pw.println(e.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        try {
            if (Files.exists(Paths.get(fileName))) {
                List<String> lines = Files.readAllLines(Paths.get(fileName));
                for (String line : lines) {
                    expenses.add(Expense.fromCSV(line));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Personal Expense Tracker ===");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. View Totals");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    tracker.addExpense(scanner);
                    break;
                case "2":
                    tracker.viewExpenses();
                    break;
                case "3":
                    tracker.viewTotals(scanner);
                    break;
                case "4":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
