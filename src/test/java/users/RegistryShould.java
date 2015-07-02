package users;


import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;


public class RegistryShould {
	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();

	@Test
	public void register_a_user () throws AlreadyRegisteredUserException {
		final RegisteredUsers registeredUsers = context.mock(RegisteredUsers.class);
		final Registry registry = new Registry(registeredUsers);
		final String userName = "user_name";

		context.checking(new Expectations() {{
			oneOf (registeredUsers).add(new User(userName));
		}});

		registry.register(userName);
	}

	@Test(expected = AlreadyRegisteredUserException.class)
	public void not_register_an_already_registered_user () throws AlreadyRegisteredUserException {
		final RegisteredUsers registeredUsers = context.mock(RegisteredUsers.class);
		final Registry registry = new Registry(registeredUsers);
		final String userName = "user_name";

		context.checking(new Expectations() {{
			exactly(1).of(registeredUsers).add(new User(userName));
			exactly(1).of(registeredUsers).add(new User(userName)); will(throwException(new AlreadyRegisteredUserException()));

		}});

		registry.register(userName);
		registry.register(userName);
	}

}
