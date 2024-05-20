package entities;

import Program.Menu;
import exceptions.InsufficientBalance;
import java.util.Scanner;

public class FinanceManager {
    private Double balance;

    public FinanceManager() {
        this.balance = 0.0;
    }

    public FinanceManager(Double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) {
        if (balance < amount) {
            throw new InsufficientBalance(String.format("Your balance of $%.2f is insufficient to withdraw $%.2f", balance, amount));
        } else {
            balance -= amount;
            System.out.printf("Withdrawal successful. Your new balance = $%.2f%n", balance);
        }
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.printf("Deposit successful. Your new balance = $%.2f%n", balance);
    }

    public Double getBalance() {
        return balance;
    }

    public void financeMenu(Scanner scanner) {
        Menu mainMenu = new Menu();
        while (true) {
            System.out.printf("Your balance = $%.2f%n", getBalance());
            System.out.println("1-Withdraw");
            System.out.println("2-Deposit");
            System.out.println("3-Back");
            System.out.println("4-Quit");

            int option = getValidIntegerInput(scanner);

            switch (option) {
                case 1:
                    System.out.println("How much do you want to withdraw? ");
                    double withdrawAmount = getValidDoubleInput(scanner);
                    withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.println("How much do you want to deposit? ");
                    double depositAmount = getValidDoubleInput(scanner);
                    deposit(depositAmount);
                    break;
                case 3:
                    return;
                case 4:
                    System.out.println("Exiting. Bye-Bye.");
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private int getValidIntegerInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private double getValidDoubleInput(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
