package com.electrico.servicingAPI.services;

import com.electrico.servicingAPI.model.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface AssortmentService {

    void saveAssortments(AssortmentsRepresentation AssortmentsRepresentation);

    void saveItems(ItemsRepresentation itemsRepresentation);

    List<ParameterEntity> getParameterList(Long itemId);

    List<ItemRepresentation>getAllItemRepresentation();

    void saveParameter(ParameterEntity parameterEntity);

    List<ItemRepresentation> getItemsByAssortmentId(Long assortmentId);
}
