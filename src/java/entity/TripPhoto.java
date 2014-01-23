/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "trip_photo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TripPhoto.findAll", query = "SELECT t FROM TripPhoto t"),
    @NamedQuery(name = "TripPhoto.findByPhotoId", query = "SELECT t FROM TripPhoto t WHERE t.photoId = :photoId"),
    @NamedQuery(name = "TripPhoto.findByUserId", query = "SELECT t FROM TripPhoto t WHERE t.userId = :userId"),
    @NamedQuery(name = "TripPhoto.findByTripId", query = "SELECT t FROM TripPhoto t WHERE t.tripId = :tripId"),
    @NamedQuery(name = "TripPhoto.findByPhotoName", query = "SELECT t FROM TripPhoto t WHERE t.photoName = :photoName")})
public class TripPhoto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "photo_id")
    private Integer photoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trip_id")
    private int tripId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "photo_name")
    private String photoName;

    public TripPhoto() {
    }

    public TripPhoto(Integer photoId) {
        this.photoId = photoId;
    }

    public TripPhoto(Integer photoId, int userId, int tripId, String photoName) {
        this.photoId = photoId;
        this.userId = userId;
        this.tripId = tripId;
        this.photoName = photoName;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (photoId != null ? photoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TripPhoto)) {
            return false;
        }
        TripPhoto other = (TripPhoto) object;
        if ((this.photoId == null && other.photoId != null) || (this.photoId != null && !this.photoId.equals(other.photoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TripPhoto[ photoId=" + photoId + " ]";
    }
    
}
