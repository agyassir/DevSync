import com.example.decs.Entity.User;
import com.example.decs.Repository.Implementation.UserRepositoryImpl;
import com.example.decs.Repository.UserRepository;
import com.example.decs.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService();
    } // Injecting the mock

    @Test
    public void testSignup_Success() {
        // Arrange
        String username = "testUser1";
        String firstname = "John";
        String lastname = "Doe";
        String email = "test1@example.com";
        String password = "password";
        boolean isChef = true;

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(userRepository.findByUsername(username)).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(new User());

        // Act
        User result = userService.signup(username, firstname, lastname, email, password, isChef);

        // Assert
        assertNotNull(result);
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testSignup_EmailAlreadyInUse() {
        // Arrange
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User()));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userService.signup("testUser", "John", "Doe", email, "password", true);
        });
    }

    @Test
    public void testSignup_UsernameAlreadyInUse() {
        // Arrange
        String username = "testUser";
        when(userRepository.findByUsername(username)).thenReturn(new User());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userService.signup(username, "John", "Doe", "test@example.com", "password", true);
        });
    }

    @Test
    public void testLogin_Success() {
        // Arrange
        String username = "testUser";
        String password = "password";
        User user = new User();
        user.setPassword(userService.hashPassword(password));

        when(userRepository.findByUsername(username)).thenReturn(user);

        // Act
        boolean result = userService.login(username, password);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testLogin_Failure() {
        // Arrange
        String username = "testUser";
        String password = "wrongPassword";

        when(userRepository.findByUsername(username)).thenReturn(null);

        // Act
        boolean result = userService.login(username, password);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testFindUserByUsername() {
        // Arrange
        String username = "testUser";
        User user = new User();
        user.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(user);

        // Act
        User result = userService.findUserbyUsername(username);

        // Assert
        assertEquals(username, result.getUsername());
    }



    @Test
    public void testIncrementUserCoinsDaily() {
        // Arrange
        User user1 = new User();
        user1.setCoins(5);
        User user2 = new User();
        user2.setCoins(10);

        when(userRepository.getAll()).thenReturn(Arrays.asList(user1, user2));

        // Act
        userService.incrementUserCoinsDaily();

        // Assert
        assertEquals(7, user1.getCoins());
        assertEquals(12, user2.getCoins());
        verify(userRepository, times(2)).update(any(User.class));
    }
}