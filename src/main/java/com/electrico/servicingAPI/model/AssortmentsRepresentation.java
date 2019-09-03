package com.electrico.servicingAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AssortmentsRepresentation {

        private List<AssortmentRepresentation> assortments;

        public static AssortmentsRepresentation of(List<AssortmentEntity> assortments) {
            return new AssortmentsRepresentation(assortments.stream()
                    .map(assortment -> AssortmentRepresentation.of(assortment.getName()))
                    .collect(Collectors.toList()));
        }
}
