package Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankerTest {

    static Client client1 = Client.builder()
            .clientId(206920647).address("Ramat-Gan")
            .fname("Sun")
            .lname("Shavit")
            .user("sun","sun")
            .numberOfAccount(0)
            .income(1000)
            .myAccounts().build();
    static Client client2 = Client.builder()
            .clientId(205635170).address("Bat-Yam")
            .fname("Daniel")
            .lname("Nadav")
            .user("daniel","daniel")
            .numberOfAccount(0)
            .income(1000)
            .myAccounts().build();
    static Account account1;
    static Account account2;
    static Banker banker = new Banker("banker","banker","banker",new User("banker","banker"),111111111);

    @BeforeAll
    static void create(){
        account1 = client1.newAccount();
        account2 = client2.newAccount();
        client2.depositMoney(1000,account2.getAccountId());
    }

    @Test
    void editClientInfo() {
        banker.editClientInfo(client1,"sun","shavit","ramat-gan",0,"sun");
        assertEquals("sun",client1.getFirstName());
        assertEquals("shavit",client1.getLastName());
        assertEquals("ramat-gan",client1.getAddress());
        assertEquals(0,client1.getIncome());
        assertEquals("sun",client1.getMyUser().getPassword());
    }

    @Test
    void transferClientToClient() {
        banker.transferClientToClient(account2,account1,500);
        assertEquals(1000,account1.getBalance());
        assertEquals(500,account2.getBalance());
    }

    @Test
    void depositClientMoney() {
        banker.depositClientMoney(client1,account1,500);
        assertEquals(500,account1.getBalance());
    }

    @Test
    void withdrawClientCash() {
        banker.withdrawClientCash(client1,account1,1000);
        assertEquals(0,account1.getBalance());
    }
}