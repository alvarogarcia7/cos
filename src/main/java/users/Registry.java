package users;


public class Registry {
	private final RegisteredUsers registeredUsers;
    private final RegistryResultListener resultListener;

    public Registry(RegisteredUsers registeredUsers, RegistryResultListener resultListener) {
		this.registeredUsers = registeredUsers;
        this.resultListener = resultListener;
    }

	public void register (final String userName)  {
        try {
            registeredUsers.add(new User(userName));
            resultListener.userSuccessfullyRegistered(userName);
        } catch (AlreadyRegisteredUserException e) {
            resultListener.alreadyRegisteredUser(userName);
        }
	}
}
