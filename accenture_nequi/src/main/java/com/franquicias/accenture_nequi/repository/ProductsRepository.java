package com.franquicias.accenture_nequi.repository;


import com.franquicias.accenture_nequi.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {


    @Query("SELECT p FROM Products p WHERE p.agency.franchise.id = :franchiseId " +
            "AND p.stock = (SELECT MAX(p2.stock) FROM Products p2 WHERE p2.agency.id = p.agency.id)")
    List<Products> findTopStockProductsByFranchise(int franchiseId);

    // Verificar si existen productos relacionados con una agencia específica
    boolean existsByAgency_Id(Integer agencyId);
}
