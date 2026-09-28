<?php
  include "db_connect.php";
 
  $customer_name = $_POST['customer_name'];
  $product_name  = $_POST['product_name'];
  $quantity      = $_POST['quantity'];
  $price         = $_POST['price'];
  $total_amount  = $_POST['total_amount'];
  $order_date    = $_POST['order_date'];
  $address       = $_POST['address'];
 
  $sql = "INSERT INTO orders (customer_name, product_name, quantity, price, total_amount, order_date, address)
          VALUES (?, ?, ?, ?, ?, ?, ?)";
 
  $stmt = $conn->prepare($sql);
  $stmt->bind_param("ssidsss", $customer_name, $product_name, $quantity, $price, $total_amount, $order_date, $address);
 
  $success = false;
  $error_message = "";
 
  if ($stmt->execute()) {
      $success = true;
  } else {
      $error_message = $stmt->error;
  }
 
  $stmt->close();
  $conn->close();
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Status - Modern Dashboard</title>
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
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 1rem;
        }

        .card {
            width: 100%;
            max-width: 28rem;
            background: #ffffff;
            padding: 2.5rem 2rem;
            border-radius: 1rem;
            box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1);
            border: 1px solid #d1fae5;
            text-align: center;
        }

        .icon-box {
            width: 3.5rem;
            height: 3.5rem;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 1.25rem auto;
        }

        .success-icon {
            background-color: #ecfdf5;
            color: #059669;
        }

        .error-icon {
            background-color: #fef2f2;
            color: #dc2626;
        }

        .icon-box svg {
            width: 1.75rem;
            height: 1.75rem;
            stroke: currentColor;
            fill: none;
            stroke-width: 2;
        }

        h2 {
            font-size: 1.375rem;
            font-weight: 700;
            color: #0f172a;
            margin-bottom: 0.5rem;
        }

        p {
            font-size: 0.875rem;
            color: #475569;
            margin-bottom: 1.75rem;
            line-height: 1.5;
        }

        .btn-group {
            display: flex;
            flex-direction: column;
            gap: 0.75rem;
        }

        .btn {
            display: block;
            width: 100%;
            padding: 0.75rem 1rem;
            border-radius: 0.75rem;
            font-weight: 600;
            font-size: 0.875rem;
            text-decoration: none;
            transition: background-color 0.2s;
            cursor: pointer;
        }

        .btn-primary {
            background-color: #059669;
            color: white;
            box-shadow: 0 4px 6px -1px rgba(5, 150, 105, 0.2);
        }

        .btn-primary:hover {
            background-color: #047857;
        }

        .btn-secondary {
            background-color: #f1f5f9;
            color: #334155;
        }

        .btn-secondary:hover {
            background-color: #e2e8f0;
        }
    </style>
</head>
<body>
    <div class="card">
        <?php if ($success) { ?>
            <div class="icon-box success-icon">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M5 13l4 4L19 7" />
                </svg>
            </div>
            <h2>Order Placed Successfully!</h2>
            <p>Your order details have been securely saved to the database.</p>
            <div class="btn-group">
                <a href="view_orders.php" class="btn btn-primary">View All Orders</a>
                <a href="index.html" class="btn btn-secondary">Place Another Order</a>
            </div>
        <?php } else { ?>
            <div class="icon-box error-icon">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
                </svg>
            </div>
            <h2>Something Went Wrong</h2>
            <p><?php echo htmlspecialchars($error_message); ?></p>
            <div class="btn-group">
                <a href="index.html" class="btn btn-primary">Try Again</a>
            </div>
        <?php } ?>
    </div>
</body>
</html>