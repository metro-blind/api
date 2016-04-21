package org.metro_blind.api.resources;

import com.google.common.base.Optional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import io.dropwizard.hibernate.UnitOfWork;

import org.metro_blind.api.model.Itinerary;
import org.metro_blind.api.model.ItineraryDAO;

@Path("/itinerary")
@Produces(MediaType.APPLICATION_JSON)
public class ItineraryResource {
    private final ItineraryDAO itineraryDao;

    public ItineraryResource(ItineraryDAO itineraryDao) {
	this.itineraryDao = itineraryDao;
    }

    @GET
    @Path("{stationIdBegin}/{stationIdEnd}")
    @UnitOfWork
    public List<Itinerary> findItineraries(@PathParam("stationIdBegin") int stationBegin, @PathParam("stationIdEnd") int stationEnd) {
	return this.itineraryDao.findByBeginEndId(stationBegin, stationEnd);
    }
}
