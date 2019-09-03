package com.electrico.servicingAPI.model;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class AssortmentRepresentation {

    private String name;


   public static AssortmentRepresentation of(String name) {
        return new AssortmentRepresentation(name);
    }
}
