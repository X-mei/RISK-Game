package client;

public class AssignTerrController {
    AssignTerrView assignTerrView;
    Client client;
    String prompt;

    public String change(Client client){
        String prompt = client.recvNameAndSeq();
        return prompt;
    }

    public AssignTerrController(AssignTerrView assignTerrView, Client client) {
        this.assignTerrView = assignTerrView;
        this.client = client;
    }
}
