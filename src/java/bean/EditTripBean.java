/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.TripCore;
import entity.Waypoint;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import session.TripCoreFacade;
import session.WaypointFacade;

/**
 *
 * @author Sonic_000
 */
@ManagedBean(name = "EditTripBean")
@RequestScoped
public class EditTripBean {
    @EJB
    public TripCoreFacade tripCoreFacade;
    @EJB
    public WaypointFacade waypointFacade;
    
    public String name;
    public Date startDate;
    public Date endDate;
    public String startLocation;
    public String endLocation;
    public int isPublic;
    public List<Waypoint> waypoints;

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }
    
    public TripCore trip;
    public List<TripCore> trips;    
    public int tripId;
    @PostConstruct
    public void init() 
    {
        String queryString;
        queryString = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getQueryString();
        tripId = Integer.parseInt(queryString);
        trip = tripCoreFacade.getTripById(tripId);
        name = trip.getName();
        startDate = trip.getStartDate();
        endDate = trip.getEndDate();
        startLocation = trip.getStartLocation();
        endLocation = trip.getDestination();
        waypoints = waypointFacade.getByTripId(tripId);
        isPublic = trip.getIsPublic();
      
        
    }
    /**
     * Creates a new instance of EditTripBean
     */
    public EditTripBean() {
        
      
        
    } 
    
    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
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

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    
    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }
    
}
