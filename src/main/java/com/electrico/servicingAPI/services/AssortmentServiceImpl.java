package com.electrico.servicingAPI.services;

import com.electrico.servicingAPI.model.*;
import com.electrico.servicingAPI.repositories.AssortmentRepository;
import com.electrico.servicingAPI.repositories.ItemRepository;
import com.electrico.servicingAPI.repositories.ParameterRepository;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
 public class AssortmentServiceImpl implements AssortmentService {

    private final AssortmentRepository assortmentRepository;
    private final ItemRepository itemRepository;
    private final ParameterRepository parameterRepository;

    @Autowired
    ModelMapper modelMapper;

    public AssortmentServiceImpl(AssortmentRepository assortmentRepository, ItemRepository itemRepository,
                                 ParameterRepository parameterRepository) {
        this.assortmentRepository = assortmentRepository;
        this.itemRepository = itemRepository;
        this.parameterRepository = parameterRepository;

    }

    @Transactional
    @Override
    public void saveAssortments(AssortmentsRepresentation assortmentsRepresentation) {
        log.info("saveAssortmens: "+assortmentsRepresentation.toString());

        List<AssortmentRepresentation> assortmentRepresentationList = assortmentsRepresentation.getAssortments();
        List<AssortmentEntity> assortmentEntities = new ArrayList<>();

        assortmentRepresentationList.forEach(assortmentRepresentation -> {
            assortmentEntities.add(modelMapper.map(assortmentRepresentation, AssortmentEntity.class));
        });

        assortmentEntities.forEach(assortment->{
            log.info("saveAssortment: "+assortment.toString());
            assortmentRepository.save(assortment);
        });
    }

    @Transactional
    @Override
    public void saveItems(ItemsRepresentation itemsRepresentation) {
        log.info("saveItems: "+ itemsRepresentation.toString());

        List<ItemRepresentation> itemRepresentationList = itemsRepresentation.getItems();

        itemRepresentationList.forEach(itemRepresentation -> {

            ItemEntity itemEntity =  modelMapper.map(itemRepresentation, ItemEntity.class);
            itemEntity.setAssortmentEntity(assortmentRepository.getOne(itemRepresentation.getAssortmentId()));

           HashMap<String, String> parameterMap = itemRepresentation.getParameters();

            for (HashMap.Entry<String, String> entry : parameterMap.entrySet()) {
                ParameterEntity parameterEntity = new ParameterEntity(entry.getKey(),entry.getValue());
                parameterEntity.setItemEntity(itemEntity);

                log.info("saveParameter: "+parameterEntity.toString());
                saveParameter(parameterEntity);
            }
            log.info("saveItem: "+itemEntity.toString());
            itemRepository.save(itemEntity);
        });
    }

    @Override
    public void saveParameter(ParameterEntity parameterEntity) {
        log.info("saveParameter("+parameterEntity.toString()+")");

        parameterRepository.save(parameterEntity);
    }

    @Override
    public List<ParameterEntity> getParameterList(Long itemId) {
        log.info("getParameterList("+itemId+")");
        return parameterRepository.findOneByItemEntityId(itemId);
    }

    @Override
    public List<ItemRepresentation> getAllItemRepresentation() {
        log.info("getAllItemRepresentation()");

        List<ItemEntity> items = itemRepository.findAll();

            List<ItemRepresentation> itemRepresentationList = items.stream()
                    .map(item -> ItemRepresentation.of(item.getShortName(),
                            item.getFullName(),
                            item.getDescription(),
                            item.getStatus(),
                            item.getAssortmentEntity().getId(),
                            getParameterMap(item.getId())))
                    .collect(Collectors.toList());

        return itemRepresentationList;
    }

    private HashMap<String, String> getParameterMap(Long itemId){
        log.info("getParameterMap("+itemId+")");

        HashMap<String, String> parametersMap = new HashMap<>();
        List<ParameterEntity> params = getParameterList(itemId);

            params.forEach((parameterEntity) -> {
                parametersMap.put(parameterEntity.getName(), parameterEntity.getValue());

            });
        return parametersMap;
    }


    @Override
    public List<ItemRepresentation> getItemsByAssortmentId(Long assortmentId) {
        log.info("getItemsByAssortmentId("+assortmentId+")");

        List<ItemEntity> itemEntity = itemRepository.findOneByAssortmentEntityId(assortmentId);

        List<ItemRepresentation> representationList = itemEntity.stream()
                .map(item -> ItemRepresentation.of(item.getShortName(),
                                                   item.getFullName(),
                                                   item.getDescription(),
                                                   item.getStatus(),
                                                   item.getAssortmentEntity().getId(),
                                                   getParameterMap(item.getId())))
                                                   .collect(Collectors.toList());
        return representationList;
    }

}
