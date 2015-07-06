package users;

import java.util.HashSet;
import java.util.Set;

public class InMemoryRegisteredUsers implements RegisteredUsers {
    private final Set<User> users;

    public InMemoryRegisteredUsers() {
        users = new HashSet<User>();
    }

    public void add(User user) throws AlreadyRegisteredUserException {
        if(contains(user)) {
            throw new AlreadyRegisteredUserException();
        }

        users.add(user);
    }

    public Boolean contains(User user) {
        return users.contains(user);
    }
}
