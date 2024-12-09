package com.franquicias.accenture_nequi.controllers;

import com.franquicias.accenture_nequi.models.Agency;
import com.franquicias.accenture_nequi.models.Franchise;
import com.franquicias.accenture_nequi.services.implement.AgencyService;
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
@RequestMapping("/api/agencies")
public class AgencyController {

    private final AgencyService agencyService;
    private final FranchiseService franchiseService;

    // Agency Service Dependency Injection
    public AgencyController(AgencyService agencyService, FranchiseService franchiseService) {
        this.agencyService = agencyService;
        this.franchiseService = franchiseService;
    }

    // Create new agency
    @PostMapping("/create/{id}/")
    public ResponseEntity<Map<String, Object>> createAgency(@RequestBody Agency agency, @PathVariable int id) {
        Map<String, Object> response = new LinkedHashMap<>();

        try {
            Franchise franchise = franchiseService.getFranchiseById(id);

            if (franchise == null) {
                response.put("success", false);
                response.put("detail", "Franchise not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            agency.setFranchise(franchise);

            Agency createdAgency = agencyService.saveAgency(agency, id);

            response.put("success", true);
            response.put("detail", "Agency created successfully.");
            response.put("agency", createdAgency);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("detail", "An error occurred while creating the agency: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    // Get all agencies
    @GetMapping("/get/all/")
    public ResponseEntity<Map<String, Object>> getAllAgencies() {
        Map<String, Object> response = new LinkedHashMap<>();

        try {
            List<Agency> agencies = agencyService.findAll();

            if (agencies.isEmpty()) {
                response.put("success", false);
                response.put("detail", "No agencies found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }else {
                response.put("success", true);
                response.put("detail", "Agencies retrieved successfully.");
                response.put("agencies", agencies);
                return ResponseEntity.ok(response);
            }

        } catch (Exception e) {
            response.put("success", false);
            response.put("detail", "An error occurred while retrieving the agencies: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    // Get agency by ID
    @GetMapping("/get/{id}/")
    public ResponseEntity<Map<String, Object>> getAgencyById(@PathVariable int id) {
        Map<String, Object> response = new LinkedHashMap<>();

        try {
            Agency agency = agencyService.getAgencyById(id);

            if (agency != null) {
                response.put("success", true);
                response.put("detail", "Agency retrieved successfully.");
                response.put("agency", agency);
                return ResponseEntity.ok(response);
            }

            response.put("success", false);
            response.put("detail", "Agency not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("detail", "An error occurred while retrieving the agency: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //Update Agency
    @PutMapping("/update/{id}/")
    public ResponseEntity<Map<String, Object>> updateAgency(@PathVariable int id, @RequestBody Agency agency) {

        Map<String, Object> response = new LinkedHashMap<>();

        try {
            Agency existingAgency = agencyService.getAgencyById(id);

            if (existingAgency == null) {
                response.put("success", false);
                response.put("detail", "Agency not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            if (agency.getName() != null) {
                existingAgency.setName(agency.getName()); // Actualizar el nombre si se proporciona uno
            }

            if (agency.getFranchise() != null) {
                existingAgency.setFranchise(agency.getFranchise()); // Si se pasa una nueva franquicia, actualizamos
            }

            Agency updatedAgency = agencyService.updateAgency(existingAgency);

            if (updatedAgency != null) {
                response.put("success", true);
                response.put("detail", "Agency updated successfully.");
                response.put("agency", updatedAgency);
                return ResponseEntity.ok(response);
            }

            response.put("success", false);
            response.put("detail", "An error occurred while updating the agency.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("detail", "An error occurred while updating the agency: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



    // Delete agency by ID
    @DeleteMapping("/delete/{id}/")
    public ResponseEntity<Map<String, Object>> deleteAgency(@PathVariable int id) {
        boolean hasAssociatedProducts = agencyService.hasAssociatedProducts(id);

        Map<String, Object> response = new HashMap<>();

        if (hasAssociatedProducts) {
            response.put("detail", "The agency cannot be deleted because it has associated products.");
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);  // 400 BAD REQUEST
        }

        boolean isDeleted = agencyService.deleteAgency(id);

        if (isDeleted) {
            response.put("detail", "The agency was deleted successfully.");
            response.put("success", true);
            return ResponseEntity.ok(response); // 200 OK
        }

        response.put("detail", "Agency not found.");
        response.put("success", false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); // 404 NOT FOUND
    }




}

