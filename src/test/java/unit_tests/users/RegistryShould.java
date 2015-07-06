package unit_tests.users;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import users.*;


public class RegistryShould {
	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();

	@Test
	public void register_a_user () throws AlreadyRegisteredUserException {
		final RegisteredUsers registeredUsers = context.mock(RegisteredUsers.class);
        final RegistryErrorsListener errorListener = context.mock(RegistryErrorsListener.class);
		final Registry registry = new Registry(registeredUsers, errorListener);
		final String userName = "user_name";

		context.checking(new Expectations() {{
			oneOf (registeredUsers).add(new User(userName));
		}});

		registry.register(userName);
	}

	@Test
	public void not_register_an_already_registered_user () throws AlreadyRegisteredUserException {
		final RegisteredUsers registeredUsers = context.mock(RegisteredUsers.class);
        final RegistryErrorsListener errorListener = context.mock(RegistryErrorsListener.class);
        final Registry registry = new Registry(registeredUsers, errorListener);
		final String userName = "user_name";
        final User user = new User(userName);

		context.checking(new Expectations() {{
			exactly(1).of(registeredUsers).add(user);
			exactly(1).of(registeredUsers).add(user);
            will(throwException(new AlreadyRegisteredUserException()));

            exactly(1).of(errorListener).alreadyRegisteredUser(userName);
		}});

		registry.register(userName);
		registry.register(userName);
	}

}
