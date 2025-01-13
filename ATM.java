import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nAutomated Teller Machine\n");
        System.out.println("Are you a new user? (Yes/No): ");
        String isNewUser = sc.nextLine().trim().toLowerCase();
        int check1=0;
        do{
            if(isNewUser.equals("yes")||isNewUser.equals("no"))
            check1=1;
            else{
                System.out.println("Invalid !! Enter Yes/No");
                isNewUser = sc.nextLine().trim().toLowerCase();
            }
        }while(check1==0);
        User user_obj;
        if (isNewUser.equals("yes")) {
            user_obj = new User();
            System.out.print("Enter Your Name: ");
            user_obj.owner = sc.nextLine();
            System.out.print("Set a 4-Digit PIN: ");
            int pin=0;
            boolean validPin=false;
        while (!validPin) {
            try {
                pin = sc.nextInt();
                sc.nextLine(); // Clear the buffer
                // Check if it's a 4-digit number
                if ((int) Math.log10(pin) + 1 == 4) {
                    validPin = true; // Exit the loop if valid
                } else {
                    System.out.println("Invalid ! Please enter a numeric 4-digit PIN:");
                }
            } catch (Exception e) {
            System.out.println("Invalid ! Please enter a numeric 4-digit PIN:");
            sc.nextLine(); // Clear the invalid input
            }
        }
            user_obj.acc_pin=pin;
            System.out.println("Account Created Successfully!");
            System.out.println("Your Account Number: " + user_obj.random16DigitNumber);
        } else {
            System.out.print("Enter Your 4-Digit PIN: ");
            user_obj = new User();
            int pin0=0;
            boolean validPin0=false;
        while (!validPin0) {
            try {
                pin0 = sc.nextInt();
                sc.nextLine(); // Clear the buffer
                // Check if it's a 4-digit number
                if ((int) Math.log10(pin0) + 1 == 4) {
                    validPin0 = true; // Exit the loop if valid
                } else {
                    System.out.println("Invalid ! Please enter a numeric 4-digit PIN:");
                }
            } catch (Exception e) {
            System.out.println("Invalid ! Please enter a numeric 4-digit PIN:");
            sc.nextLine(); // Clear the invalid input
            }
        }
            user_obj.acc_pin=pin0;
            System.out.println("Account Created Successfully!");
            System.out.println("Your Account Number: " + user_obj.random16DigitNumber);

        InnerATM inner_obj = new InnerATM(user_obj);
        int choice;
        do {
            System.out.println("\nChoose: ");
            System.out.println("1. Withdraw Cash");
            System.out.println("2. Deposit Cash");
            System.out.println("3. Current Balance & Details");
            System.out.println("4. Edit Details");
            System.out.println("5. View Transaction History");
            System.out.println("6. EXIT!");
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
                    System.out.printf("Current Balance: %.2f rs\n", user_obj.balance);
                    break;
                case 4:
                    
                        System.out.println("What Would You Like to Edit:\n1. PIN Change\n2. Account Holder Name");
                        int option = 0;
                        boolean validOption = false;
                        while (!validOption) {
                            try {
                                option = sc.nextInt();
                                sc.nextLine(); // Clear buffer
                                if (option == 1 || option == 2) {
                                     validOption = true;
                                } else {
                                     System.out.println("Invalid choice! Please select 1 or 2.");
                                }
                            }catch (Exception e) {
                                    System.out.println("Invalid input! Please enter 1 or 2.");
                                    sc.nextLine(); // Clear invalid input
                            }
                     }
                    int pin=0;
                    if (option == 1) {
                        System.out.print("Enter Your NEW PIN: ");
                        boolean validPin = false;
                        
                    while (!validPin) {
                        try {
                            pin = sc.nextInt();
                            sc.nextLine(); // Clear buffer
                            if ((int) Math.log10(pin) + 1 == 4) {
                                validPin = true;
                            } else {
                                System.out.println("Invalid! Please enter a numeric 4-digit PIN:");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid! Please enter a numeric 4-digit PIN:");
                            sc.nextLine(); // Clear invalid input
                        }
                    }
                    user_obj.acc_pin = pin;
                    System.out.println("PIN updated successfully!");
                    } else if (option == 2) {
                        System.out.print("Enter Your NEW Account Holder Name: ");
                        user_obj.owner = sc.nextLine();
                         System.out.println("Account holder name updated successfully!");
                    }
                    inner_obj.UserInfo();
                    break;

                case 5:
                    user_obj.showTransactionHistory();
                    break;
                case 6:
                    try (FileWriter file = new FileWriter("Account_Details.txt")) {
                        file.write("Owner Name: " + user_obj.owner + "\n");
                        file.write("Account Number: " + user_obj.random16DigitNumber + "\n");
                        file.write("Account PIN: " + user_obj.acc_pin + "\n");
                    } catch (IOException e) {
                        System.out.println("");
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
}
class User {
    double balance = 0;
    int acc_pin = 0;
    String owner = "XXXX";
    Random randomo = new Random();
    final String random16DigitNumber = String.valueOf(1000000000000000L + (long) (randomo.nextDouble() * 9000000000000000L));
    List<String> transactionHistory = new ArrayList<>();

    void addTransaction(String type, double amount) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        transactionHistory.add(type + ": " + amount + " at " + timestamp);
    }

    void showTransactionHistory() {
        System.out.println("Transaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No Transactions Yet!");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
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
        System.out.print("Enter The Amount you Want to Withdraw: ");
        double w_amt = sc.nextDouble();
        if (user.balance >= w_amt) {
            user.balance -= w_amt;
            System.out.println("Successfully Withdrawn!");
            System.out.println("Remaining Balance: " + user.balance);
            user.addTransaction("Withdraw", w_amt);
        } else {
            System.out.println("Insufficient Balance!");
        }
    }

    void deposit_cash() {
        System.out.print("Enter The Amount you Want to Deposit: ");
        double d_amt = sc.nextDouble();
        user.balance += d_amt;
        System.out.println("Successfully Deposited!");
        System.out.println("New Balance: " + user.balance);
        user.addTransaction("Deposit", d_amt);
    }

    void UserInfo() {
        System.out.println("\nAccount Holder Name: " + user.owner);
        System.out.println("Account Number: " + user.random16DigitNumber);
    }
}
