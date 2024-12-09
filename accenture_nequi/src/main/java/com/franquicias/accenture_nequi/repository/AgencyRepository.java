package com.franquicias.accenture_nequi.repository;

import com.franquicias.accenture_nequi.models.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Integer> {
    List<Agency> getAgencyById(int id);

    List<Agency> id(int id);

    boolean existsByFranchise_Id(Integer franchiseId);
}
