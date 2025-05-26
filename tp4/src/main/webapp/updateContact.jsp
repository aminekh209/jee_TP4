<form action="ControllerServlet" method="POST">
    <input type="hidden" name="do_this" value="saveUpdate"/>
    <input type="hidden" name="contact_id" value="${contact.contactId}"/>

    <table>
        <tr>
            <td>First Name:</td>
            <td><input type="text" name="firstName" value="${contact.firstname}" required/></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><input type="text" name="lastName" value="${contact.lastname}" required/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="email" name="email" value="${contact.email}" required/></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><input type="text" name="phone" value="${contact.phone}" required/></td>
        </tr>
        <tr>
            <td>Address:</td>
            <td><input type="text" name="address" value="${contact.address}" required/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Update Contact"/></td>
        </tr>
    </table>
</form>
