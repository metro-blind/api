package org.metro_blind.api;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

import org.metro_blind.api.model.*;
import org.metro_blind.api.resources.*;

public class MetroBlindApplication extends Application<MetroBlindConfiguration> {
    public static void main(String[] args) throws Exception {
	new MetroBlindApplication().run(args);
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
	final StationDAO stationDao = new StationDAO(hibernateBundle.getSessionFactory());
	environment.jersey().register(new StationResource(stationDao));
	final ItineraryDAO itineraryDao = new ItineraryDAO(hibernateBundle.getSessionFactory());
	environment.jersey().register(new ItineraryResource(itineraryDao));
    }
}
