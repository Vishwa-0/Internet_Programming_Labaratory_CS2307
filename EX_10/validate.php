<?php
$errors = [];
$success = false;

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $username = trim($_POST['username'] ?? '');
    $password = $_POST['password'] ?? '';
    $name = trim($_POST['name'] ?? '');
    $ccnumber = trim($_POST['ccnumber'] ?? '');
    $email = trim($_POST['email'] ?? '');
    $phone = trim($_POST['phone'] ?? '');

    if (empty($username)) {
        $errors[] = "Username is required.";
    }

    if (empty($password)) {
        $errors[] = "Password is required.";
    }

    if (empty($name)) {
        $errors[] = "Full Name is required.";
    }

    if (!preg_match('/^\d{13,19}$/', $ccnumber)) {
        $errors[] = "Invalid credit card number. It must contain 13 to 19 digits.";
    }

    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        $errors[] = "Invalid email address format.";
    }

    if (!preg_match('/^[0-9]{10}$/', $phone)) {
        $errors[] = "Invalid phone number. It must be exactly 10 digits.";
    }

    if (empty($errors)) {
        $success = true;
        $masked_cc = '•••• •••• •••• ' . substr($ccnumber, -4);
        $masked_password = str_repeat('•', strlen($password));
    }
} else {
    header("Location: index.html");
    exit();
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Result</title>
    <style>
        * {
            box-sizing: border-box;
        }

        body { 
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif; 
            background-color: #f1f5f9; 
            color: #334155;
            margin: 0;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .form-container {
            width: 100%;
            max-width: 420px; 
            background: #ffffff; 
            padding: 36px; 
            border-radius: 16px;
            box-shadow: 0 10px 25px -5px rgba(0, 0, 0, 0.08), 0 8px 10px -6px rgba(0, 0, 0, 0.04);
            border: 1px solid #e2e8f0;
            position: relative;
            overflow: hidden;
        }

        .form-container::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            height: 4px;
            background: linear-gradient(90deg, #10b981, #34d399, #6ee7b7);
        }

        h2 { 
            margin: 0 0 6px 0; 
            color: #0f172a; 
            font-size: 22px;
            font-weight: 600;
        }

        .subtitle {
            color: #64748b;
            font-size: 14px;
            margin-bottom: 24px;
        }

        .alert-success {
            background-color: #ecfdf5;
            border: 1px solid #a7f3d0;
            color: #065f46;
            padding: 12px 16px;
            border-radius: 6px;
            margin-bottom: 20px;
            font-size: 14px;
            font-weight: 500;
        }

        .alert-error {
            background-color: #fef2f2;
            border: 1px solid #fecaca;
            color: #991b1b;
            padding: 12px 16px;
            border-radius: 6px;
            margin-bottom: 20px;
            font-size: 14px;
        }

        .info-group {
            margin-bottom: 14px;
            font-size: 14px;
            border-bottom: 1px solid #f1f5f9;
            padding-bottom: 8px;
        }

        .info-group label {
            font-weight: 600;
            color: #475569;
            display: inline-block;
            width: 130px;
        }

        .back-btn {
            display: block;
            width: 100%;
            margin-top: 24px;
            padding: 11px;
            background-color: #10b981;
            color: white;
            text-align: center;
            text-decoration: none;
            border-radius: 6px;
            font-size: 14px;
            font-weight: 500;
            transition: background-color 0.15s ease;
        }

        .back-btn:hover { 
            background-color: #059669; 
        }
    </style>
</head>
<body>
    <div class="form-container">
        <?php if ($success): ?>
            <h2>Registration Successful</h2>
            <div class="subtitle">Here is the information you provided:</div>
            
            <div class="alert-success">
                Your account has been registered successfully!
            </div>

            <div class="info-group">
                <label>User Name:</label> <span><?php echo htmlspecialchars($username); ?></span>
            </div>
            <div class="info-group">
                <label>Full Name:</label> <span><?php echo htmlspecialchars($name); ?></span>
            </div>
            <div class="info-group">
                <label>Email:</label> <span><?php echo htmlspecialchars($email); ?></span>
            </div>
            <div class="info-group">
                <label>Phone Number:</label> <span><?php echo htmlspecialchars($phone); ?></span>
            </div>
            <div class="info-group">
                <label>Credit Card:</label> <span><?php echo htmlspecialchars($masked_cc); ?></span>
            </div>
            <div class="info-group">
                <label>Password:</label> <span><?php echo htmlspecialchars($masked_password); ?></span>
            </div>

            <a href="javascript:history.back()" class="back-btn">Go Back</a>

        <?php else: ?>
            <h2>Registration Failed</h2>
            <div class="subtitle">Please fix the following errors:</div>
            
            <div class="alert-error">
                <ul style="margin: 0; padding-left: 20px;">
                    <?php foreach ($errors as $error): ?>
                        <li><?php echo htmlspecialchars($error); ?></li>
                    <?php endforeach; ?>
                </ul>
            </div>

            <a href="javascript:history.back()" class="back-btn">Return to Form</a>
        <?php endif; ?>
    </div>
</body>
</html>