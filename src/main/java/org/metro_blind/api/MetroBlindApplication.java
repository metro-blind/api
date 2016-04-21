package org.metro_blind.api;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

import org.metro_blind.api.model.*;
import org.metro_blind.api.resources.*;

import java.util.EnumSet;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;

public class MetroBlindApplication extends Application<MetroBlindConfiguration> {
    public static void main(String[] args) throws Exception {
	new MetroBlindApplication().run(args);
    }

    private void configureCors(Environment environment) {
	Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
	filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
	filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
	filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
	filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
	filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
	filter.setInitParameter("allowCredentials", "true");
    }

    private final HibernateBundle<MetroBlindConfiguration> hibernateBundle =
	new HibernateBundle<MetroBlindConfiguration>(Station.class, Itinerary.class) {
	    @Override
	    public DataSourceFactory getDataSourceFactory(MetroBlindConfiguration configuration) {
		return configuration.getDataSourceFactory();
	    }
	};
    
    @Override
    public String getName() {
	return "metro-blind";
    }

    @Override
    public void initialize(Bootstrap<MetroBlindConfiguration> bootstrap) {
	bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(MetroBlindConfiguration config,
		    Environment environment) throws ClassNotFoundException {
	this.configureCors(environment);
	final StationDAO stationDao = new StationDAO(hibernateBundle.getSessionFactory());
	environment.jersey().register(new StationResource(stationDao));
	final ItineraryDAO itineraryDao = new ItineraryDAO(hibernateBundle.getSessionFactory());
	environment.jersey().register(new ItineraryResource(itineraryDao));
    }
}
