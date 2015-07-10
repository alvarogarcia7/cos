package registration.integration_tests;

import users.RegistryResultListener;

public class InMemoryRegistryResult implements RegistryResultListener {
	private String lastResult;

	@Override
	public void alreadyRegistered (final String userName) {
		lastResult = "Cannot register user " + userName + " more than once";
	}

	@Override
	public void successfullyRegistered (final String userName) {
		lastResult = "Successfully registered " + userName;
	}

	public String asText () {
		return lastResult;
	}
}
