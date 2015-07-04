package users;


public class Registry {
	private final RegisteredUsers registeredUsers;

	public Registry (final RegisteredUsers registeredUsers) {
		this.registeredUsers = registeredUsers;
	}

	public void register (final String userName) throws AlreadyRegisteredUserException {
		final User user = from(userName);
		registeredUsers.isValid(user);
		registeredUsers.add(user);
	}

	private static User from (final String userName) {
		return new User(userName);
	}
}
