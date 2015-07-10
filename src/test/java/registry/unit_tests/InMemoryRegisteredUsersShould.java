package registry.unit_tests;

import org.junit.Before;
import org.junit.Test;
import users.AlreadyRegisteredUserException;
import users.InMemoryRegisteredUsers;
import users.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class InMemoryRegisteredUsersShould {

    private InMemoryRegisteredUsers registeredUsers;
    private User user;

    @Before
    public void setUp() {
        user = new User("user_name");
        registeredUsers = new InMemoryRegisteredUsers();
    }

    @Test
    public void adds_a_user() throws AlreadyRegisteredUserException {
        registeredUsers.add(user);

        assertThat(registeredUsers.contains(user), is(true));
    }

    @Test(expected=AlreadyRegisteredUserException.class)
    public void not_adds_an_already_registered_user() throws AlreadyRegisteredUserException {
        registeredUsers.add(user);
        registeredUsers.add(user);
    }
}
