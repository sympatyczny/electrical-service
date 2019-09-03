package com.electrico.servicingAPI;

import com.electrico.servicingAPI.model.*;
import com.electrico.servicingAPI.repositories.AssortmentRepository;
import com.electrico.servicingAPI.services.AssortmentService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Log
@AllArgsConstructor
public class Controller {

    private final AssortmentRepository assortmentRepository;
    private final AssortmentService assortmentService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/assortments")
    public AssortmentsRepresentation getAllAssortments() {
        log.info("getAllAssortments()");
        return AssortmentsRepresentation.of(assortmentRepository.findAll());
    }

    @GetMapping("/assortments/{name}")
    public AssortmentRepresentation getAssortmentByName(@PathVariable String name) {
        log.info("getAssortmentByName(" + name+")");
        Optional<AssortmentEntity> assortmentEntity = assortmentRepository.findOneByName(name);

    return AssortmentRepresentation.of(assortmentEntity
            .map(AssortmentEntity::getName)
            .orElseThrow(() -> new RuntimeException("Brak asortymentu o podanej nazwie")));
    }

    @GetMapping("/items/{assortmentId}")
    public ItemsRepresentation getItemsByAssortmentName(@PathVariable Long assortmentId){
        log.info("getAllItems()");

            return new ItemsRepresentation(assortmentService.getItemsByAssortmentId(assortmentId));
    }

    @GetMapping("/items")
    public ItemsRepresentation getAllItems() {
        log.info("getAllItems()");

        return new ItemsRepresentation(assortmentService.getAllItemRepresentation());
    }

    @PostMapping("/assortments")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Assortments created!")
    public void postAssortments(@RequestBody AssortmentsRepresentation assortmentsRepresentation) {
        log.info("postAssortments("+ assortmentsRepresentation.toString()+")");
        log.info("assortmentRepresentation.size() = "+assortmentsRepresentation.getAssortments().size());

        assortmentService.saveAssortments(assortmentsRepresentation);
    }

    @PostMapping("/items")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Items created!")
    public void postItems(@RequestBody ItemsRepresentation itemsRepresentation) {
        log.info("postItems("+ itemsRepresentation.toString()+")");
        log.info("itemsRepresentation.size() = "+itemsRepresentation.getItems().size());

      assortmentService.saveItems(itemsRepresentation);
    }
}
