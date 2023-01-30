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
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "fk_order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "fk_product_id", nullable = false)
    private Product product;

    @Column
    private int count;

}
