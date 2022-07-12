package com.bysesoft.bysesoft.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity(name = "seller")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "salary")
    private Double salary;

    @OneToMany(mappedBy = "seller")
    private List<Sales> sales;

    public Seller(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }
}
