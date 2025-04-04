package com.pe_project.saveeverychildren.controller;

import com.pe_project.saveeverychildren.common.DatabaseConnection;
import com.pe_project.saveeverychildren.models.admin;
import com.pe_project.saveeverychildren.models.volunteer;
import com.pe_project.saveeverychildren.models.donation;
import com.pe_project.saveeverychildren.models.contact;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Controller
public class AdminController {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    DatabaseConnection db = null;

    public AdminController() {
        db = new DatabaseConnection();
        conn = db.getConnection();
    }

    @PostMapping("/new_admin")
    public String createNewAdmin(
            @RequestParam("admin_name") String adminName,
            @RequestParam("password") String password,
            @RequestParam("confirm_password") String confirmPassword,
            Model model) {

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "new_admin"; // Redirect back to the form with an error message
        }

        try {
            String query = "INSERT INTO admin (admin_name, password) VALUES (?, ?)";
            pst = conn.prepareStatement(query);
            pst.setString(1, adminName);
            pst.setString(2, password);

            int resultCount = pst.executeUpdate();
            if (resultCount > 0) {
                model.addAttribute("message", "New admin created successfully!");
                return "redirect:/admin_management"; // Redirect to admin management page
            } else {
                model.addAttribute("error", "Failed to create new admin. Please try again.");
                return "new_admin";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while creating a new admin.");
            return "new_admin";
        }
    }

    public List<admin> readAdmin() {
        List<admin> adminList = new ArrayList<>();
        try {
            String query = "SELECT * FROM admin";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                admin admin = new admin();
                admin.setAdmin_id(rs.getInt("admin_id"));
                admin.setAdmin_name(rs.getString("admin_name"));
                admin.setPassword(rs.getString("password"));

                adminList.add(admin);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return adminList;
    }


    public List<volunteer> readVolunteer() {
        List<volunteer> volunteerList = new ArrayList<>();
        try {
            String query = "SELECT * FROM volunteer";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                volunteer volunteer = new volunteer();
                volunteer.setVolunteer_id(rs.getInt("volunteer_id"));
                volunteer.setFirst_name(rs.getString("first_name"));
                volunteer.setLast_name(rs.getString("last_name"));
                volunteer.setAge(rs.getInt("age"));
                volunteer.setEmail(rs.getString("email"));
                volunteer.setMobile(rs.getInt("mobile"));
                volunteer.setCity(rs.getString("city"));
                volunteer.setCountry(rs.getString("country"));

                volunteerList.add(volunteer);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return volunteerList;
    }

    public List<donation> readDonation() {
        List<donation> donationList = new ArrayList<>();
        try {
            String query = "SELECT * FROM donation";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                donation donation = new donation();
                donation.setDonation_id(rs.getInt("donation_id"));
                donation.setFirst_name(rs.getString("first_name"));
                donation.setLast_name(rs.getString("last_name"));
                donation.setEmail(rs.getString("email"));
                donation.setMobile(rs.getInt("mobile"));
                donation.setAmount(rs.getInt("amount"));
                donation.setCard_number(rs.getInt("card_number"));
                donation.setDonation_date(rs.getDate("donation_date"));

                donationList.add(donation);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return donationList;
    }

    public List<contact> readContact() {
        List<contact> contactList = new ArrayList<>();
        try {
            String query = "SELECT * FROM contact";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                contact contact = new contact();
                contact.setContact_id(rs.getInt("contact_id"));
                contact.setFirst_name(rs.getString("first_name"));
                contact.setLast_name(rs.getString("last_name"));
                contact.setEmail(rs.getString("email"));
                contact.setMessages(rs.getString("messages"));

                contactList.add(contact);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return contactList;
    }

    @GetMapping("/admin_edit")
    public String editAdmin(@RequestParam int id, Model model) {
        try {
            String query = "SELECT * FROM admin where admin_id = " + id;
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            admin admin = new admin();

            while (rs.next()) {
                admin.setAdmin_id(rs.getInt("admin_id"));
                admin.setAdmin_name(rs.getString("admin_name"));
                admin.setPassword(rs.getString("password"));
            }

            model.addAttribute("admin", admin);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "admin_edit";
    }

    @PostMapping("/admin_edit")
    public String editAdmin(
            @RequestParam("admin_id") int adminId,
            @RequestParam("admin_name") String adminName,
            @RequestParam("password") String password,
            Model model) {

        admin admin = new admin();
        admin.setAdmin_id(adminId);
        admin.setAdmin_name(adminName);
        admin.setPassword(password);

        try {
            String query = "update admin set admin_id = ?, admin_name = ?, password = ? WHERE admin_id = ?";
            pst = conn.prepareStatement(query);
            pst.setInt(1, adminId);
            pst.setString(2, adminName);
            pst.setString(3, password);
            pst.setInt(4, adminId);

            pst.executeUpdate();
        } catch (SQLException e) {
            model.addAttribute("error", "Error updating admin: " + e.getMessage());
            return "admin_edit";
        }

        return "redirect:/manage_admin";
    }

    @GetMapping("/volunteer_edit")
    public String editVolunteer(@RequestParam int id, Model model) {
        try {
            String query = "SELECT * FROM volunteer where volunteer_id = " + id;
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            volunteer volunteer = new volunteer();

            while (rs.next()) {
                volunteer.setVolunteer_id(rs.getInt("volunteer_id"));
                volunteer.setFirst_name(rs.getString("first_name"));
                volunteer.setLast_name(rs.getString("last_name"));
                volunteer.setAge(rs.getInt("age"));
                volunteer.setMobile(rs.getInt("mobile"));
                volunteer.setEmail(rs.getString("email"));
                volunteer.setCity(rs.getString("city"));
                volunteer.setCountry(rs.getString("country"));
            }

            model.addAttribute("volunteer", volunteer);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "volunteer_edit";
    }

    @PostMapping("/volunteer_edit")
    public String editVolunteer(
            @RequestParam("volunteer_id") int volunteerId,
            @RequestParam("first_name") String firstName,
            @RequestParam("last_name") String lastName,
            @RequestParam("age") int age,
            @RequestParam("mobile") int mobile,
            @RequestParam("email") String email,
            @RequestParam("city") String city,
            @RequestParam("country") String country,
            Model model) {

        volunteer volunteer = new volunteer();
        volunteer.setVolunteer_id(volunteerId);
        volunteer.setFirst_name(firstName);
        volunteer.setLast_name(lastName);
        volunteer.setAge(age);
        volunteer.setMobile(mobile);
        volunteer.setEmail(email);
        volunteer.setCity(city);
        volunteer.setCountry(country);

        try {
            String query = "update volunteer set first_name = ?, last_name = ?, age = ?, email = ?, mobile = ?, city = ?, country = ? WHERE volunteer_id = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setInt(3, age);
            pst.setString(4, email);
            pst.setInt(5, mobile);
            pst.setString(6, city);
            pst.setString(7, country);
            pst.setInt(8, volunteerId);

            pst.executeUpdate();
        } catch (SQLException e) {
            model.addAttribute("error", "Error updating volunteer: " + e.getMessage());
            return "volunteer_edit";
        }

        return "redirect:/volunteer_management";
    }

    @GetMapping("/donation_edit")
    public String editDonation(@RequestParam int id, Model model) {
        try {
            String query = "SELECT * FROM donation where donation_id = " + id;
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            donation donation = new donation();

            while (rs.next()) {
                donation.setDonation_id(rs.getInt("donation_id"));
                donation.setFirst_name(rs.getString("first_name"));
                donation.setLast_name(rs.getString("last_name"));
                donation.setEmail(rs.getString("email"));
                donation.setMobile(rs.getInt("mobile"));
                donation.setAmount(rs.getInt("amount"));
                donation.setCard_number(rs.getInt("card_number"));
                donation.setDonation_date(rs.getDate("donation_date"));
            }

            model.addAttribute("donation", donation);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "donation_edit";
    }

    @PostMapping("/donation_edit")
    public String editDonation(
            @RequestParam("donation_id") int donationId,
            @RequestParam("first_name") String firstName,
            @RequestParam("last_name") String lastName,
            @RequestParam("email") String email,
            @RequestParam("mobile") int mobile,
            @RequestParam("amount") int amount,
            @RequestParam("card_number") int card_number,
            @RequestParam("donation_date") Date donationDate,
            Model model) {

        donation donation = new donation();
        donation.setDonation_id(donationId);
        donation.setFirst_name(firstName);
        donation.setLast_name(lastName);
        donation.setEmail(email);
        donation.setMobile(mobile);
        donation.setAmount(amount);
        donation.setCard_number(card_number);
        donation.setDonation_date(donationDate);

        try {
            String query = "update donation set first_name = ?, last_name = ?, email = ?, mobile = ?, amount = ?, card_number = ?, donation_date = ? WHERE donation_id = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, email);
            pst.setInt(4, mobile);
            pst.setInt(5, amount);
            pst.setInt(6, card_number);
            pst.setDate(7, donationDate);
            pst.setInt(8, donationId);

            pst.executeUpdate();
        } catch (SQLException e) {
            model.addAttribute("error", "Error updating donation: " + e.getMessage());
            return "donation_edit";
        }

        return "redirect:/donation_management";
    }

    @GetMapping("/contact_edit")
    public String editContact(@RequestParam int id, Model model) {
        try {
            String query = "SELECT * FROM contact where contact_id = " + id;
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            contact contact = new contact();

            while (rs.next()) {
                contact.setContact_id(rs.getInt("contact_id"));
                contact.setFirst_name(rs.getString("first_name"));
                contact.setLast_name(rs.getString("last_name"));
                contact.setEmail(rs.getString("email"));
                contact.setMessages(rs.getString("messages"));
            }

            model.addAttribute("contact", contact);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "contact_edit";
    }

    @PostMapping("/contact_edit")
    public String editContact(
            @RequestParam("contact_id") int contactId,
            @RequestParam("first_name") String firstName,
            @RequestParam("last_name") String lastName,
            @RequestParam("email") String email,
            @RequestParam("messages") String messages,
            Model model) {

        contact contact = new contact();
        contact.setContact_id(contactId);
        contact.setFirst_name(firstName);
        contact.setLast_name(lastName);
        contact.setEmail(email);
        contact.setMessages(messages);

        try {
            String query = "update contact set first_name = ?, last_name = ?, email = ?, messages = ? WHERE contact_id = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, email);
            pst.setString(4, messages);
            pst.setInt(5, contactId);

            pst.executeUpdate();
        } catch (SQLException e) {
            model.addAttribute("error", "Error updating contact: " + e.getMessage());
            return "contact_edit";
        }

        return "redirect:/contact_management";
    }

    @PostMapping("admin_delete")
    public String Admindelete(@RequestParam int id, Model model) {
        try {
            String query = "DELETE FROM admin WHERE admin_id = ?";
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/manage_admin";
    }

    @PostMapping("contact_delete")
    public String Contactdelete(@RequestParam int id, Model model) {
        try {
            String query = "DELETE FROM contact WHERE contact_id = ?";
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/contact_management";
    }

    @PostMapping("donation_delete")
    public String Donationdelete(@RequestParam int id, Model model) {
        try {
            String query = "DELETE FROM donation WHERE donation_id = ?";
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/donation_management";
    }

    @PostMapping("volunteer_delete")
    public String Volunteerdelete(@RequestParam int id, Model model) {
        try {
            String query = "DELETE FROM volunteer WHERE volunteer_id = ?";
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/volunteer_management";
    }


    @RequestMapping(value = {"/admin", "/admin.html"})
    public String admin(Model model) {
        return "admin";
    }

    @RequestMapping(value = {"/admin_management", "/admin_management.html"})
    public String adminManagement(Model model) {
        return "admin_management";
    }

    @RequestMapping(value = {"/new_admin", "/new_admin.html"})
    public String newAdmin(Model model) {
        return "new_admin";
    }

    @RequestMapping(value = {"/manage_admin", "/manage_admin.html"})
    public String manageAdmin(Model model) {
        List<admin> admin = readAdmin();
        model.addAttribute("adminList", admin);
        return "manage_admin";
    }

    @GetMapping("/volunteer_management")
    public String volunteerManagement(Model model) {
        List<volunteer> volunteer = readVolunteer();
        model.addAttribute("volunteerList", volunteer);
        return "volunteer_management";
    }

    @GetMapping("/donation_management")
    public String donationManagement(Model model) {
        List<donation> donation = readDonation();
        model.addAttribute("donationList", donation);
        return "donation_management";
    }

    @GetMapping("/contact_management")
    public String contactManagement(Model model) {
        List<contact> contact = readContact();
        model.addAttribute("contactList", contact);
        return "contact_management";
    }

    // Login validation
    @PostMapping("/admin_management")
    public String validateAdminLogin(
            @RequestParam("admin") String adminName,
            @RequestParam("password") String password,
            Model model) {

        try {
            String query = "SELECT * FROM admin WHERE admin_name = ? AND password = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, adminName);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
                return "redirect:/admin_management"; // Redirect to admin management page
            } else {
                model.addAttribute("error", "Invalid admin name or password");
                return "admin"; // Redirect back to login page with error
            }
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while validating credentials.");
            e.printStackTrace();
            return "admin"; // Redirect back to login page with error
        }
    }

    @GetMapping("/admin")
    public String logout() {
        return "admin"; // Redirect to the login page after logout
    }
}
