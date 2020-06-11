package Database;

import Model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientRepositoryTest {

    static ClientRepository clientRepository = new ClientRepository();

    @Test
    void validateClientId() {
        assertTrue(clientRepository.validateClientId(205635170));
        assertFalse(clientRepository.validateClientId(333344555));
    }

    @Test
    void validateClientUser() {
        assertTrue(clientRepository.validateClientUser(new User("daniel", "1")));
        assertFalse(clientRepository.validateClientUser(new User("xxxx", "xxxx")));
    }

    @Test
    void validateClientUsername() {
        assertTrue(clientRepository.validateClientUsername("daniel"));
        assertFalse(clientRepository.validateClientUsername("xxxx"));
    }
}