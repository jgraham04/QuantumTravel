/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.TripCore;
import entity.UserCore;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.TripCoreFacade;
import session.UserCoreFacade;

/**
 *
 * @author Sonic_000
 */
@WebServlet(name = "LoginServlet",
            urlPatterns = {"/LoginServlet", "/register", "/logout"
                            })
public class LoginServlet extends HttpServlet {

    @EJB
    public UserCoreFacade userFacade;
        
    @EJB
    public TripCoreFacade tripCoreFacade;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String userPath = request.getServletPath();
        
        if(userPath.equals("/logout"))
        {
            request.getSession().invalidate();
            response.sendRedirect("index.xhtml");
        }
        
        
        try {
            request.getRequestDispatcher("qRegister.xhtml").forward(request, response);
        } catch (Exception ex) {
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();
        //HttpSession session = request.getSession();
        
        //If the login button was selected
        if (userPath.equals("/LoginServlet")) 
        {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UserCore user = userFacade.findByLoginInfo(username, password);
            if (user != null) 
            {
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("username", user.getUsername());
                request.getSession().setAttribute("userfirstname", user.getFirstName());
                request.getSession().setAttribute("userlastname", user.getLastName());
                 request.getSession().setAttribute("userpassword", user.getPassword());
                request.getSession().setAttribute("useremail", user.getEmail());
                request.getSession().setAttribute("userid", user.getId());
                System.out.println("Your name is:" + user.getFirstName() + " " + user.getLastName() + ".");
                //This will be removed when we have a profile page
                 response.sendRedirect("faces/qCurrentTrip.xhtml");
                 
            } 
            else 
            {
                response.sendRedirect("index.xhtml?error=1");
            }
        }
        //If the register button was selected
        else if (userPath.equals("/register"))
        {
            String userName = request.getParameter("username");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String email = request.getParameter("email");
            
            //First check if all fields are filled in.
            if((userName != null) || (firstName != null) || (lastName != null) || password != null || confirmPassword != null || email != null)
            {
                //Next check if username is taken.
                if(userFacade.userExists(userName) || userName.equals("") || userName.length() < 4)
                {
                    //Give error message to user saying username must be changed.
                    //Then take no action.
                    response.sendRedirect("qRegister.xhtml");
                }
                //Check if passwords match.
                else if(!password.equals(confirmPassword))
                {
                    //Give error message saying the passwords must match.
                    //Then take no action.
                    response.sendRedirect("qRegister.xhtml");
                }
                //Check if the email is used.
                else if(userFacade.emailExists(email))
                {
                    //Give error message saying the email is already in use.
                    //Then take no action.
                    response.sendRedirect("qRegister.xhtml");
                }
                
                /*If username doesn't exist, and is valid.
                 * And if it is a valid username.
                 * And if the passwords match.
                 * And if the email isn't already in use.
                 * Then we can add the new user to the database.
                 */
                else
                {
                    userFacade.addUser(firstName, lastName, userName, password, email);
                    try 
                    {
                        response.sendRedirect("registrationComplete.xhtml");
                    } 
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
            else
            {
                //Give error message saying that all fields must be filled in.
                //Then take no action.
            }
        }
        else if(userPath.equals("/logout"))
        {
            response.sendRedirect("/registrationComplete.xhtml");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}