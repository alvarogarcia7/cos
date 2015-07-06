package users;

public interface RegistryResultListener {
    void alreadyRegistered(String userName);

    void successfullyRegistered(String userName);
}
