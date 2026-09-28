<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.shopping.model.Order" %>
<%@ page import="com.shopping.dao.OrderDAO" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ShopEase - View Orders</title>
<style>
  * {
    box-sizing: border-box;
  }
  body {
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
    background-color: #f0fdf4;
    color: #166534;
    margin: 0;
    min-height: 100vh;
    display: flex;
    flex-direction: column;
  }
  nav {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 18px 50px;
    background: #ffffff;
    border-bottom: 1px solid #dcfce7;
  }
  .logo {
    font-size: 20px;
    font-weight: 600;
    color: #14532d;
  }
  .logo span {
    color: #16a34a;
  }
  .nav-links a {
    color: #15803d;
    text-decoration: none;
    margin-left: 24px;
    font-size: 14px;
    font-weight: 500;
    transition: color 0.15s;
  }
  .nav-links a:hover {
    color: #16a34a;
  }
  .nav-links .btn-nav {
    background-color: #16a34a;
    padding: 8px 16px;
    border-radius: 6px;
    color: white;
  }
  .nav-links .btn-nav:hover {
    background-color: #15803d;
    color: white;
  }
  .main-container {
    flex: 1;
    max-width: 1200px;
    width: 100%;
    margin: 40px auto;
    padding: 0 20px;
  }
  h2 {
    margin: 0 0 6px 0;
    color: #14532d;
    font-size: 22px;
    font-weight: 600;
  }
  .subtitle {
    color: #15803d;
    font-size: 14px;
    margin-bottom: 24px;
  }
  .table-card {
    background: #ffffff;
    padding: 32px;
    border-radius: 16px;
    box-shadow: 0 10px 25px -5px rgba(22, 101, 52, 0.05), 0 8px 10px -6px rgba(22, 101, 52, 0.03);
    border: 1px solid #dcfce7;
    position: relative;
    overflow-x: auto;
  }
  .table-card::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    height: 4px;
    background: linear-gradient(90deg, #16a34a, #22c55e, #4ade80);
  }
  table {
    width: 100%;
    border-collapse: collapse;
    text-align: left;
    white-space: nowrap;
  }
  th, td {
    padding: 12px 16px;
    border-bottom: 1px solid #f0fdf4;
    font-size: 14px;
  }
  th {
    background-color: #f8fafc;
    color: #14532d;
    font-weight: 600;
  }
  td {
    color: #334155;
  }
  tr:hover {
    background-color: #f0fdf4;
  }
</style>
</head>
<body>
  <nav>
    <div class="logo">Shop<span>Ease</span></div>
    <div class="nav-links">
      <a href="<%=request.getContextPath()%>/index.jsp">Home</a>
      <a href="<%=request.getContextPath()%>/orderForm.jsp">Place Order</a>
      <a href="<%=request.getContextPath()%>/viewOrders.jsp" class="btn-nav">View Orders</a>
    </div>
  </nav>

  <div class="main-container">
    <h2>All Orders</h2>
    <div class="subtitle">Review and track all registered purchase records.</div>
    
    <div class="table-card">
      <table>
          <tr>
              <th>Order ID</th>
              <th>Customer</th>
              <th>Password</th>
              <th>Product</th>
              <th>Qty</th>
              <th>Price</th>
              <th>Total</th>
              <th>Date</th>
              <th>Address</th>
          </tr>
          <%
              OrderDAO dao = new OrderDAO();
              List<Order> orders = dao.getAllOrders();
              for (Order o : orders) {
          %>
          <tr>
              <td><%= o.getOrderId() %></td>
              <td><%= o.getCustomerName() %></td>
              <td><%= o.getPassword() %></td>
              <td><%= o.getProductName() %></td>
              <td><%= o.getQuantity() %></td>
              <td><%= o.getPrice() %></td>
              <td><%= o.getTotalAmount() %></td>
              <td><%= o.getOrderDate() %></td>
              <td><%= o.getAddress() %></td>
          </tr>
          <%
              }
          %>
      </table>
    </div>
  </div>
</body>
</html>