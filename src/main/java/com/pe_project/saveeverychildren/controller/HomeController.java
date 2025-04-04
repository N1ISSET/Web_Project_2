package com.pe_project.saveeverychildren.controller;

import com.pe_project.saveeverychildren.common.DatabaseConnection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Controller
public class HomeController{
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    DatabaseConnection db = null;

    @RequestMapping(value = { "/", "/home", "/home.html" })
    public String home(Model model) {
        return "home";
    }

//    @PostMapping
//    public String save(Model model) {
//
//    }
}
