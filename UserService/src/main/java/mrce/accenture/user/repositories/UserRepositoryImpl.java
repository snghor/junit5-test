package mrce.accenture.user.repositories;

import mrce.accenture.user.modal.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {

    Map<Long, User> users = new HashMap<>();
    @Override
    public boolean save(User user) {
        users.put(user.getId(), user);
        return false;
    }
}
