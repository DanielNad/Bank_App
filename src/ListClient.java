import Model.Client;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class ListClient
{
    ArrayList<Client> clientArrayList;
    private static final String filepath="src/Files/Client.det";

    public ListClient() {
        this.clientArrayList = new ArrayList<Client>();
    }

    public ArrayList<Client> getClientArrayList() {
        return clientArrayList;
    }

    public void add_to_list(Client client){
        this.clientArrayList.add(client);
    }

    public void remove_banker(int clientid){
        for (Client b:this.clientArrayList) {
            if(clientid==b.getClientId())
                this.clientArrayList.remove(b);
        }
        this.WriteObjectToFile();
    }

    public Client search_client(int clientid) {
        for(Client a:this.clientArrayList)
        {
            if(clientid == a.getClientId()) {
                this.WriteObjectToFile();
                return a;
            }
        }
        return null;
    }

    public void deposit_money(int sum,int accountid) {
        this.search_client(accountid).depositMoney(sum,accountid);
        WriteObjectToFile();

    }

    public void WriteObjectToFile() {

        try {

            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            Client[] clientToWrite = clientArrayList.toArray(new Client[clientArrayList.size()]);
            objectOut.writeObject(clientToWrite);
            objectOut.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void LoadFromFile() {

        try {
            FileInputStream fis = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Client[] clientToRead = (Client[])ois.readObject();
            clientArrayList.addAll(Arrays.asList(clientToRead));
            ois.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String string=new String();
        for (Client c:this.clientArrayList) {
            string+=c.toString()+" ";
        }
        return string;
    }
}
