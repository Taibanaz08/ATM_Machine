import java.util.Scanner;

public class ATMMachine {
    private double balance;
    private int pin;
    private boolean isPinSet = false;

    public ATMMachine() {
        this.balance = 0;
    }

    public void checkBalance() {
        System.out.println("Your balance is: $" + balance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("You've successfully deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("You've successfully withdrawn: $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public void setPin(int newPin) {
        this.pin = newPin;
        this.isPinSet = true;
        System.out.println("PIN set successfully.");
    }

    public boolean verifyPin(int inputPin) {
        return inputPin == pin;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATMMachine atm = new ATMMachine();

        // Set PIN if not already set
        if (!atm.isPinSet) {
            System.out.print("Set your PIN: ");
            int newPin = scanner.nextInt();
            atm.setPin(newPin);
        }

        int attempts = 0;
        final int maxAttempts = 3;

        // PIN verification
        while (attempts < maxAttempts) {
            System.out.print("Enter your PIN: ");
            int inputPin = scanner.nextInt();
            if (atm.verifyPin(inputPin)) {
                break;
            } else {
                attempts++;
                System.out.println("Incorrect PIN. Try again.");
                if (attempts == maxAttempts) {
                    System.out.println("Maximum attempts reached. Exiting.");
                    scanner.close();
                    return;
                }
            }
        }

        int choice;
        do {
            System.out.println("\nEnter your choice:");
            System.out.println("1. Check A/C Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}

