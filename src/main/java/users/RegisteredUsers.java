package users;

public interface RegisteredUsers {
	void add (User user) throws AlreadyRegisteredUserException;

    void addFollower(User followed, User follower);
}
