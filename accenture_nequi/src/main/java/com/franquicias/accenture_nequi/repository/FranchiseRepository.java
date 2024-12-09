package com.franquicias.accenture_nequi.repository;

import com.franquicias.accenture_nequi.models.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {
    Franchise findFirstBy();

}
