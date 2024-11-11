<%@ page isErrorPage="true" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Error</title>
  <link rel="stylesheet" href="path/to/your/styles.css"> <!-- Link to your CSS file -->
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 20px;
      background-color: #f4f4f4;
    }
    .container {
      max-width: 800px;
      margin: 0 auto;
      padding: 20px;
      background-color: #fff;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    h1 {
      color: #d9534f;
    }
    .error-details {
      background-color: #f9f9f9;
      border: 1px solid #d9534f;
      padding: 10px;
      border-radius: 5px;
      margin-bottom: 20px;
    }
    .error-details ul {
      padding: 0;
      list-style-type: none;
    }
    .error-details li {
      margin-bottom: 10px;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Error</h1>
  <p>Sorry, something went wrong. Please see the details below:</p>

  <!-- Display list of error messages -->
  <div class="error-details">
    <h2>Error Details</h2>
    <ul>
      <%
        List<String> errorMessages = (List<String>) request.getAttribute("errorMessages");
        if (errorMessages != null && !errorMessages.isEmpty()) {
          for (String errorMsg : errorMessages) {
      %>
      <li><%= errorMsg %></li>
      <%
        }
      } else {
      %>
      <li>No error details available.</li>
      <%
        }
      %>
    </ul>
  </div>

  <p><a href="#">Go back to home</a></p>
</div>
</body>
</html>
