package com.franquicias.accenture_nequi.controllers;

import com.franquicias.accenture_nequi.models.Franchise;
import com.franquicias.accenture_nequi.services.implement.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/franchises")
public class FranchiseController {

    private final FranchiseService franchiseService;

    // Franchise Service Dependency Injection
    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }


    // Create new franchise
    @PostMapping("/create/")
    public ResponseEntity<Map<String, Object>> createFranchise(@RequestBody Franchise franchise) {
        Map<String, Object> response = new LinkedHashMap<>();

        try {
            Franchise createdFranchise = franchiseService.saveFranchise(franchise);

            if (createdFranchise != null) {
                response.put("success", true);
                response.put("detail", "Franchise created successfully.");
                response.put("franchise", createdFranchise);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("detail", "Failed to create franchise. Please check the provided data.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("detail", "An error occurred while creating the franchise: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    // Get all franchises
    @GetMapping("/get/all/")
    public ResponseEntity<Map<String, Object>> getAllFranchises() {
        Map<String, Object> response = new LinkedHashMap<>();

        try {
            List<Franchise> franchises = franchiseService.findAll();

            if (franchises != null && !franchises.isEmpty()) {
                response.put("success", true);
                response.put("detail", "Franchises retrieved successfully.");
                response.put("franchises", franchises);
                return ResponseEntity.ok(response);
            }
            response.put("success", false);
            response.put("detail", "No franchises found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("detail", "An error occurred while retrieving the franchises: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



    // Get franchise by ID
    @GetMapping("/get/{id}/")
    public ResponseEntity<Map<String, Object>> getFranchiseById(@PathVariable int id) {
        Map<String, Object> response = new LinkedHashMap<>();

        try {
            Franchise franchise = franchiseService.getFranchiseById(id);

            if (franchise != null) {
                response.put("success", true);
                response.put("detail", "Franchise retrieved successfully.");
                response.put("franchise", franchise);
                return ResponseEntity.ok(response);
            }
            response.put("success", false);
            response.put("detail", "Franchise not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("detail", "An error occurred while retrieving the franchise: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



    // Update franchise
    @PutMapping("/update/{id}/")
    public ResponseEntity<Map<String, Object>> updateFranchise(@PathVariable int id, @RequestBody Franchise franchise) {

        Map<String, Object> response = new LinkedHashMap<>();

        try {
            Franchise existingFranchise = franchiseService.getFranchiseById(id);

            if (existingFranchise == null) {
                response.put("success", false);
                response.put("detail", "Franchise not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            franchise.setId(id);

            Franchise updatedFranchise = franchiseService.updateFranchise(franchise);

            if (updatedFranchise != null) {
                response.put("success", true);
                response.put("detail", "Franchise updated successfully.");
                response.put("franchise", updatedFranchise);
                return ResponseEntity.ok(response);
            }

            response.put("success", false);
            response.put("detail", "An error occurred while updating the franchise.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("detail", "An error occurred while updating the franchise: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @DeleteMapping("/delete/{id}/")
    public ResponseEntity<Map<String, Object>> deleteFranchise(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        if (franchiseService.hasAssociatedAgencies(id)) {
            response.put("detail", "The franchise cannot be deleted because it has associated agencies.");
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        boolean isDeleted = franchiseService.deleteFranchise(id);

        if (isDeleted) {
            response.put("detail", "The franchise was deleted successfully.");
            response.put("success", true);
            return ResponseEntity.ok(response);
        }

        response.put("detail", "Franchise not found.");
        response.put("success", false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}



