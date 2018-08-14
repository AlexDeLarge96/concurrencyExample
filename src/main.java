import java.util.ArrayList;
import java.util.List;

public class main
{
    private static List<Client> clients=new ArrayList<>();
    public static void main(String[] args)
    {
        createClients(5);
        Assigner a=new Assigner();
        for(Client client:clients) {
            client.performOperation(a);
        }
        a.finishAssignerJob();
    }

    public static void createClients(int numberClients)
    {
        for(int i=1;i<=numberClients;i++) {
            clients.add(new Client("Client#" + i,getOperation() ));
        }
    }

    private static OperationType getOperation(){
        switch ((int)(Math.random()*2)+1)
        {
            case 1:
                return OperationType.BUY;
            case 2:
                return OperationType.SELF;
                default:
                    return OperationType.BUY;
        }
    }
}
