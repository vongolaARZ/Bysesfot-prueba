package com.bysesoft.bysesoft.domain.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity(name = "product")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @Column(name = "price",nullable = false,updatable = false)
    private Double price;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonIgnoreProperties(value = {"products"})
    private Category category;

    @ManyToMany
    @Transient
    @JsonIgnore
    private List<Sales> sales;

}
