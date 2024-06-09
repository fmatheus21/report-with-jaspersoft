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
@Table(name = "person_type", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"}),
        @UniqueConstraint(columnNames = {"id"})})
public class PersonType implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 15)
    private String name;


}

