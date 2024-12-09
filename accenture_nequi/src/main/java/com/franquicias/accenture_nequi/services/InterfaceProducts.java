package com.franquicias.accenture_nequi.services;

import com.franquicias.accenture_nequi.models.Products;

import java.util.List;

public interface InterfaceProducts {

    //Create new products
    public abstract Products saveProduct(Products product, Integer id);

    // Get all products
    public abstract List<Products> findAll();

    // Get products by ID
    public abstract Products getProductById(Integer id);

    // Update products
    public abstract Products updateProduct(Products product);

    // Delete products by ID
    public abstract Boolean deleteProduct(Integer id);
}
