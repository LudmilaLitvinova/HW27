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
public class AddressDto {

    private Integer id;

    private String country;

    private String city;

    private String street;

    private String house;


}
