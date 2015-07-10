package registry.integration_tests;

import org.junit.Before;
import org.junit.Test;
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
}
