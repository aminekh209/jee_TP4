<form action="ControllerServlet" method="post">
    <input type="hidden" name="do_this" value="processUpdate">
    <input type="hidden" name="id" value="${contactToUpdate.id}">
    <div>
        <label>id:</label>
        <input type="text" name="contact_id" value="${contactToUpdate.id}" required>
    </div>
    
    <div>
        <label>Prénom:</label>
        <input type="text" name="firstName" value="${contactToUpdate.firstname}" required>
    </div>
    
    <div>
        <label>Nom:</label>
        <input type="text" name="lastName" value="${contactToUpdate.lastname}" required>
    </div>
    
    <div>
        <label>Email:</label>
        <input type="email" name="email" value="${contactToUpdate.email}">
    </div>
    
    <div>
        <label>Téléphone:</label>
        <input type="text" name="phone" value="${contactToUpdate.phone}">
    </div>
    
    <div>
        <label>Adresse:</label>
        <input type="text" name="address" value="${contactToUpdate.address}">
    </div>
    
    <button type="submit">Mettre à jour</button>
</form>