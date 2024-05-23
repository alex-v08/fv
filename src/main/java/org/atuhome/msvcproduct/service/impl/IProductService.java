package org.atuhome.msvcproduct.service.impl;

import org.atuhome.msvcproduct.dto.ProductDto;


import java.util.List;

public interface IProductService {

    void loadProductsFromExcel(byte[] excelFile);



    ProductDto getProductByCode(String code);

    ProductDto searchProductsByDescription(String description);


}
