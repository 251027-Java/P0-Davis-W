import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.util.Date;

import org.example.User;
import org.example.Repository.IUserRepository;
import org.example.Service.UserService;
import org.junit.jupiter.api.Assertions;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    // Field

    @InjectMocks
    private UserService user;

    @Mock
    private IUserRepository mockRepo;

    @Test
    public void testCreateUserSuccess(){
        when(mockRepo.getUserById(1)).thenReturn(null);
        
        User createUser = user.createUser(1, "Walker", "Davis", "walkDav@test.com");

        Assertions.assertNotNull(createUser);
        Assertions.assertEquals(1, createUser.getID());
        Assertions.assertEquals("Walker", createUser.getFirstName());
        Assertions.assertEquals("Davis", createUser.getLastName());
        Assertions.assertEquals("walkDav@test.com", createUser.getEmail());

        verify(mockRepo, times(1)).addUser(Mockito.any(User.class));
    }

    @Test
    public void testCreateUserFailsWhenIdExists(){
        User exists = new User(1, "Dave", "Smith", "davS@test.com", new Date());
        when(mockRepo.getUserById(1)).thenReturn(exists);

        User newUser = user.createUser(1,"Dave", "Smith", "davS@test.com");

        Assertions.assertNull(newUser);

        verify(mockRepo, never()).addUser(Mockito.any(User.class));
    }

    







}
