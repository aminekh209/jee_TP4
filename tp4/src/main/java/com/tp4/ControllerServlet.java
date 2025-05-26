package com.tp4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ContactFacade contactFacade = new ContactFacade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String do_this = request.getParameter("do_this");

        if (do_this == null) {
        	List<Contact> contacts = contactFacade.getAllContacts();
        	System.out.println("Nombre de contacts: " + contacts.size());
        	request.setAttribute("listContacts", contacts);  // Au lieu de getSession().setAttribute()
            RequestDispatcher rd = request.getRequestDispatcher("/acceuil.jsp");
            rd.forward(request, response);

        } else if ("create".equals(do_this)) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            if (lastName == null || lastName.isEmpty()) {
                response.sendRedirect("addContact.jsp");
            } else {
                Contact contact = new Contact(firstName, lastName, email, phone, address);
                contactFacade.addContact(contact);
                response.sendRedirect("ControllerServlet");
            }

        } else if ("delete".equals(do_this)) {
            String id = request.getParameter("contact_id");
            if (id == null || id.isEmpty()) {
                // Recharger la liste avant de rediriger
                List<Contact> contacts = contactFacade.getAllContacts();
                request.setAttribute("listContacts", contacts);
                request.getRequestDispatcher("removeContact.jsp").forward(request, response);
            } else {
                try {
                    // Appeler la méthode pour supprimer le contact
                    boolean isDeleted = contactFacade.deleteContact(Integer.parseInt(id));
                    if (isDeleted) {
                        // Si la suppression est réussie, recharger la liste des contacts
                        List<Contact> updatedContacts = contactFacade.getAllContacts();
                        request.setAttribute("listContacts", updatedContacts);
                    }
                    // Rediriger vers la page de suppression
                    request.getRequestDispatcher("removeContact.jsp").forward(request, response);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    response.sendRedirect("removeContact.jsp");
                }
            }
        }
          
    else if ("search".equals(do_this)) {
            String keyword = request.getParameter("keyword");
            if (keyword != null && !keyword.isEmpty()) {
                // Appeler la méthode de recherche
                List<Contact> results = contactFacade.searchContact(keyword);
                request.setAttribute("searchResults", results);
            } else {
                // Si aucun mot-clé n'est fourni, rediriger vers la page de recherche
                request.setAttribute("searchResults", new ArrayList<Contact>());
            }
            // Rediriger vers la page de résultats de recherche
            RequestDispatcher rd = request.getRequestDispatcher("/searchContact.jsp");
            rd.forward(request, response);
        }
        
        	    
    else if ("showUpdateForm".equals(do_this)) {
        	        // Étape 1: Afficher le formulaire de sélection d'ID
        	        List<Contact> contacts = contactFacade.getAllContacts();
        	        request.setAttribute("listContacts", contacts);
        	        request.getRequestDispatcher("/selectContactToUpdate.jsp").forward(request, response);
        	        
        	    } else if ("loadContactForUpdate".equals(do_this)) {
        	        // Étape 2: Charger le contact sélectionné
        	        int id = Integer.parseInt(request.getParameter("contact_id"));
        	        Contact contact = contactFacade.findContactById(id);
        	        request.setAttribute("contactToUpdate", contact);
        	        request.getRequestDispatcher("/updateContactForm.jsp").forward(request, response);
        	        
        	    } else if ("processUpdate".equals(do_this)) {
        	        try {
        	            // Récupérer les paramètres envoyés par le formulaire
        	            int id = Integer.parseInt(request.getParameter("contact_id"));
        	            String firstName = request.getParameter("firstName");
        	            String lastName = request.getParameter("lastName");
        	            String email = request.getParameter("email");
        	            String phone = request.getParameter("phone");
        	            String address = request.getParameter("address");

        	            // Créer un objet Contact avec les nouvelles informations
        	            Contact updatedContact = new Contact(firstName, lastName, email, phone, address);

        	            // Appeler la méthode updateContact avec l'objet Contact et l'ID
        	            contactFacade.updateContact(updatedContact, id);

        	            // Afficher un message dans la console pour confirmer la mise à jour
        	            System.out.println("La mise à jour a été effectuée avec succès.");

        	            // Rediriger vers la page d'accueil après la mise à jour
        	            response.sendRedirect("ControllerServlet?do_this=showHomePage");

        	        } catch (Exception e) {
        	            e.printStackTrace();
        	            response.sendRedirect("ControllerServlet?do_this=showUpdateForm");
        	        }
        	    }

        	}

    }

