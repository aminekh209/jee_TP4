<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>
</head>
<body>

<h2>Search Results</h2>

<form action="ControllerServlet" method="POST">
    <input type="text" name="keyword" placeholder="Search..." required />
    <input type="hidden" name="do_this" value="search" />
    <input type="submit" value="Search" />
</form>

<c:choose>
    <c:when test="${not empty searchResults}">
        <table border="1">
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
            </tr>
            <c:forEach var="contact" items="${searchResults}">
                <tr>
                    <td>${contact.firstname}</td>
                    <td>${contact.lastname}</td>
                    <td>${contact.email}</td>
                    <td>${contact.phone}</td>
                    <td>${contact.address}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <p>No contacts found for your search.</p>
    </c:otherwise>
</c:choose>

</body>
</html>