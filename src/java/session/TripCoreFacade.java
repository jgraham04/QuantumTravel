/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.TripCore;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sonic_000
 */
@Stateless
public class TripCoreFacade extends AbstractFacade<TripCore> {

    @PersistenceContext(unitName = "QuantumTravelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TripCoreFacade() {
        super(TripCore.class);
    }

    //Finds all trips based on status, when given a specific user ID.
    public TripCore getTripByStatus(int userId, String status) {
        List<TripCore> trips = findAll();
        
        TripCore myTrip = new TripCore();
        for (int i = 0; i < trips.size(); i++) {
            if (userId == (trips.get(i).getUserId()) && status.equals(trips.get(i).getTripStatus())) {
                myTrip = trips.get(i);
            }
        }
        
        return myTrip;
    }

    //Finds all trips for a specific user ID.
    public List<TripCore> getTripByUser(int userId) {
        //Find all trips in the database
        List<TripCore> trips = findAll();

        //Create new list object to hold all the user's trips.
        ArrayList<TripCore> myTrips = new ArrayList<TripCore>();

        for (int i = 0; i < trips.size(); i++) {
            //If a trip is found and it belongs to the user, add it to the list.
            if (userId == (trips.get(i).getUserId())) {
                if (!trips.get(i).getTripStatus().equalsIgnoreCase("current")) {
                    myTrips.add(trips.get(i));
                }
            }
        }

        return myTrips;
    }

    public List<TripCore> getAllTripByUser(int userId) {
        //Find all trips in the database
        List<TripCore> trips = findAll();

        //Create new list object to hold all the user's trips.
        ArrayList<TripCore> myTrips = new ArrayList<TripCore>();

        for (int i = 0; i < trips.size(); i++) {
            //If a trip is found and it belongs to the user, add it to the list.
            if (userId == (trips.get(i).getUserId())) {
                myTrips.add(trips.get(i));
            }
        }

        return myTrips;
    }

    public TripCore getTripById(int id) {
        List<TripCore> trips = findAll();
        for (int i = 0; i < trips.size(); i++) {
            if (trips.get(i).getId() == id) {
                return trips.get(i);
            }
        }
        return null;
    }

    public int getMaxTrip() {
        List<TripCore> allTrips = findAll();
        int temp = 0;
        for (int i = 0; i < allTrips.size(); i++) {
            if (allTrips.get(i).getId() > temp) {
                temp = allTrips.get(i).getId();
            }
        }
        return temp;
    }

    public List<TripCore> getTripsBySubstring(String subString) {
        List<TripCore> allTrips = findAll();
        List<TripCore> trips = new ArrayList<TripCore>();
        for (int i = 0; i < allTrips.size(); i++) {
            if ((allTrips.get(i).getName().toLowerCase().indexOf(subString.toLowerCase()) > -1) ||
                    (allTrips.get(i).getDestination().toLowerCase().indexOf(subString.toLowerCase()) > -1) ||
                    (allTrips.get(i).getStartLocation().toLowerCase().indexOf(subString.toLowerCase()) > -1))
                    {
                trips.add(allTrips.get(i));
            }
        
        }
        return trips;
    }
}