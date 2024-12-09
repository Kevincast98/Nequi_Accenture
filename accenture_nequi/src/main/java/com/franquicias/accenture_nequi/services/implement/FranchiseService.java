package com.franquicias.accenture_nequi.services.implement;


import com.franquicias.accenture_nequi.models.Franchise;
import com.franquicias.accenture_nequi.repository.AgencyRepository;
import com.franquicias.accenture_nequi.repository.FranchiseRepository;
import com.franquicias.accenture_nequi.services.InterfaceFranchise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FranchiseService implements InterfaceFranchise {

    @Autowired
    FranchiseRepository franchiseRepository;
    @Autowired
    private AgencyRepository agencyRepository;


    // Create new franchise
    public Franchise saveFranchise(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    // Get all franchises
    public List<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    // Get franchise by ID
    public Franchise getFranchiseById(Integer id) {
        Optional<Franchise> franchise = franchiseRepository.findById(id);
        return franchise.orElse(null);
    }

    // Update franchise
    public Franchise updateFranchise(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    // Method to check if the franchise has associated agencies
    public boolean hasAssociatedAgencies(Integer franchiseId) {
        return agencyRepository.existsByFranchise_Id(franchiseId);
    }

    // Delete franchise by ID
    public Boolean deleteFranchise(Integer id) {
        if (franchiseRepository.existsById(id)) {
            franchiseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
