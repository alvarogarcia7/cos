package users;


import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


public class RegistryShould {
	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();
	private RegisteredUsers registeredUsers;
	private Registry registry;
	public static final String USER_NAME = "user_name";
	public static final User USER = User.from(USER_NAME);

	@Before
	public void setUp () throws Exception {
		registeredUsers = context.mock(RegisteredUsers.class);
		registry = new Registry(registeredUsers);
	}

	@Test
	public void register_a_user () throws AlreadyRegisteredUserException {

		context.checking(new Expectations() {{
			oneOf (registeredUsers).add(USER);
			allowing(registeredUsers);
		}});

		registry.register(USER_NAME);
	}

	@Test(expected = AlreadyRegisteredUserException.class)
	public void not_register_an_already_registered_user () throws AlreadyRegisteredUserException {

		context.checking(new Expectations() {{
			exactly(1).of(registeredUsers).add(USER);
			exactly(1).of(registeredUsers).add(USER); will(throwException(new AlreadyRegisteredUserException()));
			allowing(registeredUsers);
		}});

		registry.register(USER_NAME);
		registry.register(USER_NAME);
	}

}
