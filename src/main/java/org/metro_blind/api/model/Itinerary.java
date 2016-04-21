package org.metro_blind.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import java.util.Objects;

@Entity
@Table(name = "itinerary")
@NamedQueries({
        @NamedQuery(
                name = "org.metro_blind.api.model.Itinerary.findByBeginEndId",
                query = "SELECT i FROM Itinerary i WHERE i.stationBegin = :stationIdBegin AND i.stationEnd = :stationIdEnd"
        )
})
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "station_id_begin", nullable = false)
    private Station stationBegin;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "station_id_by", nullable = true)
    private Station stationBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "station_id_end", nullable = false)
    private Station stationEnd;

    public Itinerary() {}

    public Itinerary(String text, Station stationBegin, Station stationBy, Station stationEnd) {
        this.text = text;
	this.stationBegin = stationBegin;
	this.stationBy = stationBy;
	this.stationEnd = stationEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Station getStationBegin() {
	return stationBegin;
    }

    public void setStationBegin(Station stationBegin) {
	this.stationBegin = stationBegin;
    }

    public Station getStationBy() {
	return stationBy;
    }

    public void setStationBy(Station stationBy) {
	this.stationBy = stationBy;
    }

    public Station getStationEnd() {
	return stationEnd;
    }

    public void setStationEnd(Station stationEnd) {
	this.stationEnd = stationEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Itinerary)) {
            return false;
        }

        final Itinerary that = (Itinerary) o;

        return Objects.equals(this.id, that.id) &&
	    Objects.equals(this.text, that.text) &&
	    Objects.equals(this.stationBegin, that.stationBegin) &&
	    Objects.equals(this.stationBy, that.stationBy) &&
	Objects.equals(this.stationEnd, that.stationEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, stationBegin, stationBy, stationEnd);
    }
}
