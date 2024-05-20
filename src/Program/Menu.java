package Program;

import entities.FinanceManager;
import entities.RegisterAccount;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static Scanner sc = new Scanner(System.in);
    private List<RegisterAccount> registerAccountList = new ArrayList<>();

    public Menu() {}

    public void contextOut() {
        boolean life = true;
        while (life) {
            try {
                System.out.println("------------------------------------------");
                System.out.println("----- Welcome to The Missouri Bank! -----");
                System.out.println("------------------------------------------");
                System.out.println("MENU");
                System.out.println("1-Register");
                System.out.println("2-Login");
                System.out.println("3-Quit");

                int op = getValidIntegerInput();
                switch (op) {
                    case 1:
                        registerAccount();
                        break;
                    case 2:
                        login();
                        break;
                    case 3:
                        System.out.println("Bye-Bye");
                        life = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input error: " + e.getMessage());
                sc.next();
            }
        }
    }

    private void registerAccount() {
        System.out.print("Id: ");
        Integer id = getValidIntegerInput();
        for (RegisterAccount registerAccount : registerAccountList){
            if (registerAccount.getId().equals(id)){
                System.out.println("Already exist Account with this id");
                System.out.println("Id: ");
                Integer id_refactored = getValidIntegerInput();
            }
        }
        System.out.print("Name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.next();
        System.out.print("First deposit: ");
        double firstDeposit = getValidDoubleInput();
        registerAccountList.add(new RegisterAccount(name, password, id, new FinanceManager(firstDeposit)));
        System.out.println("Account registered successfully.\n");
    }

    private void login() {
        System.out.print("Your ID: ");
        Integer id = getValidIntegerInput();
        RegisterAccount account = findAccountById(id);
        if (account != null) {
            System.out.println("Holder with id " + id);
            System.out.println(account.getName() + " - " + id);
            System.out.println("Do you want to continue (y/n)?");
            char op1 = sc.next().charAt(0);
            if (op1 == 'y' || op1 == 'Y') {
                System.out.print("Password: ");
                String password = sc.next();
                if (account.accessAccount(password)) {
                    contextIn(account);
                } else {
                    System.out.println("Invalid password. Returning to main menu.");
                }
            } else {
                System.out.println("Returning to main menu.");
            }
        } else {
            System.out.println("There is no account with this ID.");
        }
    }

    private RegisterAccount findAccountById(Integer id) {
        for (RegisterAccount account : registerAccountList) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null;
    }

    public void contextIn(RegisterAccount account) {
        boolean life = true;
        while (life) {
            System.out.println("1-Finance");
            System.out.println("2-Account");
            System.out.println("3-Back");
            System.out.println("4-Quit");

            int opin = getValidIntegerInput();
            switch (opin) {
                case 1:
                    account.getFinanceManager().financeMenu(sc);
                    break;
                case 2:
                    account.menuAccountManager(sc);
                    break;
                case 3:
                    life = false;
                    break;
                case 4:
                    System.out.println("Exiting. Bye-Bye");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private int getValidIntegerInput() {
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            sc.next();
        }
        return sc.nextInt();
    }

    private double getValidDoubleInput() {
        while (!sc.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid amount.");
            sc.next();
        }
        return sc.nextDouble();
    }


}
