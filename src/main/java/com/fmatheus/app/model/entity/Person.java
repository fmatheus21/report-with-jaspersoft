package com.fmatheus.app.model.entity;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Fernando Matheus
 */
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "person", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"document"}),
        @UniqueConstraint(columnNames = {"id"})})
public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 70)
    private String name;

    @Column(name = "document", nullable = false, length = 20)
    private String document;

    @ToString.Exclude
    @JoinColumn(name = "id_person_type", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private PersonType personType;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private Address address;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private Contact contact;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private Client client;


}