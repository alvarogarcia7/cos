package registration.unit_tests;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.Sequence;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import users.*;


public class RegistrationShould {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private RegisteredUsers registeredUsers;
    private RegistryResultListener resultListener;
    private Registration registration;
    private String userName;
    private User user;

    @Before
    public void setUp() {
        registeredUsers = context.mock(RegisteredUsers.class);
        resultListener = context.mock(RegistryResultListener.class);
        registration = new Registration(registeredUsers, resultListener);
        userName = "user_name";
        user = new User(userName);
    }

    @Test
    public void register_a_user() throws AlreadyRegisteredUserException {
        context.checking(new Expectations() {{
            oneOf(registeredUsers).add(user);

            oneOf(resultListener).successfullyRegistered(userName);
        }});

        registration.register(userName);
    }

    @Test
    public void not_register_an_already_registered_user() throws AlreadyRegisteredUserException {
        final Sequence registeredUsersAdditions = context.sequence("registeredUsersAdditions");
        context.checking(new Expectations() {{
            oneOf(registeredUsers).add(user); inSequence(registeredUsersAdditions);
            allowing(resultListener).successfullyRegistered(userName);

            oneOf(registeredUsers).add(user); inSequence(registeredUsersAdditions);
            will(throwException(new AlreadyRegisteredUserException()));

            oneOf(resultListener).alreadyRegistered(userName);
        }});

        registration.register(userName);
        registration.register(userName);
    }

}
