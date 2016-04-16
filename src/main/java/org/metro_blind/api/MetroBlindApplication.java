package org.metro_blind.api;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.metro_blind.api.resources.MetroBlindResource;
import org.metro_blind.api.health.TemplateHealthCheck;

public class MetroBlindApplication extends Application<MetroBlindConfiguration> {
    public static void main(String[] args) throws Exception {
	new MetroBlindApplication().run(args);
    }

    @Override
    public String getName() {
	return "hello-world";
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
    }

}
