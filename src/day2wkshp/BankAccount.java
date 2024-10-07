package day2wkshp;

import java.time.LocalDate;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class BankAccount {
    
    //All members need to be private
    //Account name cannot be change once set
    private final String accName;
    private final String accNum;
    private float accBal;
    private ArrayList<String> accTrans;
    private boolean closedAcc;
    private LocalDate accCloseDate;
    private LocalDate accOpenDate;

    //Constructor
    public BankAccount(String accName) {
        this.accName = accName;
        this.accBal = 0.0f;
        this.accNum = generateRandomAccNum(12);
        this.accTrans = new ArrayList<>();
        this.closedAcc = false;
        this.accOpenDate = LocalDate.now();
        this.accCloseDate = null;
    }

    public BankAccount(String accName, float initialBalance) {
        this.accName = accName;
        this.accBal = initialBalance;
        this.accNum = generateRandomAccNum(12);
        this.accTrans = new ArrayList<>();
        this.closedAcc = false;
        this.accOpenDate = LocalDate.now();
        this.accCloseDate = null;
    }
    
    //Method to generate a random account number
    private static String generateRandomAccNum(int length) {
        SecureRandom secureRandom = new SecureRandom();
        char[] digits = new char[length];
       //Make sure the digits are between 0-9
        digits[0]= (char)(secureRandom.nextInt(9) + '1');
        //check for repeat
        for (int i = 1; i < length; i++) {
            digits[0] = (char)(secureRandom.nextInt(10) + '0');
        }

        return new String(digits);
    }
    
    //Getter and Setters
    public String getAccName() { return accName; }
    public String getAccNum() { return accNum; }
    public float getAccBal() { return accBal; }
    public ArrayList<String> getAccTrans() { return accTrans; }
    public boolean isClosedAcc() { return closedAcc; }
    public LocalDate getAccCloseDate() { return accCloseDate; }
    public LocalDate getAccOpenDate() { return accOpenDate; }

    //Deposit Method
    public void deposit(float amount){
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount need to be positive or more than $0");
        } else if (closedAcc) {
            throw new IllegalArgumentException("Cannot deposit into a closed account.");
        } else {
            accBal += amount;
            //Add transaction to the array
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String transaction = String.format("Deposit $%.2f at %s.\n", amount, now.format(formatter));
            accTrans.add(transaction);
        }

    }

    //Withdraw Method
    public void withdraw(float amount){
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount need to be positive or more than $0");
        } else if (closedAcc) {
            throw new IllegalArgumentException("Cannot withdraw from a closed account.");
        } else if (accBal < amount){ //When there is not enough balance to withdraw from the bank
            throw new IllegalArgumentException("Insufficient Amount to withdraw from account.");
        } else {
            accBal -= amount;
            //Add transaction to the array
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String transaction = String.format("Withdraw $%.2f at %s.\n", amount, now.format(formatter));
            accTrans.add(transaction);
        }

    }
    
    //close account method
    public void closeAccount(){
        this.closedAcc = true;
    }

    //testing method
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount("Jane Doe", 200.0f); // Initial balance of $200
    
        try {
            myAccount.withdraw(50.0f); // Withdraw $50
            System.out.println("New Balance: $" + myAccount.getAccBal());
            System.out.println("Transactions: " + myAccount.getAccTrans());
    
            // Attempting to withdraw more than the current balance
            myAccount.withdraw(200.0f);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    
        // Closing the account and trying to withdraw again
        myAccount.closeAccount();
    
        try {
            myAccount.withdraw(50.0f); // This should throw an exception
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
