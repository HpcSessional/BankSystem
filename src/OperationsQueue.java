import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OperationsQueue {
    private final Queue<String> queue = new LinkedList<>();
    private final Lock lock = new ReentrantLock();

    public void addSimulation(int totalNumberOfSimulations) {
        lock.lock();
        try {
            for (int i = 0; i < totalNumberOfSimulations; i++) {
                queue.add("Simulation " + (i + 1));
                System.out.println("Added Simulation " + (i + 1));
            }
        } finally {
            lock.unlock();
        }
    }

    public String getNextOperation() {
        lock.lock();
        try {
            return queue.poll();
        } finally {
            lock.unlock();
        }
    }
}
