package com.electrico.servicingAPI.model;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class ItemRepresentation {

    private String shortName;

    private String fullName;

    private String description;

    private String status;

    private Long assortmentId;

    private HashMap<String, String> parameters;

   public static ItemRepresentation of(String shortName, String fullName, String description, String status, Long
            assortmentId, HashMap<String,String> parameters) {
        return new ItemRepresentation(shortName, fullName, description, status, assortmentId, parameters);
    }

}
