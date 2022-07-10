package com.bysesoft.bysesoft.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "sales")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long saleId;

    @Column(insertable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "sellerId")
    @JsonIgnoreProperties(value = {"sales"})
    private Seller seller;

    @Column
    private Double commission;

    @ManyToMany
    @ToString.Exclude
    private List<Product> productList;
}
