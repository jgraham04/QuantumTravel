/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.TripCore;
import entity.UserCore;
import entity.Waypoint;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Sonic_000
 */
@WebServlet(name = "TripServlet", urlPatterns = {"/TripServlet", "/EditTrip", "/TakeTrip", "/CancelTrip"})
public class TripServlet extends HttpServlet {

    @EJB
    public TripCoreFacade tripCoreFacade;
    @EJB
    public WaypointFacade waypointFacade;

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
        
        HttpSession session = request.getSession();
        String userPath = request.getServletPath();

        if (userPath.equals("/EditTrip")) {
            int tripId = Integer.parseInt(request.getParameter("tripId"));
                       
            String tripName = request.getParameter("tripName");
            double startLat = Double.parseDouble(request.getParameter("startLatitude"));
            double startLong = Double.parseDouble(request.getParameter("startLongitude"));
            double endLat = Double.parseDouble(request.getParameter("endLatitude"));
            double endLong = Double.parseDouble(request.getParameter("endLongitude"));
            String startLoc = (String) request.getParameter("startLocation");
            String endLoc = (String) request.getParameter("endLocation");
            
            String startDateString = request.getParameter("startDate");
            String endDateString = request.getParameter("endDate");

            String publicTrip = request.getParameter("isPublic");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = new Date();
            Date endDate = new Date();

            String[] waypointNames = request.getParameterValues("waypoint");
            String[] waypointLatitudeStrings = request.getParameterValues("waypointLatitude");
            String[] waypointLongitudeStrings = request.getParameterValues("waypointLongitude");
            //System.out.println("waypointNames: "+waypointNames.length);
            //System.out.println("waypointLatitudeStrings: "+waypointLatitudeStrings.length);
            //System.out.println("waypointLongitudeStrings: "+waypointLongitudeStrings.length);
            try {
                startDate = sdf.parse(startDateString);
                endDate = sdf.parse(endDateString);
            } catch (ParseException ex) {
                Logger.getLogger(TripServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            TripCore trip = tripCoreFacade.getTripById(tripId);
            trip.setName(tripName);
            trip.setStartLatitude(startLat);
            trip.setStartLongitude(startLong);
            trip.setStartLocation(startLoc);
            trip.setEndLatitude(endLat);
            trip.setEndLongitude(endLong);
            trip.setDestination(endLoc);
            trip.setCurrentLocation(startLoc);
            trip.setStartDate(startDate);
            trip.setEndDate(endDate);
            
            //The checkbox returns null if it is not checked.
            //If it is anything else, make the trip public.
            if(publicTrip != null)
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
        } else if (userPath.equals("/TripServlet")) {

            String tripName = request.getParameter("tripName");
            double startLat = Double.parseDouble(request.getParameter("startLatitude"));
            double startLong = Double.parseDouble(request.getParameter("startLongitude"));
            double endLat = Double.parseDouble(request.getParameter("endLatitude"));
            double endLong = Double.parseDouble(request.getParameter("endLongitude"));
            String startLoc = (String) request.getParameter("startLocation");
            String endLoc = (String) request.getParameter("endLocation");

            String startDateString = request.getParameter("startDate");
            String endDateString = request.getParameter("endDate");
            String[] waypointNames = request.getParameterValues("waypoint");
            String[] waypointLatitudeStrings = request.getParameterValues("waypointLatitude");
            String[] waypointLongitudeStrings = request.getParameterValues("waypointLongitude");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = new Date();
            Date endDate = new Date();


            try {
                startDate = sdf.parse(startDateString);
                endDate = sdf.parse(endDateString);
            } catch (ParseException ex) {
                Logger.getLogger(TripServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            int tripId = tripCoreFacade.getMaxTrip() + 1;

            UserCore user = (UserCore) session.getAttribute("user");

            TripCore trip = new TripCore(tripId, tripName, startDate, endDate, startLat, startLong, endLat, endLong, 0, user.getId());

            trip.setStartLocation(startLoc);
            trip.setCurrentLocation(startLoc);
            trip.setDestination(endLoc);
            tripCoreFacade.create(trip);

            if (waypointLatitudeStrings != null) {
                for (int i = 0; i < waypointLatitudeStrings.length; i++) {
                    Waypoint waypoint = new Waypoint(waypointFacade.getMaxId() + 1, Double.parseDouble(waypointLatitudeStrings[i]), Double.parseDouble(waypointLongitudeStrings[i]), tripId);
                    waypoint.setName(waypointNames[i]);
                    waypointFacade.create(waypoint);
                    //System.out.println(waypointLatitudeStrings[i]+", "+waypointLongitudeStrings[i]);
                }
            }
            /*
             * If the take trip button is pressed, it means
             * that there is no current trip for this user.
             * Thus we can safely set this trip to be the
             * current trip.
             */
        } else if (userPath.equals("/TakeTrip")) {
           
            int tripId = Integer.parseInt(request.getParameter("tripId"));
            TripCore trip = tripCoreFacade.getTripById(tripId);
            
            trip.setTripStatus("current");
            
            tripCoreFacade.edit(trip);
            
        }
        else if (userPath.equals("/CancelTrip")) {
           int tripId = Integer.parseInt(request.getParameter("tripId"));
           TripCore trip = tripCoreFacade.getTripById(tripId);
           trip.setTripStatus("cancelled");
           tripCoreFacade.edit(trip);
            
        }
        //System.out.println(tripCoreFacade.findAll().get(0).getName());
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        response.sendRedirect("qMyTrips.xhtml");
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
