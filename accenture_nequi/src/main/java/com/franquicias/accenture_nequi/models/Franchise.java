package com.franquicias.accenture_nequi.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Data
public class Franchise{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "franchise", fetch = FetchType.LAZY, targetEntity = Agency.class)
    @JsonManagedReference
    private List<Agency> agency;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Agency> getAgency() {
        return agency;
    }

    public void setAgency(List<Agency> agency) {
        this.agency = agency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}