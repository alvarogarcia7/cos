package users;


public class Registry {
	private final RegisteredUsers registeredUsers;
    private final RegistryErrorsListener errorListener;

    public Registry(RegisteredUsers registeredUsers, RegistryErrorsListener errorListener) {
		this.registeredUsers = registeredUsers;
        this.errorListener = errorListener;
    }

	public void register (final String userName)  {
        try {
            registeredUsers.add(new User(userName));
        } catch (AlreadyRegisteredUserException e) {
            errorListener.alreadyRegisteredUser(userName);
        }
	}
}
