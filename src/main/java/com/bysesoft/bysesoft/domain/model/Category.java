package com.bysesoft.bysesoft.domain.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity(name = "category")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    @JsonIgnoreProperties(value = {"category"})
    private List<Product> products;
}
