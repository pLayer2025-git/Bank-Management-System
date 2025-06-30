import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.time.LocalDate;

//New account will be created in bank account database identified via username and password
public class CreateAccount {
    Scanner sc = new Scanner(System.in);
     String username;
     long acc_no;
     double presentBalance;
     String password;
     String name;
    private LocalDate dob;
    int accType;//1-Savings account 2-FD
    static long min = 1000000000L;   // Smallest 10-digit number
    static long max = 9999999999L;
    CreateAccount()
    {

    }

static ArrayList<Long>accNumList=new ArrayList<>();
    public void createAccount() {
        System.out.println("Fill this credential");
        System.out.println("Create username and password. Don't use spaces in username and password ");
        System.out.print("Username-");
        username = sc.next();
        System.out.print("Password-");
        password = sc.next();
        //Compare username and password to check whether there is any duplicate
        this.kyc();
        long a=this.getAcc_no();
if(accNumList.size()>0)
{
    for (int i=0;i<accNumList.size();i++)
    {
        if(a==accNumList.get(i))
        {
            a=this.getAcc_no();
            i=0;
        }
    }
    acc_no=a;
    accNumList.add(a);
}
else {
    acc_no=a;  accNumList.add(a);
}
System.out.println("Account successfully created.\nThe account number for your bank account is: " + acc_no);
    }

    private void kyc() {
        boolean t = true;
        while (t) {
            sc.nextLine();
            System.out.println("Enter your name");
            name = sc.nextLine();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//            System.out.println("Enter dob in dd-mm-yyy format");
//            sc.nextLine();
//            String DOB = sc.nextLine();
//            try {
//                dob = LocalDate.parse(DOB, formatter);
//            } catch (DateTimeParseException e) {
//                System.out.println("Invalid date format. Please use dd-MM-yyyy format.");
//            }
            System.out.println("Select account type\n1:Savings account\n2:Fixed deposit\nEnter your choice number");
            accType = sc.nextInt();
            if (accType < 0 && accType > 2) {
                System.out.println(" Wrong choice");
            } else {
                t = false;
            }
        }
    }
    private long getAcc_no() {
        Random rand = new Random();
        long random10Digit = min + ((long) (rand.nextDouble() * (max - min + 1)));
        min = min + (min / 1000);
        return random10Digit;
    }
}

