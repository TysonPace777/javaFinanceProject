import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList; //data structure from java collection
import java.util.Scanner;

//Finance class with main method
// runs a program to manage finances and save or read past data from a file
public class Finance {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<String> fileHistory = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        // Declare variables
        double balance = 0;
        boolean isRunning = true;
        int choice;

        System.out.println("Welcome to the Finance Manager!");

        // Main loop
        while (isRunning) {
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Save Changes to File");
            System.out.println("5. Read from File");
            System.out.println("6. Exit");
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
                case 4 -> writeToFile(balance);
                case 5 -> balance = readFromFile();
                case 6 -> isRunning = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // Method to write to a file
    static void writeToFile(double balance) {
        System.out.println("Enter a file name: ");
        String fileName = scanner.next();

        // Check if the file name ends with .txt
        if (!fileName.endsWith(".txt")) {
            fileName += ".txt";
        }

        System.out.println("-----------------------------------");
        try (FileWriter myWriter = new FileWriter(fileName)) {
            for (String i : fileHistory) {
                myWriter.write(i + "\n");
            }
            myWriter.write("\nTotal: $" + String.format("%.2f", balance));
            System.out.println("Successfully wrote to the file.");
            System.out.println("-----------------------------------");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    //Method to read from a file with old saved data
    static double readFromFile() {
        System.out.println("Enter a file name: ");
        String fileName = scanner.next();

        // Check if the file name ends with .txt
        //conditional
        if (!fileName.endsWith(".txt")) {
            fileName += ".txt";
        }

        System.out.println("-----------------------------------");

        double balance = 0;
        fileHistory.clear();

        // read balance and data from file and if exists then return it
        try (Scanner myReader = new Scanner(new File(fileName))) {

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().trim();
                if (data.startsWith("Total:")) {
                    String amount = data.replace("Total: $", "").trim();
                    balance = Double.parseDouble(amount);
                } else if (!data.isBlank()) {
                    fileHistory.add(data);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

        return balance;
    }

    // Method to deposit money
    // Also includes expression for the amount
    static double deposit(double balance) {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        fileHistory.add("Deposited: $" + String.format("%.2f", amount) + " ---------- " + "Balance: $" + String.format("%.2f", balance + amount));
        return balance + amount;
    };

    // Method to withdraw money
    // Also includes expression for the amount
    static double withdraw(double balance) {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        fileHistory.add("Withdrew: $" + String.format("%.2f", amount) + " ---------- " + "Balance: $" + String.format("%.2f",(balance - amount)));
        return balance - amount;
    }

    // Method to check balance
    static void checkBalance(double balance) {
        System.out.printf("Balance: $%.2f\n", balance);
        System.out.println("-----------------------------------");
    }

}
