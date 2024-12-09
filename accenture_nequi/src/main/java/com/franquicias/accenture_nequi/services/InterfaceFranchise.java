package com.franquicias.accenture_nequi.services;

import com.franquicias.accenture_nequi.models.Franchise;

import java.util.List;

public interface InterfaceFranchise {

    // Create new franchise
    public abstract Franchise saveFranchise(Franchise franchise);

    // Get all franchises
    public abstract List<Franchise> findAll();

    // Get franchise by ID
    public abstract Franchise getFranchiseById(Integer id);

    // Update franchise
    public abstract Franchise updateFranchise(Franchise franchise);

    // Delete franchise by ID
    public abstract Boolean deleteFranchise(Integer id);

}
