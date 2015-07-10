package users;


public class Registration {
	private final RegisteredUsers registeredUsers;
    private final RegistryResultListener resultListener;

    public Registration (RegisteredUsers registeredUsers, RegistryResultListener resultListener) {
		this.registeredUsers = registeredUsers;
        this.resultListener = resultListener;
    }

	public void register (final String userName)  {
        registeredUsers.add(new User(userName), resultListener);
	}
}
