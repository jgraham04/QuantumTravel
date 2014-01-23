/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.bean.ManagedBean;
import entity.TripCore;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import session.TripCoreFacade;

/**
 *
 * @author Joseph
 */
@ManagedBean(name = "MyTripsBean")
@RequestScoped
public class MyTripsBean {

    public String tripName;
    public String startLocation;
    public String destination;
    public String currentLocation;
    public String date;
    public int tripId;
    int userId;
    @EJB
    public TripCoreFacade tripCoreFacade;
    List<TripCore> allTrips;
    List<TripCore> trips;

    /**
     * Creates a new instance of MyPagesBean
     */
    public MyTripsBean() {
    }

    @PostConstruct
    public void init() 
    {
        userId = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
        System.out.println(userId);

        trips = tripCoreFacade.getTripByUser(userId);
        allTrips = tripCoreFacade.getAllTripByUser(userId);
    }

    public List<TripCore> getTrips() {
        return trips;
    }

    public List<TripCore> getAllTrips() {
        return allTrips;
    }
}
