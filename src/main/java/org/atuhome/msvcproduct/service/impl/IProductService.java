package org.atuhome.msvcproduct.service.impl;

import org.atuhome.msvcproduct.entity.Product;

import java.util.List;

public interface IProductService {

    void loadProductsFromExcel(byte[] excelFile);

    List<Product> listProducts();

    Product getProductByCode(String code);

    List<Product> searchProductsByDescription(String description);

    float calculateTotalPrice(List<String> codes);
}
