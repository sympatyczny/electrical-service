package com.electrico.servicingAPI.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_ITEM")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ItemEntity {

    private static final String GENERATOR_NAME = "item.generator";

    @Id
    @SequenceGenerator(name = GENERATOR_NAME, sequenceName = "seq_item", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SHORT_NAME")
    private String shortName;

    @Column(name="FULL_NAME")
    private String fullName;

    @Column(name="DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "T_ASSORTMENT_ID", referencedColumnName = "ID")
    private AssortmentEntity assortmentEntity;

    @Column(name = "STATUS")
    private String status;

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
        status = "BROKEN";
    }

    public ItemEntity(ItemRepresentation representation) {
        this.shortName = representation.getShortName();
        this.fullName = representation.getFullName();
        this.description = representation.getDescription();
        this.status = representation.getStatus();
    }

}
