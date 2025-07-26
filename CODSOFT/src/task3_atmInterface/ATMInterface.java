package task3_atmInterface;

import java.util.Scanner;

// This class manages the user's bank balance
class UserBankAccount{
    private double balance = 1000;

    // Returns the current balance
    public double getBalance(){
        return balance;
    }

    // Updates the balance to a new value
    public void setBalance(double balance){
        this.balance = balance;
    }
}

public class ATMInterface {
    static Scanner sc = new Scanner(System.in);
    static double amount= 0.0;

    // Handles withdrawing money from the account
    public static void withdraw(UserBankAccount account){
        System.out.println("\nEnter amount to withdraw: ");
        while (true) {
            if (sc.hasNextDouble()) {
                amount = sc.nextDouble();
                if (amount < 1) {
                    System.out.println("Amount must be greater than 0! Please enter again:");
                    continue;
                }
                if (account.getBalance() >= amount) {
                    account.setBalance(account.getBalance() - amount);
                    System.out.printf("\nYou have withdrawn $%.2f\n", amount);
                    System.out.printf("Your current balance is $%.2f \n", account.getBalance());
                }
                else {
                    System.out.println("\nInsufficient balance! Please try again.");
                    System.out.printf("Your current balance is $%.2f\n ", account.getBalance());
                }
                break;
            }
            else {
                System.out.println("Invalid input! Please enter a valid amount: ");
                sc.next();
            }
        }
        userChoices();
    }

    // Handles depositing money into the account
    public static void deposit(UserBankAccount account){
        System.out.println("\nEnter amount to deposit: ");
        while (true) {
            if (sc.hasNextDouble()) {
                amount = sc.nextDouble();
                if (amount > 0) {
                    account.setBalance(account.getBalance() + amount);
                    System.out.printf("\nYou have deposited $%.2f\n", amount);
                    System.out.printf("Your current balance is $%.2f \n", account.getBalance());
                    break;
                }
                else {
                    System.out.println("Amount must be greater than 0! Please enter again:");
                }
            }
            else {
                System.out.println("Invalid input! Please enter a valid amount: ");
                sc.next();
            }
        }
        userChoices();
    }

    // Displays the current balance in the user's account
    public static void checkBalance(UserBankAccount account){
        System.out.printf("\nYour current balance is %.2f \n", account.getBalance());
        userChoices();
    }

    // Shows the list of available actions to the user
    public static void userChoices(){
        System.out.println("""

                Please choose an option:
                1. Withdraw
                2. Deposit
                3. Check balance
                4. Exit
                Enter your choice:\s""");
    }

    // Main method: Starts the ATM simulation and handles user interaction
    public static void main(String[] args){
        UserBankAccount account = new UserBankAccount();
        System.out.println("----- ATM Interface -----");
        System.out.println("\nHey User! Welcome to your ATM interface.\nYou can withdraw, deposit, and check your balance here.");
        userChoices();
        while (true){
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> withdraw(account);
                    case 2 -> deposit(account);
                    case 3 -> checkBalance(account);
                    case 4 -> {
                        System.out.println("Thank you for using our ATM service! Have a great day!");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Please enter a valid option: ");
                }
            }
            else {
                System.out.println("Invalid input! Please enter a valid integer choice: ");
                sc.next();
            }
        }
    }
}
