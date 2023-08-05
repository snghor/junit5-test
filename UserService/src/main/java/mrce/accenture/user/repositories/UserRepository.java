package mrce.accenture.user.repositories;

import mrce.accenture.user.modal.User;

public interface UserRepository {
    boolean save(User user);
}
