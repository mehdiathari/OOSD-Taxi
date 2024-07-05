package com.UNI.Taxi.controller;

import java.util.ArrayList;
import java.util.List;

import com.UNI.Taxi.entity.Driver;
import com.UNI.Taxi.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DriverController

{
    @Autowired
    private DriverRepository driverRepository;

    @GetMapping("/drivers")
    public String getAll(Model model, @Param("keyword") String keyword) {
        try {
            List<Driver> tutorials = new ArrayList<Driver>();

            if (keyword == null) {
                driverRepository.findAll().forEach(tutorials::add);
            } else {
                driverRepository.findByFamilyContainingIgnoreCase(keyword).forEach(tutorials::add);
                model.addAttribute("keyword", keyword);
            }

            model.addAttribute("drivers", tutorials);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "drivers";
    }

    @GetMapping("/drivers/new")
    public String addDriver(Model model) {
        Driver driver = new Driver();
        driver.setPublished(true);

        model.addAttribute("driver", driver);
        model.addAttribute("pageTitle", "Create new Driver");

        return "driver_form";
    }




}
