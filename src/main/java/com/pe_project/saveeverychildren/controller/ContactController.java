package com.pe_project.saveeverychildren.controller;

import com.pe_project.saveeverychildren.models.contact;
import com.pe_project.saveeverychildren.common.DatabaseConnection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class ContactController {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    DatabaseConnection db = null;

    @RequestMapping(value = {"/contact", "/contact.html"})
    public String contact(Model model) {
        return "contact";
    }


    @GetMapping(value = {"/contact", "/contact.html"})
    public String contactPage(Model model) {
        model.addAttribute("contact", new contact());
        return "contact"; // Returns the volunteer form page
    }

    @PostMapping("/contact")
    public String submitVolunteer(@ModelAttribute contact contact, Model model) {
        try {
            db = new DatabaseConnection();
            conn = db.getConnection(); // Assuming this does not throw SQLException directly

            String query = "INSERT INTO contact (first_name, last_name, email, messages) VALUES (?, ?, ?, ?)";
            pst = conn.prepareStatement(query);

            pst.setString(1, contact.getFirst_name());
            pst.setString(2, contact.getLast_name());
            pst.setString(3, contact.getEmail());
            pst.setString(4, contact.getMessages());

            int resultCount = pst.executeUpdate();

            if (resultCount > 0) {
                // Add a success message to the model
                model.addAttribute("contactResult", contact);
                return "contact_response";
            } else {
                return "contact";

            }

        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "There was an error submitting your details. Please try again.");
            return "contact"; // Return to the form page with an error message
        }
    }
}
