package users;

public interface RegisteredUsers {
	void add (User user) throws AlreadyRegisteredUserException;
}
