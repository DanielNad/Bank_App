import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class ListClient
{
    ArrayList<BClient> clientArrayList;
    private static final String filepath="src/Files/Client.det";

    public ListClient() {
        this.clientArrayList = new ArrayList<BClient>();
    }

    public ArrayList<BClient> getClientArrayList() {
        return clientArrayList;
    }

    public void add_to_list(BClient client){
        this.clientArrayList.add(client);
    }

    public void remove_banker(int clientid){
        for (BClient b:this.clientArrayList) {
            if(clientid==b.get_client_Id())
                this.clientArrayList.remove(b);
        }
        this.WriteObjectToFile();
    }

    public BClient search_client(int clientid) {
        for(BClient a:this.clientArrayList)
        {
            if(clientid == a.get_client_Id()) {
                this.WriteObjectToFile();
                return a;
            }
        }
        return null;
    }

    public void deposit_money(int sum,int accountid) {
        this.search_client(accountid).deposit_money(sum,accountid);
        WriteObjectToFile();

    }

    public void WriteObjectToFile() {

        try {

            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            BClient[] clientToWrite = clientArrayList.toArray(new BClient[clientArrayList.size()]);
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
            BClient[] clientToRead = (BClient [])ois.readObject();
            clientArrayList.addAll(Arrays.asList(clientToRead));
            ois.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String string=new String();
        for (BClient c:this.clientArrayList) {
            string+=c.toString()+" ";
        }
        return string;
    }
}
