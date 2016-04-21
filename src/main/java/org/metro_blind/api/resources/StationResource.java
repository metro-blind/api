package org.metro_blind.api.resources;

import com.google.common.base.Optional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import io.dropwizard.hibernate.UnitOfWork;

import org.metro_blind.api.model.Station;
import org.metro_blind.api.model.StationDAO;

@Path("/station")
@Produces(MediaType.APPLICATION_JSON)
public class StationResource {
    private final StationDAO stationDao;

    public StationResource(StationDAO stationDao) {
	this.stationDao = stationDao;
    }

    @GET
    @UnitOfWork
    public List<Station> listStation() {
	return this.stationDao.findAll();
    }
}
