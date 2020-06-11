package Model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

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

    @BeforeAll static void creation() {
        account1 = client1.newAccount();
        account2 = client2.newAccount();
        client2.depositMoney(1000,account2.getAccountId());
    }

    @Test
    void depositMoney() {
        client1.depositMoney(1000,account1.getAccountId());
        assertEquals(2000,account1.getBalance());
    }

    @Test
    void withdrawCash() {
        client1.withdrawCash(1000,account1.getAccountId());
        assertEquals(1000,account1.getBalance());
    }

    @Test
    void transferMoney() {
        client2.transferMoney(1000,account2.getAccountId(),account1);
        assertEquals(1000,account1.getBalance());
    }
}