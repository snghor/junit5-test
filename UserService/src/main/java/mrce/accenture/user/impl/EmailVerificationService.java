package mrce.accenture.user.impl;

import mrce.accenture.user.modal.User;

public interface EmailVerificationService {
    void scheduleEmailConfirmation(User user);
}
