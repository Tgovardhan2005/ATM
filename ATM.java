import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = "Automated Teller Machine";
        System.out.println();
        System.out.println(text);
        System.out.println("\n");
        System.out.println("INSERT YOUR CARD!\n");
        System.out.print("Enter Your 4 Digit Pin Number: ");
        int pin = sc.nextInt();
        User user_obj = new User();
        user_obj.acc_pin = pin;
        System.out.println();
        while (pin < 1000 || pin > 9999) {
            System.out.println("Invalid PIN!\nReEnter Your PIN: ");
            pin = sc.nextInt();
        }
        sc.nextLine();
        System.out.print("Enter Account Holder Name: ");
        user_obj.owner = sc.nextLine();
        System.out.println();
        System.out.print("Enter 8 digit OTP (Random): ");
        int OTP = sc.nextInt();
        System.out.println();
        while (OTP < 10000000 || OTP > 99999999) {
            System.out.println("Invalid OTP!");
            System.out.print("Enter 8 digit OTP (Random): ");
            OTP = sc.nextInt();
        }
        System.out.println("-".repeat(30));
        System.out.println();
        System.out.println("NOTE:");
        System.out.println("Maximum Transaction Limit per day: 4 LAKHS RUPEES ONLY!");
        System.out.println();
        System.out.println("-".repeat(30));
        System.out.println();
        Random random = new Random();
        System.out.println("Amount that is Available in Machine: " + (random.nextInt(900) + 100));
        System.out.println();
        int choice;
        InnerATM inner_obj = new InnerATM(user_obj);
        do {
            System.out.println();
            System.out.println("Choose: ");
            System.out.println("1. Withdraw Cash");
            System.out.println("2. Deposit Cash");
            System.out.println("3. Current Balance");
            System.out.println("4. Edit Details");
            System.out.println("5. View Transaction History");
            System.out.println("6. EXIT!");
            System.out.println();
            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    inner_obj.amt_withdraw();
                    break;
                case 2:
                    inner_obj.deposit_cash();
                    break;
                case 3:
                    inner_obj.UserInfo();
                    System.out.printf("Current Balance: ");
                    System.out.println("" + user_obj.balance);
                    break;
                case 4:
                    System.out.println("What Would You Like to Edit:\nChoose \n1. PIN Change\n2. Account Holder");
                    int option = sc.nextInt();
                    switch (option) {
                        case 1:
                            System.out.print("Enter Your NEW PIN: ");
                            user_obj.acc_pin = sc.nextInt();
                            break;
                        case 2:
                            sc.nextLine(); // Clear buffer
                            System.out.print("Enter Your NAME: ");
                            user_obj.owner = sc.nextLine();
                            break;
                        default:
                            System.out.println("Enter a Valid Option");
                            break;
                    }
                    inner_obj.UserInfo();
                    break;
                case 5:
                    user_obj.showTransactionHistory();
                    break;
                case 6:
                    // Save account details to file
                    try (FileWriter file = new FileWriter("Account_Details.txt")) {
                        file.write("Owner Name: " + user_obj.owner + "\n");
                        file.write("Account Number: " + user_obj.random16DigitNumber + "\n");
                        file.write("Account PIN: " + user_obj.acc_pin + "\n");
                    } catch (IOException e) {
                        System.out.println("Details Not Stored!!");
                        e.printStackTrace();
                    }
                    System.out.println("Thank You.....");
                    break;
                default:
                    System.out.println("Enter a valid Choice!!");
            }
        } while (choice != 6);
        sc.close();
    }
}

class User {
    double balance = 0;
    int acc_pin = 0;
    String owner = "XXXX";
    Random randomo = new Random();
    long firstHalf = 1000000000000000L + (long) (randomo.nextDouble() * 9000000000000000L);
    String random16DigitNumber = String.valueOf(firstHalf);
    List<String> transactionHistory = new ArrayList<>();

    void addTransaction(String type, double amount) {
        transactionHistory.add(type + ": " + amount);
    }

    void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

class InnerATM {
    User user;

    InnerATM(User user) {
        this.user = user;
    }
    Scanner sc = new Scanner(System.in);

    void amt_withdraw() {
        System.out.println("Enter The Amount you Want to Withdraw: ");
        double w_amt = sc.nextDouble();
        if (user.balance >= w_amt) {
            user.balance -= w_amt;
            System.out.println("Successfully Withdrawn!!");
            System.out.println("Remaining Balance: " + user.balance);
            user.addTransaction("Withdraw", w_amt);
        } else {
            System.out.println("You Don't Have a Sufficient Amount!!");
        }
    }

    void deposit_cash() {
        System.out.println("Enter The Amount you Want to Deposit: ");
        double d_amt = sc.nextDouble();
        user.balance += d_amt;
        System.out.println("Successfully Deposited!");
        System.out.println("New Balance: " + user.balance);
        user.addTransaction("Deposit", d_amt);
    }

    void UserInfo() {
        System.out.println();
        System.out.println("Account Holder Name : " + user.owner);
        System.out.println("Account Number : " + user.random16DigitNumber);
    }
}
