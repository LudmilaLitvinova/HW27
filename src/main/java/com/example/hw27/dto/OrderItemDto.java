package com.example.hw27.dto;

import lombok.*;

/**
 * @author Ludmila Litvinova on 25.01
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderItemDto {

    private Integer id;

    private OrderDto order;

    private ProductDto product;

    private int count;

}
