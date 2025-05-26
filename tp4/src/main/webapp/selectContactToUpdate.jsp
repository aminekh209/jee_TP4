
<form action="ControllerServlet" method="post">
    <input type="hidden" name="do_this" value="loadContactForUpdate">

    <!-- Saisie de l'ID du contact -->
    <label>Entrez l'ID du contact à mettre à jour:</label>
    <input type="text" name="contact_id" required> <!-- Vérifie que le nom est bien "contact_id" -->

    <button type="submit">Mettre à jour</button>
</form>
