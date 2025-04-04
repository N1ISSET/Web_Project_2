package com.pe_project.saveeverychildren.controller;

import com.pe_project.saveeverychildren.models.donation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.pe_project.saveeverychildren.common.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Controller
public class DonationController {
    private Connection conn = null;
    private PreparedStatement pst = null;
    private DatabaseConnection db = null;

    public DonationController() {
        db = new DatabaseConnection();
        conn = db.getConnection(); // Assuming this does not throw SQLException directly
    }

    @GetMapping(value = {"/donation", "/donation.html"})
    public String donationPage(Model model) {
        model.addAttribute("donation", new donation());
        return "donation"; // Returns the donation form page
    }

    @PostMapping("/donation")
    public String submitDonation(@ModelAttribute donation donation, Model model) {
        try {
            db = new DatabaseConnection();
            conn = db.getConnection(); // Assuming this does not throw SQLException directly

            String query = "INSERT INTO donation (amount, first_name, last_name, email, mobile, card_number, donation_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(query);

            pst.setInt(1, donation.getAmount());
            pst.setString(2, donation.getFirst_name());
            pst.setString(3, donation.getLast_name());
            pst.setString(4, donation.getEmail());
            pst.setInt(5, donation.getMobile());
            pst.setInt(6, donation.getCard_number());
            LocalDateTime currentDateTime = LocalDateTime.now();
            pst.setTimestamp(7, java.sql.Timestamp.valueOf(currentDateTime));


            int resultCount = pst.executeUpdate();

            if (resultCount > 0) {
                // Add a success message to the model
                model.addAttribute("donationResult", donation);
                return "donation_response";
            }else {
                return "donation";

            }

        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "There was an error submitting your details. Please try again.");
            return "donation"; // Return to the form page with an error message
        }
    }
}
