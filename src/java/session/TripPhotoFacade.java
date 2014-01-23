/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.TripPhoto;
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
public class TripPhotoFacade extends AbstractFacade<TripPhoto> {
    @PersistenceContext(unitName = "QuantumTravelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TripPhotoFacade() {
        super(TripPhoto.class);
    }
    public List<TripPhoto> getPhotoByTripId(int id) {
        List<TripPhoto> allPhotos = findAll();
        List<TripPhoto> photos = new ArrayList<TripPhoto>();
        for (int i =0;i<allPhotos.size();i++) {
            if (id==allPhotos.get(i).getTripId()) {
                photos.add(allPhotos.get(i));
            }
        }
        return photos;
    }
}
