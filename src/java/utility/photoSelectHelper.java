/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public class photoSelectHelper {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://mysql2.cs.stonybrook.edu/";
    private static final String USER = "cse308team3";
    private static final String PASS = "changeit";

    public static ArrayList processRequest() {
        Connection conn = null;
        Statement stmt = null;
        String sql = "SELECT photo_name FROM trip_photo ORDER BY RAND() LIMIT 7;";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL + USER, USER, PASS);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            ArrayList<String> st = new ArrayList<String>();

            while (rs.next()) {
                st.add(rs.getString("photo_name"));

            }

            stmt.close();
            conn.close();

            return st;
        } catch (Exception e) {
            System.out.print(e);
        }
        // return null if there is error
        return null;
    }

    public static ArrayList processRequest(int user_id, int trip_id) {
        Connection conn = null;
        Statement stmt = null;
        String sql = "SELECT * FROM trip_photo where user_id = "+user_id+" and trip_id ="+trip_id+";";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL + USER, USER, PASS);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            ArrayList<String> st = new ArrayList<String>();

            while (rs.next()) {
                st.add(rs.getString("photo_name"));

            }

            stmt.close();
            conn.close();

            return st;
        } catch (Exception e) {
            System.out.print(e);
        }
        // return null if there is error
        return null;
    }

    public static void insertPhotoRequest(int user_id, int trip_id, String photo_name) {
        Connection conn = null;
        Statement stmt = null;
        String sql = "INSERT INTO trip_photo (user_id, trip_id, photo_name) VALUES ('" + user_id + "', '" + trip_id + "', '" + photo_name + "');";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL + USER, USER, PASS);

            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.print(e);
        }
    }
    public static void deletePhotoRequest(int user_id, int trip_id, String photo_name) {
        Connection conn = null;
        Statement stmt = null;
        String sql = "delete from trip_photo where user_id=user_id and trip_id=trip_id "
                + "and photo_name='photo_name';";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL + USER, USER, PASS);

            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
