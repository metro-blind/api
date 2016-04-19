package org.metro_blind.api.security;

import org.metro_blind.api.model.User;
import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class SimpleAuthenticator implements Authenticator<BasicCredentials, User> {
    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
	if ("secret".equals(credentials.getPassword())) {
	    return Optional.of(new User(credentials.getUsername(), credentials.getPassword()));
	}
	return Optional.absent();
    }
}
