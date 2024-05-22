package org.atuhome.msvcproduct.service;

import org.atuhome.msvcproduct.entity.Product;
import org.atuhome.msvcproduct.service.impl.IProductService;

import java.util.List;

public class ProductService implements IProductService {
    @Override
    public void loadProductsFromExcel(byte[] excelFile) {



    }

    @Override
    public List<Product> listProducts() {
        return List.of();
    }

    @Override
    public Product getProductByCode(String code) {
        return null;
    }

    @Override
    public List<Product> searchProductsByDescription(String description) {
        return List.of();
    }

    @Override
    public float calculateTotalPrice(List<String> codes) {
        return 0;
    }
}
