package org.metro_blind.api;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.metro_blind.api.resources.MetroBlindResource;
import org.metro_blind.api.health.TemplateHealthCheck;
import org.metro_blind.api.security.SimpleAuthenticator;
import org.metro_blind.api.security.SimpleAuthorizer;
import org.metro_blind.api.model.User;

public class MetroBlindApplication extends Application<MetroBlindConfiguration> {
    public static void main(String[] args) throws Exception {
	new MetroBlindApplication().run(args);
    }

    @Override
    public String getName() {
	return "metro-blind";
    }

    @Override
    public void initialize(Bootstrap<MetroBlindConfiguration> bootstrap) {
	// nothing to do yet
    }

    @Override
    public void run(MetroBlindConfiguration configuration,
		    Environment environment) {
	// nothing to do yet
	final MetroBlindResource resource = new MetroBlindResource(
								   configuration.getTemplate(),
								   configuration.getDefaultName()
								   );
	final TemplateHealthCheck healthCheck =
	    new TemplateHealthCheck(configuration.getTemplate());
	environment.healthChecks().register("template", healthCheck);
	environment.jersey().register(resource);

	environment.jersey().register(new AuthDynamicFeature
				      (
				       new BasicCredentialAuthFilter.Builder<User>()
				       .setAuthenticator(new SimpleAuthenticator())
				       .setAuthorizer(new SimpleAuthorizer())
				       .setRealm("SUPER SECRET STUFF")
				       .buildAuthFilter()
				       )
				      );
	environment.jersey().register(RolesAllowedDynamicFeature.class);
	//If you want to use @Auth to inject a custom Principal type into your resource
	environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }
}
