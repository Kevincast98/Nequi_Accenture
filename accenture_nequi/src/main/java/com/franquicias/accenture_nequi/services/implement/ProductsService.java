package com.franquicias.accenture_nequi.services.implement;

import com.franquicias.accenture_nequi.models.Agency;
import com.franquicias.accenture_nequi.models.Franchise;
import com.franquicias.accenture_nequi.models.Products;
import com.franquicias.accenture_nequi.repository.AgencyRepository;
import com.franquicias.accenture_nequi.repository.FranchiseRepository;
import com.franquicias.accenture_nequi.repository.ProductsRepository;
import com.franquicias.accenture_nequi.services.InterfaceProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsService implements InterfaceProducts {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private AgencyRepository agencyRepository;

    //Create new products
    public Products saveProduct(Products product, Integer id) {
        Agency agen = agencyRepository.findById(id).orElse(null);
        product.setAgency(agen);
        return productsRepository.save(product);
    }

    // Get all products
    public List<Products> findAll() {
        return productsRepository.findAll();
    }

    // Get products by ID
    public Products getProductById(Integer id) {
        Optional<Products> product = productsRepository.findById(id);
        return product.orElse(null);
    }


    // Update products
    public Products updateProduct(Products product) {
        return productsRepository.save(product);
    }

    // Delete products by ID
    public Boolean deleteProduct(Integer id) {
        if (productsRepository.existsById(id)) {
            productsRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Products> getStockMaxByFranchise(int franchiseId) {
        List<Products> products = productsRepository.findTopStockProductsByFranchise(franchiseId);
        return products;
    }


}
