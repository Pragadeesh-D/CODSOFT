package task3_atmInterface;

import java.util.Scanner;
class UserBankAccount{
    private double balance = 0.0;

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }
}

public class ATMInterface {
    static Scanner sc = new Scanner(System.in);
    static double amount= 0.0;
    public static void withdraw(UserBankAccount account){
        while (true) {
            System.out.println("Enter amount to withdraw: ");
            if (sc.hasNextDouble()) {
                amount = sc.nextDouble();
                if (amount < 1) {
                    System.out.println("Amount must be greater than 0! Please enter again.");
                    continue;
                }
                if (account.getBalance() >= amount) {
                    account.setBalance(account.getBalance() - amount);
                    System.out.printf("You have withdrawn $%.2f\n ", amount);
                    System.out.printf("Your current balance is $%.2f \n", account.getBalance());
                    break;
                }
                else {
                    System.out.println("Insufficient balance! Please try again.");
                    System.out.printf("Your current balance is $%.2f\n ", account.getBalance());
                    break;
                }
            }
            else {
                System.out.println("Invalid input! Please enter a valid amount: ");
                sc.next();
            }
        }
        userChoices();
    }

    public static void deposit(UserBankAccount account){
        while (true) {
            System.out.println("Enter amount to deposit: ");
            if (sc.hasNextDouble()) {
                amount = sc.nextDouble();
                if (amount > 0) {
                    account.setBalance(account.getBalance() + amount);
                    System.out.printf("You have deposited $%.2f\n ", amount);
                    System.out.printf("Your current balance is $%.2f \n", account.getBalance());
                    break;
                }
                else {
                    System.out.println("Amount must be greater than 0! Please enter again.");
                    continue;
                }
            }
            else {
                System.out.println("Invalid input! Please enter a valid amount: ");
                sc.next();
            }
        }
        userChoices();
    }

    public static void checkBalance(UserBankAccount account){
        System.out.printf("Your current balance is %.2f \n", account.getBalance());
        userChoices();
    }

    public static void userChoices(){
        System.out.println("\nPlease choose an option:\n" +
                "1. Withdraw\n" +
                "2. Deposit\n" +
                "3. Check balance\n" +
                "4. Exit\n" +
                "Enter your choice: ");
    }

    public static void main(String[] args){
        UserBankAccount account = new UserBankAccount();
        System.out.println("Hey User! Welcome to your ATM interface.\nYou can withdraw, deposit, and check your balance here.");
        userChoices();
        while (true){
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        withdraw(account);
                        break;
                    case 2:
                        deposit(account);
                        break;
                    case 3:
                        checkBalance(account);
                        break;
                    case 4:
                        System.out.println("Thank you for using our ATM service! Have a great day!");
                        return;
                    default:
                        System.out.println("Invalid ihoice! Please enter a valid option: ");
                }
            }
            else {
                System.out.println("Invalid input! Please enter a valid integer choice: ");
                sc.next();
            }
        }
    }
}
