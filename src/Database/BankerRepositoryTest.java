package Database;

import Model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankerRepositoryTest {

    static BankerRepository bankerRepository = new BankerRepository();

    @Test
    void validateBankerUser() {
        assertEquals(true,bankerRepository.validateBankerUser(new User("admin","admin")));
        assertEquals(false,bankerRepository.validateBankerUser(new User("xxxx","xxxx")));
    }

    @Test
    void validateBankerId() {
        assertEquals(true,bankerRepository.validateBankerId(0));
        assertEquals(false,bankerRepository.validateBankerId(1));
    }

    @Test
    void validateBankerUsername() {
        assertEquals(true,bankerRepository.validateBankerUsername("admin"));
        assertEquals(false,bankerRepository.validateBankerUsername("xxxx"));
    }

    @Test
    void validateBankMangaer() {
        assertEquals(true,bankerRepository.validateBankMangaer("admin","admin"));
        //DO NOT DELETE TEST FROM DB
        assertEquals(false,bankerRepository.validateBankMangaer("test","test"));
        assertEquals(false,bankerRepository.validateBankMangaer("xxxx","xxxx"));
    }
}