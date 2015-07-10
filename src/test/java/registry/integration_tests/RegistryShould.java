package registry.integration_tests;

import org.junit.Before;
import org.junit.Test;
import users.AlreadyRegisteredUserException;
import users.InMemoryRegisteredUsers;
import users.Registry;
import users.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RegistryShould {

	public static final String USER_NAME = "a_user";
	private InMemoryRegisteredUsers registeredUsers;
	private InMemoryRegistryResult result;
	private Registry registry;

	@Before
	public void setUp () {
		registeredUsers = new InMemoryRegisteredUsers();
		result = new InMemoryRegistryResult();
		registry = new Registry(registeredUsers, result);
	}

	@Test
	public void register_a_user () {
		registry.register(USER_NAME);

		assertThat(this.registeredUsers.contains(new User(USER_NAME)), is(true));
		assertThat(result.asText(), is("Successfully registered a_user"));
	}

	@Test
	public void complain_when_it_is_not_allowed_to_register_a_user () throws AlreadyRegisteredUserException {
		givenAlreadyRegistered(USER_NAME);

		registry.register(USER_NAME);

		assertThat(result.asText(), is("Cannot register user a_user more than once"));
	}

	private void givenAlreadyRegistered (final String userName) throws AlreadyRegisteredUserException {
		registeredUsers.add(new User(userName));
	}
}
