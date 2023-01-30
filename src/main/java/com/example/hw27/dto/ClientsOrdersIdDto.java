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
public class ClientsOrdersIdDto {

    private Integer id;

    private String name;

    private String email;

    private String phone;

    private AddressDto address;

    private List<Integer> orderIds;


}
