public class Client
{
    private String name;
    private OperationType operation;

    public Client(String name, OperationType operation) {
        this.name = name;
        this.operation = operation;
    }

    public void performOperation(Assigner assigner){
        assigner.attend(this);
    }

    public String getName() {
        return name;
    }

    public OperationType getOperation() {
        return operation;
    }
}
