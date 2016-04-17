package org.metro_blind.api.resources;

import org.metro_blind.api.model.User;
import io.dropwizard.auth.Auth;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.metro_blind.api.model.UserDAO;

@Path("/user")
@Produces(MediaType.TEXT_PLAIN)
public class UserResource {
    private UserDAO userDao;

    public UserResource(UserDAO userData) {
	this.userDao = userDao;
    }
    
    @PermitAll
    @GET
    public String showSecret(@Auth User user) {
        return String.format("Hey there, %s. You know the secret! %d", user.getName(), user.getId());
    }

    @RolesAllowed("ADMIN")
    @GET
    @Path("admin")
    public String showAdminSecret(@Auth User user) {
        return String.format("Hey there, %s. It looks like you are an admin. %d", user.getName(), user.getId());
    }
}
