/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.TripCore;
import entity.Waypoint;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import session.TripCoreFacade;
import session.WaypointFacade;

/**
 *
 * @author Firat
 */
 
@ManagedBean(name = "CurrentTripTableBean")
@RequestScoped
public class CurrentTripTableBean {
    public String tripName;
    public String startLocation;
    public String destination;
    public String currentLocation;
    public int tripId;
    public int isPublic;
    public List<Waypoint> waypoints;
    
    String tripStatus="current";
    int userId=(Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
    
    @EJB
    public TripCoreFacade tripCoreFacade;
    @EJB
    public WaypointFacade waypointFacade;
    TripCore trip ;
      
     public CurrentTripTableBean() {
    }
     
    @PostConstruct
    public void init()
    {
        //System.out.print("Current trip user is "+userId)
     trip = tripCoreFacade.getTripByStatus(userId, tripStatus);
     isPublic = trip.getIsPublic();
     if(trip.getId() != null)
        waypoints = waypointFacade.getByTripId(trip.getId());
    }
    
    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }
    
    public String getTripName() 
    {
        return trip.getName();
    }

    public void setTripName(String tripName) 
    {
        this.tripName = tripName;
    }

    public String getStartLocation() 
    {
        return trip.getStartLocation();
    }

    public void setStartLocation(String startLocation) 
    {
        this.startLocation = startLocation;
    }

    public String getDestination() 
    {
        return trip.getDestination();
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCurrentLocation() 
    {
        return trip.getCurrentLocation();
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getUserId() {
        return trip.getUserId();
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    

    public int getTripId() {
        return trip.getId();
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }
    
}
