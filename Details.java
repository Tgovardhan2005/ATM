import java.util.*;
public class Details extends index {
    String acc_no="XXXXXXXXX"+pin;
    String acc_owner="XXXXXX";
    double balance=0;
}
public class Access extends Details{
    Scanner scan = new Scanner(System.in);
    void Cash_Withdraw(){
        System.out.print("Enter Amount(Rs): ");
        double w_amt = scan.nextDouble();
        if(w_amt<=balance){
            balance-=w_amt;
            System.out.println("Succesfully Withdrawed!");
            System.out.println("Remaining balance: "+balance);
        }else{
            System.out.println("You Don't have a Sufficient Amount!");
        }
    }
}
public class index{
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String text = "Automated Teller Machine";
        System.out.println();
        System.out.println(text);
        System.out.println("\n");
        System.out.println("INSERT YOUR CARD!\n");
        System.out.print("Enter Your 4 Digit Pin Number: ");
        int pin=sc.nextInt();
        System.out.println();
        while(pin<1000 || pin>9999){
            System.out.println("Invalid PIN!\nReEnter Your PIN: ");
            pin=sc.nextInt();
        }
        System.out.print("Enter 8 digit OTP(Random): ");
        int OTP=sc.nextInt();
        System.out.println();
        while(OTP<10000000 || OTP>99999999){
            System.out.println("Invalid OTP!");
            System.out.print("Enter 8 digit OTP(Random): ");
            OTP=sc.nextInt();
        }
        System.out.println("-".repeat(30));
        System.out.println();
        System.out.println("NOTE:");
        System.out.println("Maximum Transaction Limit per day: 4 LAKHS RUPEES ONLY!");
        System.out.println();
        System.out.println("-".repeat(30));
        System.out.println();
        Random random = new Random();
        System.out.println("Amount that Avalible in Machine: "+random.nextInt(900)+100);

        System.out.println();
        Access ac = new Access();
        int choice;
        do{
            System.out.println("Choose: ");
            System.out.println("1.Withdraw Cash");
            System.out.println("2.Deposite Cash");
            System.out.println("3.Current balance");
            System.out.println("4.Edit Details");
            System.out.println("5.EXIT!");
            choice=sc.nextInt();
            switch(choice){
                case 1:
                ac.Cash_Withdraw();
                break;
                case 2:
                break;
                case 3:
                break;
                case 4:
                break;
                case 5:
                System.err.println("Exiting.....");
                break;
            }
        }while(choice!=5);
    }
}
