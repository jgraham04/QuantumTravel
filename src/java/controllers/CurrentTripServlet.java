/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import bean.CurrentTripTableBean;
import entity.TripCore;
import entity.Waypoint;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.TripCoreFacade;
import session.WaypointFacade;

/**
 *
 * @author Firat
 */
@WebServlet(name = "CurrentTripServlet", urlPatterns = {"/CurrentTripServlet"})
public class CurrentTripServlet extends HttpServlet {

    @EJB
    public TripCoreFacade tripCoreFacade;
    @EJB
    public WaypointFacade waypointFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

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
        processRequest(request, response);
        HttpSession session = request.getSession();

        int tripId = Integer.parseInt(request.getParameter("tripId"));
        String publicTrip = request.getParameter("isPublic");
        String newCurrentLocation = (String) request.getParameter("newLocation");

        String[] waypointNames = request.getParameterValues("waypoint");
        String[] waypointLatitudeStrings = request.getParameterValues("waypointLatitude");
        String[] waypointLongitudeStrings = request.getParameterValues("waypointLongitude");

        TripCore trip = tripCoreFacade.getTripById(tripId);

        trip.setCurrentLocation(newCurrentLocation);

        //The checkbox returns null if it is not checked.
        //If it is anything else, make the trip public.
        if (publicTrip != null)
            trip.setIsPublic(1);
        else
            trip.setIsPublic(0);       

        List<Waypoint> previousWaypoints = waypointFacade.getByTripId(tripId);
        for (int i = 0; i < previousWaypoints.size(); i++) {
            waypointFacade.remove(previousWaypoints.get(i));
        }
        if (waypointNames != null) {
            for (int i = 0; i < waypointNames.length; i++) {
                Waypoint waypoint = new Waypoint(waypointFacade.getMaxId() + 1, Double.parseDouble(waypointLatitudeStrings[i]), Double.parseDouble(waypointLongitudeStrings[i]), tripId);
                waypoint.setName(waypointNames[i]);
                waypointFacade.create(waypoint);
                System.out.println("Create Done-" + waypoint.getName());
            }
        }

        tripCoreFacade.edit(trip);

        response.sendRedirect("qCurrentTrip.xhtml");

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
