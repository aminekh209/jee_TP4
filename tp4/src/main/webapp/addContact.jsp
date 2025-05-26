<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter un contact</title>
</head>
<body>
    <h2 align="center">Ajouter un nouveau contact</h2>

    <form action="ControllerServlet" method="POST">
        <input type="hidden" name="do_this" value="create" />

        <table align="center" cellpadding="10" border="0">
            <tr>
                <td colspan="2" align="center">
                    <font size="4">Veuillez remplir les informations</font>
                </td>
            </tr>
            <tr>
                <td>Prénom</td>
                <td><input type="text" name="firstName" required /></td>
            </tr>
            <tr>
                <td>Nom</td>
                <td><input type="text" name="lastName" required /></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="email" name="email" required /></td>
            </tr>
            <tr>
                <td>Téléphone</td>
                <td><input type="text" name="phone" required /></td>
            </tr>
            <tr>
                <td>Adresse</td>
                <td><input type="text" name="address" required /></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Enregistrer" /></td>
            </tr>
        </table>
    </form>
</body>
</html>
