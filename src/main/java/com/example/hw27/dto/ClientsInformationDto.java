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
public class ClientsInformationDto {

    private Integer id;

    private String name;

    private String email;

    private String phone;

    private AddressDto address;


}
