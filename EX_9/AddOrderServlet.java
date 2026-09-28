package com.shopping.controller;
 
import com.shopping.dao.OrderDAO;
import com.shopping.model.Order;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
@WebServlet(name = "AddOrderServlet", urlPatterns = {"/AddOrderServlet"})
public class AddOrderServlet extends HttpServlet {
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        String customerName = request.getParameter("customerName");
        String password = request.getParameter("password");
        String productName  = request.getParameter("productName");
        int quantity         = Integer.parseInt(request.getParameter("quantity"));
        BigDecimal price      = new BigDecimal(request.getParameter("price"));
        BigDecimal totalAmount = price.multiply(BigDecimal.valueOf(quantity));
        Date orderDate        = Date.valueOf(request.getParameter("orderDate"));
        String address        = request.getParameter("address");
 
        Order order = new Order(customerName, password, productName, quantity, price,
                                totalAmount, orderDate, address);
 
        OrderDAO dao = new OrderDAO();
        boolean success = dao.addOrder(order);
 
        if (success) {
            response.sendRedirect("viewOrders.jsp");
        } else {
            request.setAttribute("errorMessage", "Failed to place the order. Please try again.");
            request.getRequestDispatcher("orderForm.jsp").forward(request, response);
        }
    }
}