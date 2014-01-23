/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.UserCore;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sonic_000
 */
@Stateless
public class UserCoreFacade extends AbstractFacade<UserCore> {
    @PersistenceContext(unitName = "QuantumTravelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserCoreFacade() {
        super(UserCore.class);
    }
    
    public UserCore findByLoginInfo(String username, String password) {
        List<UserCore> users = findAll();
        for (int i=0;i<users.size();i++) {
            if (username.equalsIgnoreCase(users.get(i).getUsername()) && password.equals(users.get(i).getPassword())) {
                return users.get(i);
            } 
        }
        return null;
    }
    
    /**
     * Returns true if username is in the database.
     * 
     * @param username
     * @return 
     */
    public boolean userExists(String username) {
        List<UserCore> users = findAll();
        for (int i=0;i<users.size();i++) {
            if (username.equalsIgnoreCase(users.get(i).getUsername())) {
                return true;
            } 
        }
        return false;
    }
    
    /**
     * Returns true if email is in the database.
     * 
     * @param email
     * @return 
     */
    public boolean emailExists(String email) {
        List<UserCore> users = findAll();
        for (int i=0;i<users.size();i++) {
            if (email.equalsIgnoreCase(users.get(i).getEmail())) {
                return true;
            } 
        }
        return false;
    }
    
    /**
     * Adds a user to the database.
     * 
     * @param firstName
     * @param lastName
     * @param userName
     * @param password
     * @param email 
     */
    public void addUser(String firstName, String lastName, String userName, String password, String email)
    {
        UserCore newUser = new UserCore();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setUsername(userName);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setIsAdmin(0);              //It is assumed that registered users are all non-admins.
        create(newUser);
    }
}
