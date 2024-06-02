import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final String id;
    private final OperationsQueue operationsQueue;
    private double balance;
    private final Lock lock = new ReentrantLock();

    public Bank(String id, OperationsQueue operationsQueue) {
        this.id = id;
        this.operationsQueue = operationsQueue;
        this.balance = 0;
    }

    public void deposit() {
        while (true) {
            lock.lock();
            try {
                String operation = operationsQueue.getNextOperation();
                if (operation == null) {
                    break;
                }
                balance += 100;  // Example deposit amount
                System.out.println(operation + ": Deposited 100. Current balance: " + balance);
            } finally {
                lock.unlock();
            }
        }
    }

    public void withdraw() {
        while (true) {
            lock.lock();
            try {
                String operation = operationsQueue.getNextOperation();
                if (operation == null) {
                    break;
                }
                if (balance >= 50) {  // Example withdrawal amount
                    balance -= 50;
                    System.out.println(operation + ": Withdrew 50. Current balance: " + balance);
                } else {
                    System.out.println(operation + ": Insufficient funds. Current balance: " + balance);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
