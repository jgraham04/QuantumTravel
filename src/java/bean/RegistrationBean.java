/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Sonic_000
 */
@ManagedBean
@RequestScoped
public class RegistrationBean {
    public String firstName;

    public String getFirstName() {
        System.out.println("Getter");
        return firstName;
    }

    public void setFirstName(String firstName) {
        System.out.println("Setter");
        this.firstName = firstName;
    }
    /**
     * Creates a new instance of RegistrationBean
     */
    public RegistrationBean() {
        System.out.println("Bean has begun.");
    }
}
