package com.electrico.servicingAPI.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.time.LocalDateTime;

import static org.hibernate.id.IdentifierGenerator.GENERATOR_NAME;

@Entity
@Table(name = "T_PARAMETER")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ParameterEntity {

    @Id
    @SequenceGenerator(name = GENERATOR_NAME, sequenceName = "seq_parameter", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
    @Column(name = "ID")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="VALUE")
    private String value;

    @Column(name="DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "T_ITEM_ID", referencedColumnName = "ID")
    private ItemEntity itemEntity;

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

    public ParameterEntity(ParameterRepresentation representation) {
        this.name = representation.getName();
        this.value = representation.getValue();
    }

    public ParameterEntity(String name, String value) {
    this.name = name;
    this.value= value;
    }

}
