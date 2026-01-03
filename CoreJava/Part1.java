// Part 1: Basics of Java Concepts: Class, Object, State, and Behavior.
// This class demonstrates the blueprint for a Bank Account.
// It encapsulates state (account details) and exposes behavior (transaction methods).

class BankAccount{
    private double currentBalance;
    private String accountHolderName;
    private boolean isActive;

    public BankAccount(String name){
        this(name, 0.00);
    }

    public BankAccount(String name, double initialDepositAmount){
        if(initialDepositAmount<0) initialDepositAmount = 0;
        this.currentBalance = initialDepositAmount;
        this.accountHolderName = name;
        this.isActive = true;
        System.out.println(">> Acount Created for "+name);
    }

    public void depositAmount(double amount){
        if(amount>=0){
            this.currentBalance += amount;
            System.out.println(accountHolderName+" deposited: Rs "+amount);
        } else{
            System.out.println("Error: Deposited Amount must be positive!");
        }
    }

    public void withdrawAmount(double amount){
        if(this.currentBalance < amount && amount > 0){
            System.out.println("Cannot withdraw insufficient funds : Current Balance Rs "+this.currentBalance);
        } else {
            this.currentBalance -= amount;
            System.out.println(accountHolderName+" withdrawed: Rs "+amount);
        }
    }

    // Public "getter" method to securely retrieve the current balance
    public double getBalance() {
        return this.currentBalance;
    }

    public String getAccountHolderName(){
        return this.accountHolderName;
    }

    public void showStatus(){
        System.out.println("--------------------------------");
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Current Balance: Rs " + currentBalance);
        System.out.println("Active Status: " + isActive);
        System.out.println("--------------------------------");
    }

}

public class Part1 {
    public static void main(String[] args) {
        // 1. Creating an Object (Instance)
        BankAccount account1 = new BankAccount("Aryan Kanyawar", 2000.00);

        // 2. Accessing Behavior
        account1.showStatus(); // Check initial state

        // 3. Changing State via Behavior getter setter methods
        account1.depositAmount(300);
        account1.withdrawAmount(200);

        // State has now changed
        account1.showStatus();

        // Creating a second, independent object
        BankAccount account2 = new BankAccount("Sahil Yadav", 1000.00);
        account2.showStatus();

        // Using getter:
        System.out.println("Accessed Using getter : "+account2.getAccountHolderName()+" has a balance "+account2.getBalance());

        // account1.currentBalance = 10000; throws a compile time error cannot access private member from outside the class

        // Example of constructor overloading
        BankAccount acc3 = new BankAccount("Aroosh Joshi");
        acc3.showStatus();

    }
}
