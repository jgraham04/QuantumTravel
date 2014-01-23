/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sonic_000
 */
@Entity
@Table(name = "waypoint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Waypoint.findAll", query = "SELECT w FROM Waypoint w"),
    @NamedQuery(name = "Waypoint.findById", query = "SELECT w FROM Waypoint w WHERE w.id = :id"),
    @NamedQuery(name = "Waypoint.findByName", query = "SELECT w FROM Waypoint w WHERE w.name = :name"),
    @NamedQuery(name = "Waypoint.findByLatitude", query = "SELECT w FROM Waypoint w WHERE w.latitude = :latitude"),
    @NamedQuery(name = "Waypoint.findByLongitude", query = "SELECT w FROM Waypoint w WHERE w.longitude = :longitude"),
    @NamedQuery(name = "Waypoint.findByTripId", query = "SELECT w FROM Waypoint w WHERE w.tripId = :tripId")})
public class Waypoint implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude")
    private double latitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude")
    private double longitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tripId")
    private int tripId;

    public Waypoint() {
    }

    public Waypoint(Integer id) {
        this.id = id;
    }

    public Waypoint(Integer id, double latitude, double longitude, int tripId) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tripId = tripId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Waypoint)) {
            return false;
        }
        Waypoint other = (Waypoint) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Waypoint[ id=" + id + " ]";
    }
    
}
