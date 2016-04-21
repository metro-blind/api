package org.metro_blind.api.model;

import org.metro_blind.api.model.Station;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class ItineraryDAO extends AbstractDAO<Itinerary> {
    public ItineraryDAO(SessionFactory factory) {
        super(factory);
    }

    public Itinerary create(Itinerary person) {
        return persist(person);
    }

    public List<Itinerary> findByBeginEndId(int stationIdBegin, int stationIdEnd) {
	return list(namedQuery("org.metro_blind.api.model.Itinerary.findByBeginEndId")
		    .setInteger("stationIdBegin", stationIdBegin)
		    .setInteger("stationIdEnd", stationIdEnd));
    }
}
