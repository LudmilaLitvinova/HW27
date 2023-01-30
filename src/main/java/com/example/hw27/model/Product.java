package com.example.hw27.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ludmila Litvinova on 25.01
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "my_store")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private double price;


}
