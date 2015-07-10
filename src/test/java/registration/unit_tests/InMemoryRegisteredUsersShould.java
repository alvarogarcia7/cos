package registration.unit_tests;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import users.AlreadyRegisteredUserException;
import users.InMemoryRegisteredUsers;
import users.RegistryResultListener;
import users.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class InMemoryRegisteredUsersShould {

    private InMemoryRegisteredUsers registeredUsers;
    private User user;
    private RegistryResultListener resultListener;

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Before
    public void setUp() {
        resultListener = context.mock(RegistryResultListener.class);
        user = new User("user_name");
        registeredUsers = new InMemoryRegisteredUsers();
    }

    @Test
    public void adds_a_user() throws AlreadyRegisteredUserException {
        context.checking(new Expectations() {{
            oneOf(resultListener).successfullyRegistered("user_name");
        }});

        registeredUsers.add(user, resultListener);

        assertThat(registeredUsers.contains(user), is(true));
    }

    @Test
    public void not_adds_an_already_registered_user() throws AlreadyRegisteredUserException {
        context.checking(new Expectations() {{
            oneOf(resultListener).successfullyRegistered("user_name");
            oneOf(resultListener).alreadyRegistered("user_name");
        }});

        registeredUsers.add(user, resultListener);
        registeredUsers.add(user, resultListener);
    }
}
