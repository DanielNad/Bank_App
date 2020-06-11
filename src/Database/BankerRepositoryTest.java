package Database;

import Model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankerRepositoryTest {

    static BankerRepository bankerRepository = new BankerRepository();

    @Test
    void validateBankerUser() {
        assertTrue(bankerRepository.validateBankerUser(new User("admin", "admin")));
        assertFalse(bankerRepository.validateBankerUser(new User("xxxx", "xxxx")));
    }

    @Test
    void validateBankerId() {
        assertTrue(bankerRepository.validateBankerId(0));
        assertFalse(bankerRepository.validateBankerId(1));
    }

    @Test
    void validateBankerUsername() {
        assertTrue(bankerRepository.validateBankerUsername("admin"));
        assertFalse(bankerRepository.validateBankerUsername("xxxx"));
    }

    @Test
    void validateBankMangaer() {
        assertTrue(bankerRepository.validateBankMangaer("admin", "admin"));
        //DO NOT DELETE TEST FROM DB
        assertFalse(bankerRepository.validateBankMangaer("test", "test"));
        assertFalse(bankerRepository.validateBankMangaer("xxxx", "xxxx"));
    }
}