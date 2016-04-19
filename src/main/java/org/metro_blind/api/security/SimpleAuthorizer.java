package org.metro_blind.api.security;

import org.metro_blind.api.model.User;
import io.dropwizard.auth.Authorizer;

public class SimpleAuthorizer implements Authorizer<User> {
    @Override
    public boolean authorize(User user, String role) {
	return true;
	/*
        if(user.getRoles() != null && user.getRoles().contains(role)) {
            return true;
        }
        return false;
	*/
    }
}
