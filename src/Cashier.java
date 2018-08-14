import java.util.function.Supplier;

public class Cashier implements Supplier<String>
{
    private String name;
    private boolean availability;
    private Client assignedClient;
    private int timeAttention;

    public Cashier(String name, boolean availability, Client assignedClient) {
        this.name = name;
        this.availability = availability;
        this.assignedClient = assignedClient;
    }

    public String attendClient()
    {
        timeAttention=(int)(Math.random()*5)+1;
        try {
            Thread.sleep(timeAttention*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name+" "+assignedClient.getName()+" "+assignedClient.getOperation();
    }

    @Override
    public String get() {
        return attendClient();
    }

    public int getTimeAttention() {
        return timeAttention;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAssignedClient(Client assignedClient) {
        this.assignedClient = assignedClient;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
