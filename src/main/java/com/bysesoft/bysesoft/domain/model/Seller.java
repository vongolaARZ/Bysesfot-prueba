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

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private Double salary;

    @OneToMany
    @JsonIgnoreProperties(value = {"seller"})
    private List<Sales> sales;
}
