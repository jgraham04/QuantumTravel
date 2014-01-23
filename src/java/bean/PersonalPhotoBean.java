/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import utility.photoSelectHelper;

/**
 *
 * @author Andrew
 */
@ManagedBean(name = "PersonalPhotoBean")
@RequestScoped
public class PersonalPhotoBean {

    /**
     * Creates a new instance of PersonalPhotoBean
     */
    private List<String> images;

    @PostConstruct
    public void init() {
        String userid = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uploadUserid").toString();
        String tripid = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uploadTripid").toString();
        images = new ArrayList<String>(photoSelectHelper.processRequest(Integer.parseInt(userid),Integer.parseInt(tripid)));

    }

    public List<String> getImages() {
        return images;
    }
}
