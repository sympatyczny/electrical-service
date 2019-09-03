package com.electrico.servicingAPI.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_ASSORTMENT")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AssortmentEntity {

    private static final String GENERATOR_NAME = "assortment.generator";

    @Id
    @SequenceGenerator(name = GENERATOR_NAME, sequenceName = "seq_assortment", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DATE_CREATED")
    private LocalDateTime dateCreated;

    @Column(name = "USER_CREATED")
    private String userCreated;

    @Version
    @Column(name = "ENTITY_VERSION")
    private Integer entityVersion;

    @PrePersist
    void onPrePersist() {
        dateCreated = LocalDateTime.now();
        userCreated = "SERVICE";
    }

    public AssortmentEntity(AssortmentRepresentation representation) {
        this.name = representation.getName();
    }

}


