/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.TripCore;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import session.TripCoreFacade;

/**
 *
 * @author Sonic_000
 */
@ManagedBean(name = "SearchBean")
@RequestScoped
public class SearchBean {
    @EJB
    public TripCoreFacade tripCoreFacade;
    public String searchQuery;
    public List<TripCore> trips;
    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
    /**
     * Creates a new instance of SearchBean
     */
    public SearchBean() {
    }
    
    @PostConstruct
    public void init() 
    {
        searchQuery = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getQueryString().replace("q=", "");    
        trips = tripCoreFacade.getTripsBySubstring(searchQuery);

    }

    public List<TripCore> getTrips() {
        return trips;
    }

    public void setTrips(List<TripCore> trips) {
        this.trips = trips;
    }
}
