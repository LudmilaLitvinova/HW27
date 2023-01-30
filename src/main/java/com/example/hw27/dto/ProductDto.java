package com.example.hw27.dto;

import lombok.*;

/**
 * @author Ludmila Litvinova on 25.01
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProductDto {

    private Integer id;

    private String name;

    private String description;

    private double price;


}
