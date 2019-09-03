package com.electrico.servicingAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParametersRepresentation {

    private List<ParameterRepresentation> parameterRepresentations;

    public static ParametersRepresentation of(List<ParameterEntity> parameters) {
        return new ParametersRepresentation(parameters.stream()
                .map(parameterEntity -> ParameterRepresentation.of(parameterEntity.getName(),
                                                                   parameterEntity.getValue(),
                                                                   parameterEntity.getDescription()))
                .collect(Collectors.toList()));
    }

}
