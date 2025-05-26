<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Remove Contact</title>
</head>
<body>

    <h2>Remove Contact</h2>

    <c:choose>
        <c:when test="${not empty listContacts}">
            <form action="ControllerServlet" method="POST">
                <input type="hidden" name="do_this" value="delete"/>
                
                <label for="contact_id">Select Contact to Delete:</label>
                <select name="contact_id" id="contact_id" required>
                    <option value="" disabled selected>Select a contact</option>
                  
                    <c:forEach var="contact" items="${listContacts}">
                        <option value="${contact.id}">${contact.firstname} ${contact.lastname}</option>
                    </c:forEach>
                </select>
                
                <br/><br/>
                <input type="submit" value="Delete Contact"/>
            </form>
        </c:when>
        <c:otherwise>
            <p>No contacts available for deletion</p>
        </c:otherwise>
    </c:choose>

    <br/>
    <a href="ControllerServlet">Back to Contact List</a>

</body>
</html>