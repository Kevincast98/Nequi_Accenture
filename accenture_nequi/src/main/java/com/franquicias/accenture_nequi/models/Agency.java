package com.franquicias.accenture_nequi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "franchise_id", nullable = false)
    @JsonBackReference
    private Franchise franchise;

    @OneToMany(mappedBy = "agency", fetch = FetchType.LAZY, targetEntity = Products.class)
    @JsonManagedReference
    private List<Products> products;


    @JsonGetter("franchise_id")
    public Integer getFranchiseId() {
        return franchise != null ? franchise.getId() : null;
    }

    public Franchise getFranchise() {
        return franchise;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
}