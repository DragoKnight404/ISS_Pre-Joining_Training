/**
 * Part 8: Multithreading & Concurrency
 * Concepts Covered:
 * 1. Thread Safety: Instance variables (Unsafe) vs Local variables (Safe)
 * 2. Synchronization: Method-level vs Block-level locking
 * 3. Keywords: 'volatile' (Visibility)
 * 4. ExecutorService: Thread Pools (The modern way to manage threads)
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class SharedInventory {

    // INSTANCE VARIABLE (Shared State)
    // This lives in the Heap. Multiple threads access the SAME memory.
    // WITHOUT synchronization, this is NOT thread-safe.
    private int itemsInStock = 0;

    // VOLATILE KEYWORD (Visibility Guarantee)
    // Ensures that changes to this variable are immediately visible to all threads.
    // It prevents the CPU from caching this value locally.
    // Note: volatile does NOT guarantee atomicity (use synchronized for that).
    private volatile boolean systemRunning = true;

    // ==========================================
    // 1. SYNCHRONIZED METHOD
    // Locks the ENTIRE method. Only one thread can enter at a time.
    // Good for simple logic, but can be slow if the method is long.
    // ==========================================
    public synchronized void addStockMethodSync() {
        itemsInStock++;
    }

    // ==========================================
    // 2. SYNCHRONIZED BLOCK
    // Locks only a CRITICAL SECTION.
    // Better performance because threads wait less.
    // ==========================================
    public void addStockBlockSync() {
        // ... heavy non-critical logic could go here ...

        synchronized (this) {
            itemsInStock++;
        }
    }

    // ==========================================
    // 3. LOCAL VARIABLE SAFETY
    // Local variables live on the Stack. Each thread has its OWN stack.
    // Therefore, local variables are inherently THREAD-SAFE.
    // ==========================================
    public void demonstrateLocalSafety(String threadName) {
        int tempCount = 0; // Local variable
        tempCount++;
        System.out.println(threadName + " local count: " + tempCount + " (Safe)");
    }

    public int getStock() {
        return itemsInStock;
    }

    public void stopSystem() {
        this.systemRunning = false;
    }

    public boolean isRunning() {
        return systemRunning;
    }
}

public class Part8 {
    public static void main(String[] args) throws InterruptedException {

        SharedInventory inventory = new SharedInventory();

        // ==========================================
        // 4. THREAD POOL EXECUTOR
        // Instead of 'new Thread()', we use a pool.
        // It recycles threads, saving memory and startup time.
        // ==========================================
        System.out.println("--- Starting Thread Pool (3 Threads) ---");
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // TASK 1: Use Synchronized Method (1000 times)
        executor.submit(() -> {
            for(int i=0; i<1000; i++) {
                inventory.addStockMethodSync();
            }
            inventory.demonstrateLocalSafety("Thread-1");
        });

        // TASK 2: Use Synchronized Block (1000 times)
        executor.submit(() -> {
            for(int i=0; i<1000; i++) {
                inventory.addStockBlockSync();
            }
            inventory.demonstrateLocalSafety("Thread-2");
        });

        // TASK 3: Volatile Monitor
        // This thread runs as long as the volatile flag is true.
        executor.submit(() -> {
            System.out.println("Monitor Thread Started...");
            while (inventory.isRunning()) {
                // Busy wait simulation
            }
            System.out.println("Monitor Thread Detected Stop Signal!");
        });

        // Shutdown stops the service from accepting new tasks but allows existing tasks to complete.
        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.SECONDS); // Wait for tasks to finish

        inventory.stopSystem(); // This flips the volatile flag, stopping Thread 3

        System.out.println("\n--- Final Results ---");
        System.out.println("Expected Stock: 2000");
        System.out.println("Actual Stock:   " + inventory.getStock());

        if (inventory.getStock() == 2000) {
            System.out.println(">> SUCCESS: Thread Safety Achieved.");
        } else {
            System.out.println(">> FAILURE: Race Condition Detected.");
        }
    }
}