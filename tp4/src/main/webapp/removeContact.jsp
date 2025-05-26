<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Remove Contact</title>
</head>
<body>

    <h2>Remove Contact by ID</h2>

    <form action="ControllerServlet" method="POST">
        <input type="hidden" name="do_this" value="delete"/>

        <label for="contact_id">Enter Contact ID to Delete:</label>
        <input type="number" name="contact_id" id="contact_id" required/>

        <br/><br/>
        <input type="submit" value="Delete Contact"/>
    </form>

    <br/>
    <a href="ControllerServlet">Back to Contact List</a>

</body>
</html>
