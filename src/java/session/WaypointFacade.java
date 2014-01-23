/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Waypoint;
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
public class WaypointFacade extends AbstractFacade<Waypoint> {
    @PersistenceContext(unitName = "QuantumTravelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WaypointFacade() {
        super(Waypoint.class);
    }
    
    public int getMaxId() {
         List<Waypoint> allWaypoints = findAll();
        int temp =0;
        for (int i=0;i<allWaypoints.size();i++) {
            if (allWaypoints.get(i).getId()>temp) {
                temp=allWaypoints.get(i).getId();
            }
        }
        return temp;
    }
    public List<Waypoint> getByTripId(int tripId) {
        List<Waypoint> yourWaypoints = findAll();
        List<Waypoint> myWaypoints = new ArrayList<Waypoint>();
        for (int i=0;i<yourWaypoints.size();i++) {
            if (yourWaypoints.get(i).getTripId()==tripId) {
                myWaypoints.add(yourWaypoints.get(i));
            }
        }
        return myWaypoints;
    }
}
