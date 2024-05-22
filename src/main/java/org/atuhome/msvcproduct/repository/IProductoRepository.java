package org.atuhome.msvcproduct.repository;


import org.atuhome.msvcproduct.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Product, String> {

    List<Product> findByDescripcion(String descripcion);

}
