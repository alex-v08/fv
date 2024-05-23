package org.atuhome.msvcproduct.controller;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.atuhome.msvcproduct.dto.ProductDto;
import org.atuhome.msvcproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")

@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    @PostMapping("/upload")
    public ResponseEntity<String>uploadProducts(@RequestParam("file") MultipartFile file){

        try {
            productService.loadProductsFromExcel(file.getBytes());
            return ResponseEntity.ok("Products uploaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while uploading the products!");

        }


    }

    //get products by code
    @GetMapping()
    public ResponseEntity<ProductDto>getProductByCode(@RequestParam("code") String code) {

        try {
            return ResponseEntity.ok(productService.getProductByCode(code));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @GetMapping("/search")
    public ResponseEntity<ProductDto>searchProductsByDescription(@RequestParam("description") String description) {

        try {
            return ResponseEntity.ok(productService.searchProductsByDescription(description));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }




}
