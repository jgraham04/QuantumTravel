/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sonic_000
 */
@Entity
@Table(name = "trip_core")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TripCore.findAll", query = "SELECT t FROM TripCore t"),
    @NamedQuery(name = "TripCore.findById", query = "SELECT t FROM TripCore t WHERE t.id = :id"),
    @NamedQuery(name = "TripCore.findByName", query = "SELECT t FROM TripCore t WHERE t.name = :name"),
    @NamedQuery(name = "TripCore.findByStartDate", query = "SELECT t FROM TripCore t WHERE t.startDate = :startDate"),
    @NamedQuery(name = "TripCore.findByEndDate", query = "SELECT t FROM TripCore t WHERE t.endDate = :endDate"),
    @NamedQuery(name = "TripCore.findByStartLatitude", query = "SELECT t FROM TripCore t WHERE t.startLatitude = :startLatitude"),
    @NamedQuery(name = "TripCore.findByStartLongitude", query = "SELECT t FROM TripCore t WHERE t.startLongitude = :startLongitude"),
    @NamedQuery(name = "TripCore.findByEndLatitude", query = "SELECT t FROM TripCore t WHERE t.endLatitude = :endLatitude"),
    @NamedQuery(name = "TripCore.findByEndLongitude", query = "SELECT t FROM TripCore t WHERE t.endLongitude = :endLongitude"),
    @NamedQuery(name = "TripCore.findByIsPublic", query = "SELECT t FROM TripCore t WHERE t.isPublic = :isPublic"),
    @NamedQuery(name = "TripCore.findByTripStatus", query = "SELECT t FROM TripCore t WHERE t.tripStatus = :tripStatus"),
    @NamedQuery(name = "TripCore.findByDisplayPhoto", query = "SELECT t FROM TripCore t WHERE t.displayPhoto = :displayPhoto"),
    @NamedQuery(name = "TripCore.findByUserId", query = "SELECT t FROM TripCore t WHERE t.userId = :userId"),
    @NamedQuery(name = "TripCore.findByCurrentLocation", query = "SELECT t FROM TripCore t WHERE t.currentLocation = :currentLocation"),
    @NamedQuery(name = "TripCore.findByDestination", query = "SELECT t FROM TripCore t WHERE t.destination = :destination"),
    @NamedQuery(name = "TripCore.findByStartLocation", query = "SELECT t FROM TripCore t WHERE t.startLocation = :startLocation")})
public class TripCore implements Serializable {
    @Column(name = "isRoundTrip")
    private Integer isRoundTrip;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_latitude")
    private double startLatitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_longitude")
    private double startLongitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_latitude")
    private double endLatitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_longitude")
    private double endLongitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isPublic")
    private int isPublic;
    @Size(max = 50)
    @Column(name = "tripStatus")
    private String tripStatus;
    @Size(max = 45)
    @Column(name = "displayPhoto")
    private String displayPhoto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "userId")
    private int userId;
    @Size(max = 255)
    @Column(name = "currentLocation")
    private String currentLocation;
    @Size(max = 255)
    @Column(name = "Destination")
    private String destination;
    @Size(max = 255)
    @Column(name = "Start_Location")
    private String startLocation;

    public TripCore() {
    }

    public TripCore(Integer id) {
        this.id = id;
    }

    public TripCore(Integer id, String name, Date startDate, Date endDate, double startLatitude, double startLongitude, double endLatitude, double endLongitude, int isPublic, int userId) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.isPublic = isPublic;
        this.userId = userId;
        this.tripStatus = "planned";
        this.currentLocation = this.startLocation;
    }
    public TripCore(String name, Date startDate, Date endDate, double startLatitude, double startLongitude, double endLatitude, double endLongitude, int isPublic, int userId) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.isPublic = isPublic;
        this.userId = userId;
        this.tripStatus = "planned";
        this.currentLocation = this.startLocation;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(double startLatitude) {
        this.startLatitude = startLatitude;
    }

    public double getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(double startLongitude) {
        this.startLongitude = startLongitude;
    }

    public double getEndLatitude() {
        return endLatitude;
    }

    public void setEndLatitude(double endLatitude) {
        this.endLatitude = endLatitude;
    }

    public double getEndLongitude() {
        return endLongitude;
    }

    public void setEndLongitude(double endLongitude) {
        this.endLongitude = endLongitude;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }

    public String getDisplayPhoto() {
        return displayPhoto;
    }

    public void setDisplayPhoto(String displayPhoto) {
        this.displayPhoto = displayPhoto;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
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
        if (!(object instanceof TripCore)) {
            return false;
        }
        TripCore other = (TripCore) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TripCore[ id=" + id + " ]";
    }

    public Integer getIsRoundTrip() {
        return isRoundTrip;
    }

    public void setIsRoundTrip(Integer isRoundTrip) {
        this.isRoundTrip = isRoundTrip;
    }
    
}