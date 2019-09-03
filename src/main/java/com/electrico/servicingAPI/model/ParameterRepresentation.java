package com.electrico.servicingAPI.model;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class ParameterRepresentation {

    private String name;

    private String value;

    private String description;

    public static ParameterRepresentation of(String name, String value, String description) {
        return new ParameterRepresentation(name, value, description);
    }

}
