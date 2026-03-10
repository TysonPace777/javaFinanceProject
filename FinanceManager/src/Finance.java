import java.util.Scanner;

public class Finance {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        // Declare variables
        double balance = 0;
        boolean isRunning = true;
        int choice;

        System.out.println("Welcome to the Finance Manager!");

        while (isRunning) {
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            // Check if the input is an integer
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            }
            else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }
            System.out.println("-----------------------------------");

            switch (choice) {
                case 1 -> balance = deposit(balance);
                case 2 -> balance = withdraw(balance);
                case 3 -> checkBalance(balance);
                case 4 -> isRunning = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // Method to deposit money
    static double deposit(double balance) {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        return balance + amount;
    };

    // Method to withdraw money
    static double withdraw(double balance) {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        return balance - amount;
    }

    // Method to check balance
    static void checkBalance(double balance) {
        System.out.printf("Balance: $%.2f\n", balance);
        System.out.println("-----------------------------------");
    }

}
