package org.atuhome.msvcproduct.service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.atuhome.msvcproduct.dto.ProductDto;
import org.atuhome.msvcproduct.entity.Product;
import org.atuhome.msvcproduct.repository.IProductRepository;
import org.atuhome.msvcproduct.service.impl.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor

public class ProductService implements IProductService {

    @Autowired
    private final IProductRepository productRepository;





    @Override
    public void loadProductsFromExcel(byte[] excelFile) {

        try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(excelFile))) {
            // Get the first sheet
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate over all the rows in the sheet
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                // Skip the header row
                if (row.getRowNum() == 0) {
                    continue;
                }

                // Create a new product object
                ProductDto productDto = new ProductDto();
                productDto.setCode(Long.valueOf(row.getCell(0).getStringCellValue()));
                productDto.setDescription(row.getCell(1).getStringCellValue());
                productDto.setPrice((float) row.getCell(2).getNumericCellValue());

                // Save the productDto to the database
                saveProductFromDto(productDto);

            }

        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
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


    //Find product by filter for description in database




    //Find product by filter for description in database




}
