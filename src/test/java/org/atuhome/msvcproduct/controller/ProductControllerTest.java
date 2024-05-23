package org.atuhome.msvcproduct.controller;

import org.atuhome.msvcproduct.dto.ProductDto;
import org.atuhome.msvcproduct.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Objects;

import static org.mockito.Mockito.when;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void uploadProducts() throws Exception {
        // Preparar datos de prueba
        MockMultipartFile file = new MockMultipartFile("file", "F:\\fvp\\msvc-product\\data.xls", MediaType.APPLICATION_OCTET_STREAM_VALUE, "contenido del archivo".getBytes());



        // Realizar la solicitud y verificar el resultado
        mockMvc.perform(MockMvcRequestBuilders.multipart("/product/upload").file(file))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Products uploaded successfully!"));
    }

    @Test
    void getProductByCode() throws Exception {
        // Preparar datos de prueba
        String code = "123";

        // Simular el comportamiento del servicio
        ProductDto productDto = new ProductDto();
        // Configurar el comportamiento esperado del servicio
        when(productService.getProductByCode(code)).thenReturn(productDto);

        // Realizar la solicitud y verificar el resultado
        mockMvc.perform(MockMvcRequestBuilders.get("/product?code=" + code))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(productDto.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(productDto.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(productDto.getPrice()));
    }

    @Test
    void searchProductsByDescription() throws Exception {
        // Preparar datos de prueba
        String description = "producto de prueba";

        // Simular el comportamiento del servicio
        ProductDto productDto = new ProductDto();
        // Configurar el comportamiento esperado del servicio
        when(productService.searchProductsByDescription(description)).thenReturn(productDto);

        // Realizar la solicitud y verificar el resultado
        mockMvc.perform(MockMvcRequestBuilders.get("/product/search?description=" + description))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(productDto.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(productDto.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(productDto.getPrice()));
    }
}
