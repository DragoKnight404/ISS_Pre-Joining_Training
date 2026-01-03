/**
Part 5: OOP Pillars
Concepts Covered:
1. Encapsulation: Private data + Getters/Setters
2. Abstraction: Abstract classes & methods
3. Inheritance: 'extends' keyword
4. Polymorphism: Overloading (Compile-time) vs Overriding (Runtime)
5. Interfaces: Contracts for behavior
 */

// INTERFACE (The Contract)
interface SecurityProtocol {
    // Interfaces define WHAT must be done, not HOW.
    // Variables in interfaces are 'public static final' by default.
    String ENCRYPTION_TYPE = "SHA-256";

    void scanForFraud(); // Abstract method by default
}

// ABSTRACT CLASS (The Template)
abstract class PaymentChannel implements SecurityProtocol {

    // ENCAPSULATION: Data is private. Direct access is blocked.
    private String transactionId;
    private double balance;

    public PaymentChannel(String id, double initialBalance) {
        this.transactionId = id;
        this.balance = initialBalance;
    }

    // Encapsulation: Getter (Controlled Read Access)
    public double getBalance() {
        return balance;
    }

    // Encapsulation: Setter (Controlled Write Access with Validation)
    public void deductBalance(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println(">> Transaction Failed: Insufficient Funds");
        }
    }

    // ABSTRACTION: This method MUST be implemented by child classes.
    // The "What" is defined here. The "How" depends on the child.
    abstract void processPayment(double amount);

    // Concrete method (Inherited by all children)
    public void printReceipt() {
        System.out.println("Transaction ID: " + transactionId + " | Encrypted: " + ENCRYPTION_TYPE);
    }
}

// CONCRETE CLASS 1 (Credit Card)
class CreditCard extends PaymentChannel {

    public CreditCard(String id, double limit) {
        super(id, limit); // Calls parent constructor
    }

    // POLYMORPHISM (OVERRIDING): Changing parent behavior
    // This occurs at RUNTIME.
    @Override
    void processPayment(double amount) {
        System.out.println("Connecting to VISA Gateway...");
        if (getBalance() >= amount) {
            deductBalance(amount);
            System.out.println("Success: Paid $" + amount + " via Credit Card.");
        }
    }

    // Interface implementation
    @Override
    public void scanForFraud() {
        System.out.println("Security: Scanning Location and IP address...");
    }
}


// CONCRETE CLASS 2 (Digital Wallet)
class DigitalWallet extends PaymentChannel {

    String walletOwner;

    public DigitalWallet(String id, double balance, String owner) {
        super(id, balance);
        this.walletOwner = owner;
    }

    // POLYMORPHISM (OVERRIDING)
    @Override
    void processPayment(double amount) {
        System.out.println("Verifying Biometrics for " + walletOwner + "...");
        deductBalance(amount);
        System.out.println("Success: Wallet transfer of $" + amount + " complete.");
    }

    // POLYMORPHISM (OVERLOADING): Same method name, different parameters.
    // This occurs at COMPILE TIME.
    // Scenario: User applies a promo code.
    void processPayment(double amount, String promoCode) {
        System.out.println("Applying Promo Code: " + promoCode);
        double discountedAmount = amount * 0.90; // 10% discount
        processPayment(discountedAmount); // Reuse the logic above
    }

    @Override
    public void scanForFraud() {
        System.out.println("Security: Verifying Device Fingerprint...");
    }
}

// MAIN CLASS (Execution)
public class Part5 {
    public static void main(String[] args) {

        System.out.println("--- Scenario 1: Polymorphism (Overriding) ---");
        // Parent Reference -> Child Object
        // This is crucial. The variable is 'PaymentChannel', but it behaves like 'CreditCard'.
        PaymentChannel myCard = new CreditCard("TXN_101", 500.00);

        myCard.scanForFraud();      // Calls CreditCard's implementation
        myCard.processPayment(100); // Calls CreditCard's implementation
        myCard.printReceipt();      // Calls Parent's implementation

        System.out.println("\n--- Scenario 2: Polymorphism (Overloading) ---");
        DigitalWallet myWallet = new DigitalWallet("TXN_102", 200.00, "Alice");

        // Calling the overloaded method (with 2 arguments)
        myWallet.processPayment(50, "CASHBACK10");

        System.out.println("\n--- Scenario 3: Encapsulation Protection ---");
        // myCard.balance = 0; // ERROR: 'balance' has private access. Cannot be accessed outside the class.
        System.out.println("Remaining Balance (Accessed via Getter): " + myCard.getBalance());
    }
}