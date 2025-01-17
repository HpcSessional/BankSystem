public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        System.out.println("Initializing banking system..");

        int totalNumberOfSimulation = 10;
        OperationsQueue operationsQueue = new OperationsQueue();
        Bank bank = new Bank("123", operationsQueue);

        System.out.println("Initializing simulation....");
        Thread simulationThread = new Thread(() -> {
            operationsQueue.addSimulation(totalNumberOfSimulation);
        });
        simulationThread.start();

        try {
            simulationThread.join();  // Ensure simulation finishes before starting deposit/withdraw
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Initializing deposit system....");
        Thread depositThread = new Thread(() -> {
            bank.deposit();
        });
        depositThread.start();

        System.out.println("Initializing withdraw system....");
        Thread withdrawThread = new Thread(() -> {
            bank.withdraw();
        });
        withdrawThread.start();

        try {
            depositThread.join();
            withdrawThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed");
    }
}
