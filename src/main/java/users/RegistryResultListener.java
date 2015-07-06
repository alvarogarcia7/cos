package users;

public interface RegistryResultListener {
    void alreadyRegisteredUser(String userName);

    void userSuccessfullyRegistered(String userName);
}
