package com.example.hw27.dto;

import lombok.*;

import java.util.List;


/**
 * @author Ludmila Litvinova on 25.01
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDto {

    private Integer id;

    private ClientDto client;

    private List<OrderItemDto> orderItems;


}
