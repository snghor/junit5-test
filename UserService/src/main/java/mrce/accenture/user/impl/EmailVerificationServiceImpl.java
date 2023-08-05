package mrce.accenture.user.impl;

import mrce.accenture.user.modal.User;

public class EmailVerificationServiceImpl implements EmailVerificationService {
    @Override
    public void scheduleEmailConfirmation(User user) {
        // Put user details into email queue
        System.out.println("real send email is call with mockito");
    }
}
