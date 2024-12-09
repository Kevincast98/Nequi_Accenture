package com.franquicias.accenture_nequi.controllers;

import com.franquicias.accenture_nequi.models.Agency;
import com.franquicias.accenture_nequi.models.Franchise;
import com.franquicias.accenture_nequi.models.Products;
import com.franquicias.accenture_nequi.repository.FranchiseRepository;
import com.franquicias.accenture_nequi.services.implement.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.franquicias.accenture_nequi.services.implement.AgencyService;

import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    // Agency Service Dependency Injection
    @Autowired
    private AgencyService agencyService;

    @Autowired
    private ProductsService productService;

    @Autowired
    private FranchiseRepository franchiseRepository;


    @PostMapping("/create/{id}/")
    public ResponseEntity<Map<String, Object>> createProduct(@RequestBody Products product, @PathVariable int id) {

        Map<String, Object> response = new LinkedHashMap<>();

        try {
            Agency agency = agencyService.getAgencyById(id);
            if (agency == null) {
                response.put("success", false);
                response.put("detail", "Agency not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            if (product.getName() == null || product.getName().isEmpty()) {
                response.put("success", false);
                response.put("detail", "Product name is required.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            if (product.getStock() <= 0) {
                response.put("success", false);
                response.put("detail", "Product stock must be greater than 0.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            product.setAgency(agency);

            Products createdProduct = productService.saveProduct(product, id);

            response.put("success", true);
            response.put("detail", "Product created successfully.");
            response.put("product", createdProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("detail", "An error occurred while creating the product: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    // Get all products
    @GetMapping("/get/all/")
    public ResponseEntity<Map<String, Object>> getAllProducts() {
        Map<String, Object> response = new LinkedHashMap<>();

        try {
            List<Products> products = productService.findAll();

            if (products.isEmpty()) {
                response.put("success", false);
                response.put("detail", "No products found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {
                response.put("success", true);
                response.put("detail", "Products retrieved successfully.");
                response.put("products", products);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("detail", "An error occurred while retrieving the products: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    // Get product by ID
    @GetMapping("/get/{id}/")
    public ResponseEntity<Map<String, Object>> getProductById(@PathVariable int id) {
        Map<String, Object> response = new LinkedHashMap<>();

        try {
            Products product = productService.getProductById(id);

            if (product != null) {
                response.put("success", true);
                response.put("detail", "Product retrieved successfully.");
                response.put("product", product);
                return ResponseEntity.ok(response);
            }

            response.put("success", false);
            response.put("detail", "Product not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("detail", "An error occurred while retrieving the product: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



    // Update products
    @PutMapping("/update/{id}/")
    public ResponseEntity<Map<String, Object>> updateProduct(@PathVariable int id, @RequestBody Products product) {
        Map<String, Object> response = new LinkedHashMap<>();

        try {
            Products existingProduct = productService.getProductById(id);

            if (existingProduct == null) {
                response.put("success", false);
                response.put("detail", "Product not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            if (product.getName() != null) {
                existingProduct.setName(product.getName());
            }

            Products updatedProduct = productService.updateProduct(existingProduct);

            if (updatedProduct != null) {
                response.put("success", true);
                response.put("detail", "Product updated successfully.");
                response.put("product", updatedProduct);
                return ResponseEntity.ok(response);
            }

            response.put("success", false);
            response.put("detail", "An error occurred while updating the product.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("detail", "An error occurred while updating the product: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



    // Delete products by ID
    @DeleteMapping("/delete/{id}/")
    public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable int id) {
        boolean isDeleted = productService.deleteProduct(id);

        Map<String, Object> response = new HashMap<>();

        if (isDeleted) {
            response.put("detail", "The product was deleted");
            response.put("success", true);
            return ResponseEntity.ok(response);
        }

        response.put("detail", "Product not found");
        response.put("success", false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    // TopStock Products
    @GetMapping("/stock-max/{franchiseId}/")
    public ResponseEntity<Map<String, Object>> getStockMaxByFranchise(@PathVariable int franchiseId) {
        Map<String, Object> response = new LinkedHashMap<>();

        try {
            if (franchiseRepository.findById(franchiseId).isEmpty()) {
                response.put("success", false);
                response.put("detail", "Franchise not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            List<Products> products = productService.getStockMaxByFranchise(franchiseId);

            if (products.isEmpty()) {
                response.put("success", false);
                response.put("detail", "No products found for this franchise.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            response.put("success", true);
            response.put("detail", "Products with max stock retrieved successfully.");
            response.put("products", products);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("detail", "An error occurred while retrieving the products: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



}
