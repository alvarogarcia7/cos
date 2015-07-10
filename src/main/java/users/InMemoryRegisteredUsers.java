package users;

import java.util.HashSet;
import java.util.Set;

public class InMemoryRegisteredUsers implements RegisteredUsers {
    private final Set<User> users;

    public InMemoryRegisteredUsers() {
        users = new HashSet<User>();
    }

    public void add (User user, final RegistryResultListener resultListener) {
        if(contains(user)) {
            resultListener.alreadyRegistered(user.name());
            return;
        }

        users.add(user);
        resultListener.successfullyRegistered(user.name());
    }

    public Boolean contains(User user) {
        return users.contains(user);
    }
}
