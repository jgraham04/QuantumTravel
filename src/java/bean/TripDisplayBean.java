/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.TripCore;
import entity.TripPhoto;
import entity.Waypoint;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import session.TripCoreFacade;
import session.TripPhotoFacade;
import session.WaypointFacade;

/**
 *
 * @author Sonic_000
 */
@ManagedBean(name = "TripDisplayBean")
@RequestScoped
public class TripDisplayBean {
    @EJB
    public TripCoreFacade tripCoreFacade;
    @EJB
    public WaypointFacade waypointFacade;
    @EJB
    public TripPhotoFacade tripPhotoFacade;
    
    public boolean tripAllowed;
    public List<Waypoint> waypoints;

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }
    
    public boolean isTripAllowed() {
        return tripAllowed;
    }

    public void setTripAllowed(boolean tripAllowed) {
        this.tripAllowed = tripAllowed;
    }
    public int tripId;
    public TripCore trip;

    public TripCore getTrip() {
        return trip;
    }

    public void setTrip(TripCore trip) {
        this.trip = trip;
    }
    
    @PostConstruct
    public void init() {
        String queryString;
        queryString = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getQueryString();
        tripId = Integer.parseInt(queryString);
        trip = tripCoreFacade.getTripById(tripId);
        if (trip==null){
            tripAllowed=false;
        } else {
            tripAllowed=true;
        }
        waypoints =waypointFacade.getByTripId(tripId);
              
        
    }
    public List<String> getImages() {
        List<TripPhoto> photos = tripPhotoFacade.getPhotoByTripId(tripId);
        List<String> photoString = new ArrayList<String>();
        for (int i=0;i<photos.size();i++) {
            photoString.add(photos.get(i).getPhotoName());
            //System.out.println("Photo: "+photos.get(i).getPhotoName());
        }
        return photoString;
        
    }
    
    /**
     * Creates a new instance of TripDisplayBean
     */
    public TripDisplayBean() {
    }
}
