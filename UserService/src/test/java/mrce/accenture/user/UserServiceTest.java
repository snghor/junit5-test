package mrce.accenture.user;

import mrce.accenture.user.exceptions.EmailServiceException;
import mrce.accenture.user.exceptions.UserServiceException;
import mrce.accenture.user.impl.EmailVerificationServiceImpl;
import mrce.accenture.user.impl.UserServiceImpl;
import mrce.accenture.user.modal.User;
import mrce.accenture.user.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests user service")
class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;
    User user;
    @Mock
    UserRepository userRepository;
    @Mock
    EmailVerificationServiceImpl emailVerificationService;

    @BeforeEach
    void init() {
    }

    @DisplayName("create user")
    @Test
    void test_create_user_when_user_details_provider_should_return_new_valid_user() {
        // Arrange -> Given
        when(userRepository.save(any(User.class))).thenReturn(Boolean.TRUE);
        user = new User(1L, "FirstName", "LastName", "email", "password");

        // Act -> When
        user = userService.createUser(user);

        // Assert -> Then
        Assertions.assertEquals(1L, user.getId());
        Assertions.assertEquals("password", user.getPassword());
        Assertions.assertEquals("email", user.getEmail());
        Assertions.assertEquals("LastName", user.getLastName());
        Assertions.assertEquals("FirstName", user.getFirstName());

        verify(userRepository, timeout(1)).save(any(User.class));
    }

    @Test
    @DisplayName("throw user exception service")
    void test_create_user_when_save_user_throw_exception_should_throws_UserServiceException() {
        // Arrange => Given
        when(userRepository.save(any(User.class))).thenThrow(RuntimeException.class);
        user = new User(1L, "FirstName", "LastName", "email", "password");

        // Act & Assert
        Assertions.assertThrows(UserServiceException.class, ()-> user = userService.createUser(user)
        , "Should have thrown UserServiceException instead");

    }

    @Test
    @DisplayName("throw user service exception when send email")
    void test_create_user_when_email_notification_exception_should_throws_UserServiceException() {
        // Arrange => Given
        when(userRepository.save(any(User.class))).thenReturn(Boolean.TRUE);
        doThrow(EmailServiceException.class)
                .when(emailVerificationService)
                .scheduleEmailConfirmation(any(User.class));

        user = new User(1L, "FirstName", "LastName", "email", "password");

        // Act & Assert
        Assertions.assertThrows(EmailServiceException.class, ()->
            user = userService.createUser(user), "Should have thrown UserServiceException instead");

        verify(emailVerificationService, timeout(1)).scheduleEmailConfirmation(any(User.class));
    }

    @DisplayName("create user and send email")
    @Test
    void test_create_user_when_email_notification_is_mock_should_return_valid_user() {
        // Arrange => Given
        when(userRepository.save(any(User.class))).thenReturn(Boolean.TRUE);
        doNothing().when(emailVerificationService).scheduleEmailConfirmation(any(User.class));
        user = new User(1L, "FirstName", "LastName", "email", "password");

        // Act -> When
        user = userService.createUser(user);

        // Assert -> Then
        Assertions.assertEquals(1L, user.getId());
        verify(emailVerificationService, timeout(1)).scheduleEmailConfirmation(any(User.class));
        verify(userRepository, timeout(1)).save(any(User.class));
    }

    @Test
    @DisplayName("create user and send real email")
    void test_create_user_when_use_real_method_send_email_should_return_valid_user() {
        // Arrange => Given
        when(userRepository.save(any(User.class))).thenReturn(Boolean.TRUE);
        doCallRealMethod().when(emailVerificationService).scheduleEmailConfirmation(any(User.class));
        user = new User(1L, "FirstName", "LastName", "email", "password");

        // Act -> When
        user = userService.createUser(user);

        // Assert -> Then
        Assertions.assertEquals(1L, user.getId());
        verify(emailVerificationService, timeout(1)).scheduleEmailConfirmation(any(User.class));
        verify(userRepository, timeout(1)).save(any(User.class));
    }
}
