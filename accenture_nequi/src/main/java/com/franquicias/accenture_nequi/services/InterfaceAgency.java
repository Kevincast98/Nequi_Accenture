package com.franquicias.accenture_nequi.services;

import com.franquicias.accenture_nequi.models.Agency;

import java.util.List;

public interface InterfaceAgency {

    // Create new agency
    public abstract Agency saveAgency(Agency agency, Integer id);

    // Get all agencies
    public abstract List<Agency> findAll();

    // Get agency by ID
    public abstract Agency getAgencyById(Integer id);

    // Update agency
    public abstract Agency updateAgency(Agency agency);

    // Delete agency by ID
    public abstract Boolean deleteAgency(Integer id);
}
