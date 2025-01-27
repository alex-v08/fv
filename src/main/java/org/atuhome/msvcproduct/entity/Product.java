package org.atuhome.msvcproduct.entity;


import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter

public class Product {

    @Id
    private String codigo;
    private String descripcion;
    private float precio;
}
