package com.nagarro.nagp;

import com.nagarro.nagp.exception.InvalidRequestException;
import com.nagarro.nagp.handler.InventoryHandler;

import java.util.Objects;

public class InventoryResource {

    private final InventoryHandler handler;

    public InventoryResource(final InventoryHandler handler) {
        this.handler = handler;
    }

    public Inventory createInventory(final Inventory inventory){

        if(Objects.nonNull(inventory)){
           return handler.createInventory(inventory);
        }

        throw new InvalidRequestException("Inventory must not be null");

    }

}
