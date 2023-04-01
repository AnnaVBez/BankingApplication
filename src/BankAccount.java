import java.text.DecimalFormat;
import java.util.Scanner;

public class BankAccount implements BankingOperations{
    private double balanceRUB;
    private double balanceEUR;
    private double balanceUSD;
    private User account;


    public BankAccount(User account) {
        this.account = account;
    }

    @Override
    public void deposit(int amount, int currency) {
        switch (currency) {
            case 1 -> {
                balanceRUB += amount;
            }
            case 2 -> {
                balanceUSD += amount;
            }
            case 3 -> {
                balanceEUR += amount;
            }
        }
        System.out.println("You have deposited " + amount);
    }
    @Override
    public void withdraw(int amount, int currency) {
        switch (currency) {
            case 1 -> {
                if (amount >= balanceRUB) {
                    System.err.println("There are not enough funds in the account!");
                    return;
                }
                balanceRUB -= amount;
            }
            case 2 -> {
                if (amount >= balanceUSD) {
                    System.err.println("There are not enough funds in the account!");
                    return;
                }
                balanceUSD -= amount;
            }
            case 3 -> {
                if (amount >= balanceEUR) {
                    System.err.println("There are not enough funds in the account!");
                    return;
                }
                balanceEUR -= amount;
            }
        }
        System.out.println("You have withdrawn " + amount);
    }
    @Override
    public void currencyTransfer(int currencyCurrent, int amount, int currencyAnother) {
        switch (currencyCurrent) {
            case 1 -> { // from RUB to USD and EUR
                if (currencyAnother == 1) {
                    double conversionToUSD = amount / 77.09;
                    balanceUSD += conversionToUSD;
                } else {
                    double conversionToEUR = amount / 83.76;
                    balanceEUR += conversionToEUR;
                }
                balanceRUB -= amount;
            }
            case 2 -> { // from USD to RUB and EUR
                if (currencyAnother == 1) {
                    double conversionToRUB = amount * 77.09;
                    balanceRUB += conversionToRUB;
                } else {
                    double conversionToEUR = amount * 0.91895;
                    balanceEUR += conversionToEUR;
                }
                balanceUSD -= amount;
            }
            case 3 -> { // from EUR to RUB and USD
                if (currencyAnother == 1) {
                    double conversionToRUB = amount * 83.76;
                    balanceRUB += conversionToRUB;
                } else {
                    double conversionToUSD = amount * 1.09;
                    balanceUSD += conversionToUSD;
                }
                balanceEUR -= amount;
            }
        }
        System.out.println("The conversion was successful!");
    }
    // checking for the correctness of the number
    public int enterAndCheckInt(Scanner scan, int elements) {
        int number;
        while (true) {
            String input = scan.next();
            try {
                number = Integer.parseInt(input);
                if (elements == 3) {
                    if (number == 1 | number == 2 | number == 3) {
                        break;
                    }
                } else {
                    if (number == 1 | number == 2) {
                        break;
                    }
                }
                throw new NumberFormatException();
            } catch (NumberFormatException nf) {
                if (elements == 3) {
                    System.err.println("Enter a number from 1 to 3!");
                } else System.err.println("Enter a number from 1 or 2!");
            }
        }
        return number;
    }
    // checking for the correctness of the amount
    public int enterAndCheckAmount(Scanner scan) {
        int number;
        while (true) {
            String input = scan.next();
            try {
                number = Integer.parseInt(input);
                if (number > 0) {
                    break;
                } else if (number == 0) {
                    System.err.println("Enter an amount greater than zero!");
                    continue;
                }
                throw new NumberFormatException();
            } catch (NumberFormatException nf) {
                System.err.println("Enter a positive number!");
            }
        }
        return number;
    }

    public void showMenu() {
        char option = '\0';
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello " + account.getName() + "!");

        do {
            System.out.println("~~~~~~~~~~~~~~~~");
            System.out.println("Enter an option");
            System.out.println("A. Check Balance");
            System.out.println("B. Deposit");
            System.out.println("C. Withdraw");
            System.out.println("D. Transfer money from one currency account to another");
            System.out.println("E. Exit");
            System.out.println("~~~~~~~~~~~~~~~~");
            option = scanner.next().charAt(0);
            switch (option) {
                case 'A' -> {
                    System.out.println("------------");
                    System.out.println("Balance RUB = " + new DecimalFormat("#.##").format(balanceRUB) + " RUB");
                    System.out.println("Balance USD = " + new DecimalFormat("#.##").format(balanceUSD) + " USD");
                    System.out.println("Balance EUR = " + new DecimalFormat("#.##").format(balanceEUR) + " EUR");
                    System.out.println("------------");
                    System.out.println("\n");
                }
                case 'B' -> {
                    System.out.println("------------");
                    System.out.println("Enter a number in which currency to make a deposit");
                    System.out.println("1. RUB");
                    System.out.println("2. USD");
                    System.out.println("3. EUR");
                    System.out.println("------------");
                    int currency = enterAndCheckInt(scanner, 3);
                    System.out.println("Enter an amount to deposit:");
                    int amount = enterAndCheckAmount(scanner);
                    deposit(amount, currency);
                }
                case 'C' -> {
                    System.out.println("------------");
                    System.out.println("Enter the number in which currency you want to withdraw");
                    System.out.println("1. RUB");
                    System.out.println("2. USD");
                    System.out.println("3. EUR");
                    System.out.println("------------");
                    int currency = enterAndCheckInt(scanner, 3);
                    System.out.println("Enter an amount to withdraw:");
                    int amount = enterAndCheckAmount(scanner);
                    withdraw(amount, currency);
                }
                case 'D' -> {
                    System.out.println("------------");
                    System.out.println("Select the account from which the currency conversion will be received");
                    System.out.println("1. RUB");
                    System.out.println("2. USD");
                    System.out.println("3. EUR");
                    System.out.println("------------");
                    int currencyCurrent = enterAndCheckInt(scanner, 3);
                    System.out.println("Enter an amount to conversion:");
                    int amount = enterAndCheckAmount(scanner);

                    System.out.println("------------");
                    System.out.println("Select the account to which the currency conversion will be made");
                    if (currencyCurrent == 1) {
                        System.out.println("1. USD");
                        System.out.println("2. EUR");
                    } else if (currencyCurrent == 2) {
                        System.out.println("1. RUB");
                        System.out.println("2. EUR");
                    } else {
                        System.out.println("1. RUB");
                        System.out.println("2. USD");
                    }
                    System.out.println("------------");
                    int currencyAnother = enterAndCheckInt(scanner, 2);
                    currencyTransfer(currencyCurrent, amount, currencyAnother);
                }
                case 'E' -> System.out.println("~~~~~~~~~~~~~~~~");
                default -> System.err.println("Invalid option! Please enter again");
            }
        } while (option != 'E');

        System.out.println("Thank you for using our application!");
    }
}
