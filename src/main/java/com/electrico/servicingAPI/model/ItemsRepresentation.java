package com.electrico.servicingAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.util.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Log
public class ItemsRepresentation {

    private List<ItemRepresentation> items;

}
