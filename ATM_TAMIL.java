package Tamil;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class ATM_TAMIL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = "தொலைநிலை பரிமாற்ற இயந்திரம்";
        System.out.println();
        System.out.println(text);
        System.out.println("\n");
        System.out.println("உங்கள் அட்டையை இடுங்கள்!\n");
        System.out.print("உங்கள் 4 எண் PIN எண் உள்ளிடவும்: ");
        int pin = sc.nextInt();
        User user_obj = new User();
        user_obj.acc_pin = pin;
        System.out.println();
        while (pin < 1000 || pin > 9999) {
            System.out.println("தவறான PIN!\nமீண்டும் உங்கள் PIN ஐ உள்ளிடவும்: ");
            pin = sc.nextInt();
        }
        sc.nextLine();
        System.out.print("அக்கவுண்ட் ஹோல்டரின் பெயரை உள்ளிடவும்: ");
        user_obj.owner = sc.nextLine();
        System.out.println();
        System.out.print("8 அணி OTP (அந்த வழியில்): ");
        int OTP = sc.nextInt();
        System.out.println();
        while (OTP < 10000000 || OTP > 99999999) {
            System.out.println("தவறான OTP!");
            System.out.print("8 அணி OTP (அந்த வழியில்): ");
            OTP = sc.nextInt();
        }
        System.out.println("-".repeat(30));
        System.out.println();
        System.out.println("குறிப்பு:");
        System.out.println("ஒரே நாளில் அதிகபட்ச பரிவர்த்தனை வரம்பு: 4 LAKHS ரூபாய் மட்டும்!");
        System.out.println();
        System.out.println("-".repeat(30));
        System.out.println();
        Random random = new Random();
        System.out.println("இயந்திரத்தில் உள்ள தொகை: " + (random.nextInt(900) + 100));
        System.out.println();
        int choice;
        InnerATM inner_obj = new InnerATM(user_obj);
        do {
            System.out.println();
            System.out.println("தேர்வுசெய்யவும்: ");
            System.out.println("1. பணத்தை withdraw செய்யவும்");
            System.out.println("2. பணத்தை deposit செய்யவும்");
            System.out.println("3. தற்போதைய இருப்பு");
            System.out.println("4. விவரங்களை திருத்துக");
            System.out.println("5. வெளியேறு!");
            System.out.println();
            System.out.print("உங்கள் தேர்வை உள்ளிடவும்: ");
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
                    System.out.printf("தற்போதைய இருப்பு: ");
                    System.out.println("" + user_obj.balance);
                    break;
                case 4:
                    System.out.println("என்ன நீக்க விரும்புகிறீர்கள்:\n தேர்ந்தெடுக்கவும்\n1. PIN மாறுதல்\n2. அக்கவுண்ட் ஹோல்டர்");
                    int option = sc.nextInt();
                    switch (option) {
                        case 1:
                            System.out.print("உங்கள் புதிய PIN ஐ உள்ளிடவும்: ");
                            user_obj.acc_pin = sc.nextInt();
                            break;
                        case 2:
                            sc.nextLine();
                            System.out.print("உங்கள் பெயரை உள்ளிடவும்: ");
                            user_obj.owner = sc.nextLine();
                            break;
                        default:
                            System.out.println("சரியான விருப்பத்தை உள்ளிடவும்");
                            break;
                    }
                    inner_obj.UserInfo();
                    break;
                case 5:
                    try (FileWriter file = new FileWriter("Account_Details.txt")) {
                        file.write("உரிமையாளர் பெயர்: ");
                        file.write(user_obj.owner);
                        file.write(" அக்கவுண்ட் எண்: ");
                        file.write(user_obj.random16DigitNumber);
                        file.write(" அக்கவுண்ட் PIN: ");
                        file.write(String.valueOf(user_obj.acc_pin));
                    } catch (IOException e) {
                        System.out.println("சமர்ப்பிக்கவில்லை!!");
                    }
                    System.out.println("நன்றி.....");
                    break;
                default:
                    System.out.println("சரியான தேர்வை உள்ளிடவும்!!");
            }
        } while (choice != 5);
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
}

class InnerATM {
    User user;

    InnerATM(User user) {
        this.user = user;
    }
    Scanner sc = new Scanner(System.in);

    void amt_withdraw() {
        System.out.println("நீங்கள் எவ்வளவு தொகையை எடுத்துக்கொள்ள விரும்புகிறீர்கள்: ");
        double w_amt = sc.nextDouble();
        if (user.balance >= w_amt) {
            user.balance -= w_amt;
            System.out.println("வெற்றிகரமாக எடுத்துக்கொள்ளப்பட்டது!!");
            System.out.println("மீதமுள்ள இருப்பு: " + user.balance);
        } else {
            System.out.println("உங்கள் கணக்கில் போதுமான தொகை இல்லை!!");
        }
    }

    void deposit_cash() {
        System.out.println("நீங்கள் எவ்வளவு தொகையை வைச்சிட விரும்புகிறீர்கள்: ");
        double d_amt = sc.nextDouble();
        user.balance += d_amt;
        System.out.println("வெற்றிகரமாக வைச்சிடப்பட்டது!");
        System.out.println("புதிய இருப்பு: " + user.balance);
    }

    void UserInfo() {
        System.out.println();
        System.out.println("அக்கவுண்ட் ஹோல்டர் பெயர் : " + user.owner);
        System.out.println("அக்கவுண்ட் எண் : " + user.random16DigitNumber);
    }
}
