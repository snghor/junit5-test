package mrce.accenture.user.impl;

import mrce.accenture.user.exceptions.EmailServiceException;
import mrce.accenture.user.exceptions.UserServiceException;
import mrce.accenture.user.modal.User;
import mrce.accenture.user.repositories.UserRepository;

public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    EmailVerificationService emailVerificationService;

    public UserServiceImpl(UserRepository userRepository, EmailVerificationService emailVerificationService) {
        this.userRepository = userRepository;
        this.emailVerificationService = emailVerificationService;
    }

    @Override
    public User createUser(User user) {
        boolean isUserCreated;
        try{
            isUserCreated = userRepository.save(user);
        } catch (RuntimeException e) {
            throw new UserServiceException("throw new exception");
        }

        try{
            emailVerificationService.scheduleEmailConfirmation(user);
        } catch (RuntimeException e) {
            throw new EmailServiceException("throw new exception");
        }
        if(!isUserCreated) throw new UserServiceException("Could not create user");
        return user;
    }
}
