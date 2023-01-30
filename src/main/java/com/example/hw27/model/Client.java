package com.example.hw27.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


/**
 * @author Ludmila Litvinova on 25.01
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(schema = "my_store")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phone;

    @OneToOne()
    @JoinColumn(name = "fk_address_id")
    private Address address;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orderHistory;


}
