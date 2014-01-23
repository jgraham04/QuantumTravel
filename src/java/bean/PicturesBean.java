/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ekudler
 */
@ManagedBean(name = "picturesBean")
@RequestScoped
public class PicturesBean {
    List<String> listX;
    /**
     * Creates a new instance of PicturesBean
     */
    public PicturesBean() {
        listX = new ArrayList<String>();
        
       String path=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
       System.out.print(path);
        
       File dir = new File("\\img\\galleria\\");
        
         System.out.print(dir.getAbsolutePath());
        File [] files = dir.listFiles();
        for (int i=0;i<files.length;i++) {
            listX.add(files[i].getName());
        }
        
    }
    public List<String> getListX() {
        return listX;
    }
}
