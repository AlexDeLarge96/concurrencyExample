import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Assigner {
    private List<Cashier> cashiers = new ArrayList<>();
    ExecutorService executor;

    public Assigner() {
        executor= Executors.newFixedThreadPool(3);
        createCashier(3);
    }

    /**
     * Attend a client on wait. Looking for an available agent and assign a client to this agent.
     * If there aren't available agents then the client is put on hold
     * @param client The client on wait
     */
    public void attend(Client client) {
        Cashier cashierAvailable = obtainCashierAvailable();
        if (cashierAvailable != null) {
            cashierAvailable.setAssignedClient(client);
            cashierAvailable.setAvailability(false);
            CompletableFuture
                    .supplyAsync(cashierAvailable, executor)
                    .thenAccept(response -> {
                        System.out.println(response + " " + cashierAvailable.getTimeAttention());
                        cashierAvailable.setAvailability(true);
                        cashierAvailable.setAssignedClient(null);
                    });
        }
        else{
            putOnWait(client);
        }
    }

    public void putOnWait(Client client)
    {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        attend(client);
    }

    public void finishAssignerJob()
    {
        executor.shutdown();
    }
    private Cashier obtainCashierAvailable() {
        return cashiers.stream().filter(Cashier::isAvailability).findFirst().orElse(null);
    }

    private void createCashier(int numberCashier) {
        for (int i = 1; i <= numberCashier; i++) {
            cashiers.add(new Cashier("Cashier#" + i, true, null));
        }
    }
}
