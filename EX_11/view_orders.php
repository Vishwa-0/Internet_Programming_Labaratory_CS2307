<?php
  include "db_connect.php";
 
  $sql = "SELECT * FROM orders ORDER BY order_id DESC";
  $result = $conn->query($sql);
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Orders - Modern Dashboard</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Inter', sans-serif;
            background-color: #f8fafc;
            color: #1e293b;
            min-height: 100vh;
            padding: 2rem 1rem;
        }

        .container {
            max-width: 1280px;
            margin: 0 auto;
            background: #ffffff;
            padding: 2rem;
            border-radius: 1rem;
            box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1);
            border: 1px solid #d1fae5;
        }

        h2 {
            font-size: 1.5rem;
            font-weight: 700;
            color: #0f172a;
            margin-bottom: 1.5rem;
        }

        .table-container {
            overflow-x: auto;
            margin-bottom: 1.5rem;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            font-size: 0.875rem;
            text-align: left;
        }

        th {
            background-color: #f1f5f9;
            color: #475569;
            font-weight: 600;
            padding: 0.875rem 1rem;
            border-bottom: 1px solid #cbd5e1;
        }

        td {
            padding: 0.875rem 1rem;
            border-bottom: 1px solid #e2e8f0;
            color: #334155;
        }

        tr:hover {
            background-color: #f8fafc;
        }

        .total-col {
            font-weight: 600;
            color: #059669;
        }

        .btn {
            display: inline-block;
            padding: 0.75rem 1.5rem;
            background-color: #059669;
            color: white;
            text-decoration: none;
            border-radius: 0.75rem;
            font-weight: 600;
            transition: background-color 0.2s;
            box-shadow: 0 4px 6px -1px rgba(5, 150, 105, 0.2);
        }

        .btn:hover {
            background-color: #047857;
        }

        .empty-row {
            text-align: center;
            color: #94a3b8;
            padding: 2rem;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>All Orders</h2>
        
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Customer</th>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total Amount</th>
                        <th>Order Date</th>
                        <th>Shipping Address</th>
                    </tr>
                </thead>
                <tbody>
                    <?php if ($result->num_rows > 0) { ?>
                        <?php while ($row = $result->fetch_assoc()) { ?>
                            <tr>
                                <td><?php echo $row['order_id']; ?></td>
                                <td><?php echo $row['customer_name']; ?></td>
                                <td><?php echo $row['product_name']; ?></td>
                                <td><?php echo $row['quantity']; ?></td>
                                <td>$<?php echo number_format($row['price'], 2); ?></td>
                                <td class="total-col">$<?php echo number_format($row['total_amount'], 2); ?></td>
                                <td><?php echo $row['order_date']; ?></td>
                                <td><?php echo nl2br($row['address']); ?></td>
                            </tr>
                        <?php } ?>
                    <?php } else { ?>
                        <tr><td colspan="8" class="empty-row">No orders found.</td></tr>
                    <?php } ?>
                </tbody>
            </table>
        </div>

        <a href="index.html" class="btn">Place Another Order</a>
    </div>
</body>
</html>
<?php $conn->close(); ?>