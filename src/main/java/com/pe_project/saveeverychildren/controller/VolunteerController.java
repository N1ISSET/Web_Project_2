package com.pe_project.saveeverychildren.controller;

import com.pe_project.saveeverychildren.models.volunteer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.pe_project.saveeverychildren.common.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Controller
public class VolunteerController {

    private Connection conn = null;
    private PreparedStatement pst = null;
    private DatabaseConnection db = null;

    public VolunteerController() {
        db = new DatabaseConnection();
        conn = db.getConnection(); // Assuming this does not throw SQLException directly
    }


    @GetMapping(value = {"/volunteer", "/volunteer.html"})
    public String volunteerPage(Model model) {
        model.addAttribute("volunteer", new volunteer());
        return "volunteer"; // Returns the volunteer form page
    }

    @PostMapping("/volunteer")
    public String submitVolunteer(@ModelAttribute volunteer volunteer, Model model) {
        try {
            db = new DatabaseConnection();
            conn = db.getConnection(); // Assuming this does not throw SQLException directly

            String query = "INSERT INTO volunteer (first_name, last_name, age, mobile, email, city, country) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(query);

            pst.setString(1, volunteer.getFirst_name());
            pst.setString(2, volunteer.getLast_name());
            pst.setInt(3, volunteer.getAge());
            pst.setInt(4, volunteer.getMobile());
            pst.setString(5, volunteer.getEmail());
            pst.setString(6, volunteer.getCity());
            pst.setString(7, volunteer.getCountry());

            int resultCount = pst.executeUpdate();

            if (resultCount > 0) {
                // Add a success message to the model
                model.addAttribute("volunteerResult", volunteer);
                return "volunteer_response";
            }else {
                return "volunteer";

            }

        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "There was an error submitting your details. Please try again.");
            return "volunteer"; // Return to the form page with an error message
        }
    }

}

