<%@ page import="com.tp4.Contact" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>





<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Accueil</title>
</head>
<body>
    <h1>Liste des contacts : </h1>
    <table border="2">
        <tr>
            
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Address</th>
        </tr>

        <!-- Boucle JSTL pour afficher les contacts -->
     <c:forEach var="contact" items="${requestScope.listContacts}">
        <tr>
        
        <td>${contact.firstname}</td> <!-- prénom -->
        <td>${contact.lastname}</td> <!-- nom -->
        <td>${contact.email}</td>
        <td>${contact.phone}</td>
        <td>${contact.address}</td>
        </tr>
   </c:forEach>
    </table>

    <!-- Liens pour créer, supprimer, modifier et rechercher des contacts -->
    <a href="ControllerServlet?do_this=create">Créer un nouveau contact</a><br>
    <a href="ControllerServlet?do_this=delete">Supprimer un Contact</a><br>
    <a href="ControllerServlet?do_this=showUpdateForm">Modifier un Contact</a><br>
    <a href="ControllerServlet?do_this=search">Rechercher un contact</a><br>
</body>
</html>
