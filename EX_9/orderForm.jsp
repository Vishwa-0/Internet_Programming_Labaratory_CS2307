<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ShopEase - Place Order</title>
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
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 40px 20px;
  }
  .form-card {
    background: #ffffff;
    padding: 36px;
    border-radius: 16px;
    box-shadow: 0 10px 25px -5px rgba(22, 101, 52, 0.05), 0 8px 10px -6px rgba(22, 101, 52, 0.03);
    border: 1px solid #dcfce7;
    width: 100%;
    max-width: 480px;
    position: relative;
    overflow: hidden;
  }
  .form-card::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    height: 4px;
    background: linear-gradient(90deg, #16a34a, #22c55e, #4ade80);
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
  label {
    display: block;
    margin-top: 14px;
    margin-bottom: 6px;
    font-size: 13px;
    font-weight: 500;
    color: #15803d;
  }
  input[type="text"],
  input[type="password"],
  input[type="number"],
  input[type="date"],
  select,
  textarea {
    width: 100%;
    padding: 10px 12px;
    border: 1px solid #bbf7d0;
    border-radius: 6px;
    font-size: 14px;
    background-color: #ffffff;
    color: #14532d;
    transition: border-color 0.15s ease, box-shadow 0.15s ease;
  }
  select option {
    background-color: #ffffff;
    color: #14532d;
  }
  textarea {
    resize: vertical;
  }
  input:focus, select:focus, textarea:focus {
    outline: none;
    border-color: #16a34a;
    box-shadow: 0 0 0 3px rgba(22, 163, 74, 0.12);
  }
  input[readonly] {
    background-color: #f0fdf4;
    color: #15803d;
  }
  input::placeholder, textarea::placeholder {
    color: #86efac;
  }
  input[type="submit"] {
    width: 100%;
    margin-top: 24px;
    padding: 11px;
    background-color: #16a34a;
    color: white;
    border: none;
    border-radius: 6px;
    font-size: 14px;
    font-weight: 500;
    cursor: pointer;
    transition: background-color 0.15s ease;
  }
  input[type="submit"]:hover {
    background-color: #15803d;
  }
</style>
<script>
    const productPrices = {
        "Wireless Headphones": 79.99,
        "Smart Watch": 149.50,
        "Running Shoes": 59.99,
        "Mechanical Keyboard": 89.00,
        "Gaming Mouse": 45.25
    };

    function updatePriceAndTotal() {
        let productSelect = document.getElementById("productName");
        let priceField = document.getElementById("price");
        let selectedProduct = productSelect.value;

        if (selectedProduct && productPrices[selectedProduct] !== undefined) {
            priceField.value = productPrices[selectedProduct].toFixed(2);
        } else {
            priceField.value = "";
        }

        calculateTotal();
    }

    function calculateTotal() {
        let quantity = document.getElementById("quantity").value;
        let price = document.getElementById("price").value;
        let totalField = document.getElementById("totalAmountDisplay");
        
        if (quantity && price) {
            let total = (parseFloat(quantity) * parseFloat(price)).toFixed(2);
            totalField.value = total;
        } else {
            totalField.value = "";
        }
    }
</script>
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
    <div class="form-card">
      <h2>Place an Order</h2>
      <div class="subtitle">Enter your order details below.</div>
      
      <form action="AddOrderServlet" method="post">
          <label for="customerName">Name</label>
          <input type="text" id="customerName" name="customerName" placeholder="johndoe" required/>

          <label for="password">Password</label>
          <input type="password" id="password" name="password" placeholder="••••••••" required/>

          <label for="productName">Product</label>
          <select id="productName" name="productName" required onchange="updatePriceAndTotal()">
              <option value="" disabled selected>-- Select a Product --</option>
              <option value="Wireless Headphones">Wireless Headphones</option>
              <option value="Smart Watch">Smart Watch</option>
              <option value="Running Shoes">Running Shoes</option>
              <option value="Mechanical Keyboard">Mechanical Keyboard</option>
              <option value="Gaming Mouse">Gaming Mouse</option>
          </select>

          <label for="quantity">Quantity</label>
          <input type="number" id="quantity" name="quantity" min="1" placeholder="1" required oninput="calculateTotal()"/>

          <label for="price">Price</label>
          <input type="number" id="price" name="price" step="0.01" placeholder="0.00" readonly required/>

          <label for="totalAmountDisplay">Total Amount</label>
          <input type="text" id="totalAmountDisplay" placeholder="0.00" readonly/>

          <label for="orderDate">Order Date</label>
          <input type="date" id="orderDate" name="orderDate" required/>

          <label for="address">Address</label>
          <textarea id="address" name="address" rows="3" placeholder="123 Main St, City"></textarea>

          <input type="submit" value="Place Order"/>
      </form>
    </div>
  </div>
</body>
</html>