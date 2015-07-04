package users;

public interface RegisteredUsers {
	void add (User user) throws AlreadyRegisteredUserException;

	boolean isValid (User user);
}
