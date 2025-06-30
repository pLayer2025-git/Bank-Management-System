import java.util.ArrayList;
import java.util.Scanner;

// After login the account direct this account to account class where there is menu driven program for payment and all
public class LoginAccount {
    Scanner sc3=new Scanner(System.in);
    void loginAccount(ArrayList<CreateAccount>ob) {
        String u1, p;
        System.out.println("Enter your credentials");
        System.out.println("Enter username");
        u1 = sc3.next();
        System.out.println("Enter your password");
        p = sc3.next();boolean t=true;
        if (ob.size() > 0) {
            for (int i = 0; i < ob.size(); i++) {
                if ((ob.get(i).username.equals(u1)) && (ob.get(i).password.equals(p))) {
                    Account object = new Account(ob.get(i).username, ob.get(i).acc_no);
                    ob.get(i).presentBalance=object.menu();t=false;
                    break;
                }
            }
            if(t)
            {
                System.out.println("Incorrect Username or Password");
            }
        }
        else {
            System.out.println("No account inside bank");
        }
    }

}
