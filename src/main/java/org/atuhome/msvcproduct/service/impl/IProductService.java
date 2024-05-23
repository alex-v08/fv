package org.atuhome.msvcproduct.service.impl;

import org.atuhome.msvcproduct.dto.ProductDto;
import org.atuhome.msvcproduct.entity.Product;

import java.util.List;

public interface IProductService {

    void loadProductsFromExcel(byte[] excelFile);

    public List<ProductDto> listProducts(String description);

    Product getProductByCode(String code);

    ProductDto searchProductsByDescription(String description);

    float calculateTotalPrice(List<String> codes);
}
