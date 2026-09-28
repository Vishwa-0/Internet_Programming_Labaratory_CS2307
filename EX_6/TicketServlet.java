package com.bus.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TicketServlet", urlPatterns = {"/TicketServlet"})
public class TicketServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // TODO: Replace 'your_database_name' with your actual MySQL database name
    private static final String DB_URL = "jdbc:mysql://localhost:3306/servlet?useSSL=false&serverTimezone=UTC&autoReconnect=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "mysql";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String userName = request.getParameter("user_name");
        String source = request.getParameter("source");
        String destination = request.getParameter("destination");
        String eventName = (source != null && destination != null) ? (source + " to " + destination) : ""; 
        String numTicketsStr = request.getParameter("num_tickets");

        if (userName == null || source == null || destination == null || numTicketsStr == null || 
            userName.trim().isEmpty() || source.trim().isEmpty() || destination.trim().isEmpty() || numTicketsStr.trim().isEmpty()) {
            out.println("<h3>Error: All required fields must be filled out. Check your HTML input names.</h3>");
            return;
        }

        int numTickets = Integer.parseInt(numTicketsStr);
        String sql = "INSERT INTO tickets (user_name, event_name, num_tickets) VALUES (?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, userName);
                pstmt.setString(2, eventName);
                pstmt.setInt(3, numTickets);

                int rowsAffected = pstmt.executeUpdate();
                
                // Debugging output to server console
                System.out.println("DEBUG: Rows affected in database: " + rowsAffected);

                out.println("<html><head><title>Booking Status</title></head><body style='font-family:Arial; text-align:center; padding-top:50px;'>");
                if (rowsAffected > 0) {
                    out.println("<h2 style='color:green;'>Bus Ticket Booked Successfully!</h2>");
                    out.println("<p><strong>Passenger:</strong> " + userName + "</p>");
                    out.println("<p><strong>Route:</strong> " + eventName + "</p>");
                    out.println("<p><strong>Seats Booked:</strong> " + numTickets + "</p>");
                    out.println("<br><a href='index.html'>Book Another Ticket</a>");
                } else {
                    out.println("<h2 style='color:red;'>Booking failed. Zero rows affected.</h2>");
                }
                out.println("</body></html>");
            }
        } catch (ClassNotFoundException e) {
            out.println("<h3 style='color:red;'>MySQL JDBC Driver missing. Place it in GlassFish lib folder.</h3>");
            e.printStackTrace(out);
        } catch (SQLException e) {
            out.println("<h3 style='color:red;'>Database Error: " + e.getMessage() + "</h3>");
            e.printStackTrace(out);
        }
    }
}