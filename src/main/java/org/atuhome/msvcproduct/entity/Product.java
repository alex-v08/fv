package org.atuhome.msvcproduct.entity;


import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data

@NoArgsConstructor

public class Product {

    @Id
    private Long code;
    private String description;
    private float price;

}
