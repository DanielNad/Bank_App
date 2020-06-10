package Model;

public class BankManager extends Banker {

    public BankManager(String last_name, String first_name, String address, User my_user, int banker_id) {
        super(last_name, first_name, address, my_user, banker_id);
    }

    public Banker createNewBanker(String last_name, String first_name, String address, String username,String password, int banker_id) {
       return new Banker(last_name,first_name,address,new User(username,password),banker_id);
    }

    @Override
    public String toString() {
        return "BankerManager{" +
                "my_user=" + this.getMyUser() +
                ", banker_id=" + this.getBankerId() +
                " first name="+this.getFirstName()+
                " last name="+this.getLastName()+
                '}';
    }
}