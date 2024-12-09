package com.franquicias.accenture_nequi.services.implement;

import com.franquicias.accenture_nequi.models.Agency;
import com.franquicias.accenture_nequi.models.Franchise;
import com.franquicias.accenture_nequi.repository.AgencyRepository;
import com.franquicias.accenture_nequi.repository.FranchiseRepository;
import com.franquicias.accenture_nequi.repository.ProductsRepository;
import com.franquicias.accenture_nequi.services.InterfaceAgency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgencyService implements InterfaceAgency {

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private FranchiseRepository franchiseRepository;
    @Autowired
    private ProductsRepository productsRepository;

    // Create new agency
    public Agency saveAgency(Agency agency, Integer id) {
        Franchise fran = franchiseRepository.findById(id).orElse(null);
        agency.setFranchise(fran);
        return agencyRepository.save(agency);
    }

    // Get all agencies
    public List<Agency> findAll() {
        return agencyRepository.findAll();
    }

    // Get agency by ID
    public Agency getAgencyById(Integer id) {
        Optional<Agency> agency = agencyRepository.findById(id);
        return agency.orElse(null);
    }

    // Update agency
    public Agency updateAgency(Agency agency) {
        return agencyRepository.save(agency);
    }

    // Method to check if the agency has associated products
    public boolean hasAssociatedProducts(Integer agencyId) {
        return productsRepository.existsByAgency_Id(agencyId);
    }


    public Franchise getDefaultFranchise() {
        return franchiseRepository.findFirstBy();
    }

    // Get agency by id
    public Agency getAgencyById(int id) {
        Optional<Agency> agency = agencyRepository.findById(id);
        return agency.orElse(null);
    }
    // Delete agency by ID
    public Boolean deleteAgency(Integer id) {
        if (agencyRepository.existsById(id)) {
            if (productsRepository.existsByAgency_Id(id)) {
                return false;
            }
            agencyRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
