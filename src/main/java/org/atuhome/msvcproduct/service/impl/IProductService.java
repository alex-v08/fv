package org.atuhome.msvcproduct.service.impl;

import org.atuhome.msvcproduct.dto.ProductDto;


import java.util.List;
import java.util.Set;

public interface IProductService {

    void loadProductsFromExcel(Set<ProductDto> productDtos);


    Set<ProductDto> readExcelFile(String filePath);
    ProductDto getProductByCode(String code);

    ProductDto searchProductsByDescription(String description);


}
