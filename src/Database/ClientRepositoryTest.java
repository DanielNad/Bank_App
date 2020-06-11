package Database;

import Model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientRepositoryTest {

    static ClientRepository clientRepository = new ClientRepository();

    @Test
    void validateClientId() {
        assertEquals(true,clientRepository.validateClientId(205635170));
        assertEquals(false,clientRepository.validateClientId(333344555));
    }

    @Test
    void validateClientUser() {
        assertEquals(true,clientRepository.validateClientUser(new User("daniel","1")));
        assertEquals(false,clientRepository.validateClientUser(new User("xxxx","xxxx")));
    }

    @Test
    void validateClientUsername() {
        assertEquals(true,clientRepository.validateClientUsername("daniel"));
        assertEquals(false,clientRepository.validateClientUsername("xxxx"));
    }
}