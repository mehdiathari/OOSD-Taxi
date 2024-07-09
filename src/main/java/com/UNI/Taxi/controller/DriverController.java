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
            List<Driver> drivers = new ArrayList<Driver>();

            if (keyword == null) {
                driverRepository.findAll().forEach(drivers::add);
            } else {
                driverRepository.findByFamilyContainingIgnoreCase(keyword).forEach(drivers::add);
                model.addAttribute("keyword", keyword);
            }

            model.addAttribute("drivers", drivers);
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

    @PostMapping("/drivers/save")
    public String saveDriver(Driver driver, RedirectAttributes redirectAttributes) {
        try {
            driverRepository.save(driver);

            redirectAttributes.addFlashAttribute("message", "The Driver has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/drivers";
    }

    @GetMapping("/drivers/{id}")
    public String editDriver(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Driver driver = driverRepository.findById(id).get();

            model.addAttribute("driver", driver);
            model.addAttribute("pageTitle", "Edit Driver (ID: " + id + ")");

            return "driver_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/drivers";
        }
    }

    @GetMapping("/drivers/delete/{id}")
    public String deleteTutorial(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            driverRepository.deleteById(id);

            redirectAttributes.addFlashAttribute("message", "The Driver with id=" + id + " has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/drivers";
    }

    @GetMapping("/drivers/{id}/published/{status}")
    public String updateTutorialPublishedStatus(@PathVariable("id") Long id, @PathVariable("status") boolean published,
                                                Model model, RedirectAttributes redirectAttributes) {
        try {
            driverRepository.updatePublishedStatus(id, published);

            String status = published ? "published" : "disabled";
            String message = "The Driver id=" + id + " has been " + status;

            redirectAttributes.addFlashAttribute("message", message);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/drivers";
    }}
