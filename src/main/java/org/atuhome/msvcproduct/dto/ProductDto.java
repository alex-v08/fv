package org.atuhome.msvcproduct.dto;

import lombok.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Data

public class ProductDto {

    private String code;
    private String description;
    private float price;


}
