package org.atuhome.msvcproduct.repository;


import org.atuhome.msvcproduct.dto.ProductDto;
import org.atuhome.msvcproduct.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {


    ProductDto findByDescription(String description);
    ProductDto findByCode(Long code);

}
