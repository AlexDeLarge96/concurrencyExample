import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Assigner
{
    private List<Cashier>cashiers=new ArrayList<>();
    ExecutorService executor= Executors.newFixedThreadPool(3);

    public Assigner() {
        createCashier(3);
    }

    public void attend(List<Client> clients)
    {
        int i=0;
        while(i<clients.size()) {
            Cashier cashierAvailable = obtainCashierAvailable();
            if (cashierAvailable != null) {
                cashierAvailable.setAssignedClient(clients.get(i));
                cashierAvailable.setAvailability(false);
                CompletableFuture
                        .supplyAsync(cashierAvailable, executor)
                        .thenAccept(response ->{System.out.println(response + " " + cashierAvailable.getTimeAttention());
                        cashierAvailable.setAvailability(true);cashierAvailable.setAssignedClient(null);
                        });
                i++;

            }
        }
        executor.shutdown();
    }

//    public void putOnWait()
//    {
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        attend();
//    }

    private Cashier obtainCashierAvailable()
    {
        for(Cashier cashier:cashiers)
        {
            if(cashier.isAvailability()){return cashier;}
        }
        return null;
    }

    private void createCashier(int numberCashier)
    {
        for(int i=1;i<=numberCashier;i++){
            cashiers.add(new Cashier("Cashier#"+i,true,null));
        }
    }
}
