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

	@Before
	public void setUp () throws Exception {
		registeredUsers = context.mock(RegisteredUsers.class);
		registry = new Registry(registeredUsers);
	}

	@Test
	public void register_a_user () throws AlreadyRegisteredUserException {
		final String userName = "user_name";

		context.checking(new Expectations() {{
			oneOf (registeredUsers).add(new User(userName));
			allowing(registeredUsers);
		}});

		registry.register(userName);
	}

	@Test(expected = AlreadyRegisteredUserException.class)
	public void not_register_an_already_registered_user () throws AlreadyRegisteredUserException {
		final String userName = "user_name";

		context.checking(new Expectations() {{
			exactly(1).of(registeredUsers).add(new User(userName));
			exactly(1).of(registeredUsers).add(new User(userName)); will(throwException(new AlreadyRegisteredUserException()));
			allowing(registeredUsers);
		}});

		registry.register(userName);
		registry.register(userName);
	}

}
