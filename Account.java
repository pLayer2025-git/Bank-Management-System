import java.util.Scanner;

//Account internal management like deposit ,withdrawl or view balance.
class Account {
    Scanner sc1 = new Scanner(System.in);
    private double balance;
    private String username;
    private long acc_no;

    Account(String username, long acc_no) {
        this.username = username;
        this.acc_no = acc_no;
    }

    double menu() {
        System.out.println("Welcome " + username);
        while (true) {
            System.out.println("1-view balance\n2-withdraw amount\n3-deposit amount\n4-Exit");
            switch (sc1.nextInt()) {
                case 1:
                    viewBalance();
                    break;
                case 2:
                    System.out.println("Enter your amount");
                    withdrawlSavingAccount(sc1.nextDouble());
                    break;
                case 3:
                    System.out.println("Enter your amount");
                    deposit(sc1.nextDouble());
                    break;
                case 4:
                    return this.balance;
                default:
                    System.out.println("Invalid choice");
            }
        }
        //2
        // return balance;
    }


    void viewBalance() {
        System.out.println("Balance=" + balance);
    }

    void withdrawlSavingAccount(double amt) {
        if ((amt <= (balance - 1000)) && (amt > 0)) {
            balance = balance - amt;
            System.out.println("Withdrawl Successfully");
        } else {
            System.out.println("Cannot withdrawl given amount! ");
            System.out.println("Minimum account balance allowed is 1000 rs ");
        }
    }

    void deposit(double amt) {
        if (amt > 0) {
            balance = balance + amt;
            if (balance < 1000) {
                System.out.println("balance amt  less than 1000 not allowed, please entered more amt");
                balance = balance - amt;
            }
            else
            {
                System.out.println("Amount Deposited Successfully");
            }

        }
    }
    //update below function in future

    void payTo(long acc_no) {
        //for transaction and all
    }

    void receiveFrom(long acc_no) {
        //for receiving
    }
}
