/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import utility.photoSelectHelper;
 
@ManagedBean(name="fileUploadBean")
@SessionScoped
public class FileUploadController {
   String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
   String adjustedPath = path.substring(0, path.length()-9);
   String userid = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uploadUserid").toString();
   String tripid = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uploadTripid").toString();
   private String destination=adjustedPath+"web\\img\\galleria\\";
   
//   @EJB
//   public TripPhotoFacade tripPhotoFacade;

    public void upload(FileUploadEvent event) {  
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        // Do what you want with the file        
        try {
//            TripPhoto tripPhoto = new TripPhoto();
//            tripPhoto.setPhotoId(8);
//            tripPhoto.setTripId(2);
//            tripPhotoFacade.edit(tripPhoto);
            userid = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uploadUserid").toString();
            tripid = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uploadTripid").toString();
            photoSelectHelper.insertPhotoRequest(Integer.parseInt(userid), Integer.parseInt(tripid), userid+"_"+tripid+"_"+event.getFile().getFileName());
            copyFile(userid+"_"+tripid+"_"+event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }  

    public void copyFile(String fileName, InputStream in) {
           try {
             
             
                // write the inputStream to a FileOutputStream
                OutputStream out = new FileOutputStream(new File(destination + fileName));
             
                int read = 0;
                byte[] bytes = new byte[1024];
             
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
             
                in.close();
                out.flush();
                out.close();
             
                System.out.println("New file created!");
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    }
}