package day2wkshp;

public class FixedDepositAccount extends BankAccount {
    private float interest;
    private int duration;
    private boolean interestSet;
    private boolean durationSet;

    //Set the initial interest and duration (default cannot be changed)
    private static final float DEFAULT_INTEREST = 3.0f;
    private static final int DEFAULT_DURATION = 6;  

    public FixedDepositAccount(String name, float initialBalance) {
        super(name, initialBalance);
        this.interest = DEFAULT_INTEREST;
        this.duration = DEFAULT_DURATION;
        this.interestSet = false;
        this.durationSet = false;
    }

    //Set interest
    public FixedDepositAccount(String name, Float initialBalance, Float interest) {
        super(name, initialBalance);
        this.interest = interest;
        this.duration = DEFAULT_DURATION;
        this.interestSet = true;
        this.durationSet = false;
    
    }
    //set interest and duration
    public FixedDepositAccount (String name, Float initialBalance, Float interest, int duration) {
        super(name, initialBalance);
        this.interest = interest;
        this.duration = duration;
        this.interestSet = true;
        this.durationSet = true;
    }

    public float getInterest() { return interest; }

    public int getDuration() { return duration; }

    public boolean isInterestSet() {return interestSet; }

    public boolean isDurationSet() { return durationSet; }

    //set interest rate (only can change once)
    public void setInterest(float interest) {
        if (interestSet) {
            throw new IllegalArgumentException("Interest can only be set once.");
        }
        this.interest = interest;
        this.interestSet = true;
    }

    //set duration (only can change once) 
    public void setDuration(int duration) {
        if (durationSet) {
            throw new IllegalArgumentException("Duration can only be set once");
        }
        this.duration = duration;
        this.durationSet = true;
    }
    
    @Override
    public void deposit(float amount){
        //No operation
    }

    @Override
    public void withdraw(float amount){
        //No operation
    }

    
    // get balance including interest
    @Override
    public float getAccBal() {
        return super.getAccBal() + interest; // Return balance plus interest
    }

    //Testing method to check for error
    public static void main(String[] args) {
        FixedDepositAccount fdAccount = new FixedDepositAccount("Mary Jane", 100.0f);

        System.out.printf("Initial Balance with interest : $%.2f%n", fdAccount.getAccBal());

        try {
            fdAccount.setInterest(5.0f); // Set interest to 5%
            System.out.println("Interest set to 5%. New Balance (with interest): $" + fdAccount.getAccBal());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            fdAccount.setDuration(12); // Set duration to 12 months
            System.out.println("Duration set to 12 months.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            fdAccount.setDuration(24);
            System.out.println("Duration set to 24 months.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        //final
        System.out.printf("Final Balance (with interest): $%.2f%n", fdAccount.getAccBal());
    
    }

}




    
    