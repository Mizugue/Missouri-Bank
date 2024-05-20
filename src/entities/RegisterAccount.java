package entities;

import exceptions.PasswordError;
import java.util.Scanner;

public class RegisterAccount {
    private String name;
    private String password;
    private Integer id;
    private FinanceManager financeManager;

    public RegisterAccount() {}

    public RegisterAccount(String name, String password, Integer id, FinanceManager financeManager) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.financeManager = financeManager;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String oldPassword, String newPassword) {
        if (!oldPassword.equals(password)) {
            throw new PasswordError("Old password does not match.");
        }
        password = newPassword;
        System.out.printf("Password updated successfully from %s to %s%n", oldPassword, newPassword);
    }

    public FinanceManager getFinanceManager() {
        return financeManager;
    }

    public boolean accessAccount(String password) {
        if (this.password.equals(password)) {
            System.out.println("---------------------");
            System.out.println("Welcome to your account, " + getName() + "!");
            System.out.println("---------------------");
            return true;
        } else {
            System.out.println("Incorrect password.");
            return false;
        }
    }

    public void menuAccountManager(Scanner sc) {
        while (true) {
            System.out.println("1-Change your name");
            System.out.println("2-Change your password");
            System.out.println("3-Back");
            System.out.println("4-Quit");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.print("New name: ");
                    String newName = sc.nextLine();
                    setName(newName);
                    System.out.println("Name updated successfully.");
                    break;
                case 2:
                    System.out.print("Old password: ");
                    String oldPassword = sc.nextLine();
                    System.out.print("New password: ");
                    String newPassword = sc.nextLine();
                    try {
                        setPassword(oldPassword, newPassword);
                    } catch (PasswordError e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    return;
                case 4:
                    System.out.println("Exiting. Bye-Bye.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    @Override
    public String toString() {
        return "Name: " + name +
               ", ID: " + id +
               ", Balance: " + financeManager.getBalance();
    }
}
