import Model.Banker;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class ListBanker
{
    private static final String filepath="src/Files/Bankers.det";
    private ArrayList<Banker> bankerList;

    public ListBanker() {
        this.bankerList = new ArrayList<Banker>();
    }

    public ArrayList<Banker> get_banker_list() {
        return bankerList;
    }

    public void add_banker_to_list(Banker banker){
        this.bankerList.add(banker);
    }

    public void remove_banker(int bankerid){
        for (Banker b:this.bankerList) {
            if(bankerid==b.getBankerId())
                this.bankerList.remove(b);
        }
    }

    public Banker search_banker(int bankerid) {
        for(Banker a:this.bankerList)
        {
            if(bankerid == a.getBankerId())
                return a;
        }
        return null;
    }

    public void WriteObjectToFile() {

        try {

            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            Banker[] bankersToWrite = bankerList.toArray(new Banker[bankerList.size()]);
            objectOut.writeObject(bankersToWrite);
            objectOut.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String string=new String();
        for (Banker c:this.bankerList) {
            string+=c.toString()+" ";
        }
        return string;
    }

    public void LoadFromFile() {

        try {
            FileInputStream fis = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Banker[] bankersToRead = (Banker[])ois.readObject();
            bankerList.addAll(Arrays.asList(bankersToRead));
            ois.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
