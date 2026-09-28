package com.shopping.dao;
 
import com.shopping.model.Order;
import com.shopping.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
 
public class OrderDAO {
 
    // Insert a new order into the database
    public boolean addOrder(Order order) {
        String sql = "INSERT INTO orders (customer_name, password, product_name, quantity, "
                   + "price, total_amount, order_date, address) VALUES (?,?,?,?,?,?,?,?)";
 
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setString(1, order.getCustomerName());
            ps.setString(2, order.getPassword());
            ps.setString(3, order.getProductName());
            ps.setInt(4, order.getQuantity());
            ps.setBigDecimal(5, order.getPrice());
            ps.setBigDecimal(6, order.getTotalAmount());
            ps.setDate(7, order.getOrderDate());
            ps.setString(8, order.getAddress());
 
            return ps.executeUpdate() > 0;
 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
 
    // Retrieve all orders from the database
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM orders ORDER BY order_id DESC";
 
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
 
            while (rs.next()) {
                Order o = new Order();
                o.setOrderId(rs.getInt("order_id"));
                o.setCustomerName(rs.getString("customer_name"));
                o.setPassword(rs.getString("password"));
                o.setProductName(rs.getString("product_name"));
                o.setQuantity(rs.getInt("quantity"));
                o.setPrice(rs.getBigDecimal("price"));
                o.setTotalAmount(rs.getBigDecimal("total_amount"));
                o.setOrderDate(rs.getDate("order_date"));
                o.setAddress(rs.getString("address"));
                orderList.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
}