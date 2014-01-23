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
import utility.photoSelectHelper;

/**
 *
 * @author Firat
 */
@ManagedBean(name = "GalleriaBean")
@RequestScoped
public class GalleriaBean {

    /**
     * Creates a new instance of GalleriaBeanNew
     */
   private List<String> images;

   

    @PostConstruct
    public void init() {
        images =new ArrayList<String>(photoSelectHelper.processRequest()) ;

    }
    
    public List<String> getImages() {
        return images;
    }

}
