package registry.integration_tests;

import users.RegistryResultListener;

public class InMemoryRegistryResult implements RegistryResultListener {
	private String lastResult;

	@Override
	public void alreadyRegistered (final String userName) {

	}

	@Override
	public void successfullyRegistered (final String userName) {
		lastResult = "Successfully registered " + userName;
	}

	public String asText () {
		return lastResult;
	}
}
