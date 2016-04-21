package org.metro_blind.api.model;

import org.metro_blind.api.model.Station;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class StationDAO extends AbstractDAO<Station> {
    public StationDAO(SessionFactory factory) {
        super(factory);
    }

    public Station create(Station person) {
        return persist(person);
    }

    public List<Station> findAll() {
        return list(namedQuery("org.metro_blind.api.model.Station.findAll"));
    }

}
