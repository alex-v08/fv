package org.atuhome.msvcproduct.service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.atuhome.msvcproduct.dto.ProductDto;
import org.atuhome.msvcproduct.entity.Product;
import org.atuhome.msvcproduct.repository.IProductRepository;
import org.atuhome.msvcproduct.service.impl.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

import java.util.Set;


@Service
@Transactional
@RequiredArgsConstructor

public class ProductService implements IProductService {

    @Autowired
    private final IProductRepository productRepository;


    @Override
    public void loadProductsFromExcel(Set<ProductDto> productDtos) {

        saveProductsFromDtosInDB(productDtos);





    }

    @Override
    public Set<ProductDto> readExcelFile(String filePath) {
        Set<ProductDto> productDtos = new HashSet<>();
        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook = WorkbookFactory.create(file);
            //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                ProductDto productDto = new ProductDto();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType()){
                        case STRING:
                            productDto.setDescription(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            productDto.setCode((long) cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            productDto.setPrice((float) cell.getNumericCellValue());
                            break;
                    }
                }
                productDtos.add(productDto);

            }
            file.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return productDtos;
    }


    @Override
    public ProductDto getProductByCode(String code) {
        ProductDto productDto = productRepository.findByCode(Long.valueOf(code));
        return productDto;

    }

    @Override
    public ProductDto searchProductsByDescription(String description) {
        ProductDto productDto = productRepository.findByDescription(description);
        return productDto;
    }


    private void saveProductFromDto(ProductDto productDto) {
        Product product = new Product();
        product.setCode(productDto.getCode());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
    }

    private void saveProductsFromDtosInDB(Set<ProductDto> productDtos) {

        //Save products in database
        productDtos.forEach(productDto -> {
            saveProductFromDto(productDto);
        });



    }




    //Find product by filter for description in database




}
