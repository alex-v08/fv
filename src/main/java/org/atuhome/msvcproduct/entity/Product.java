package org.atuhome.msvcproduct.entity;


import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor

public class Product {

    @Id
    private String code;
    private String description;
    private float price;

}
