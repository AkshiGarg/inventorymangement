package com.nagarro.nagp.handler;

import com.nagarro.nagp.Inventory;
import com.nagarro.nagp.domain.Category;
import com.nagarro.nagp.exception.InvalidRequestException;
import com.nagarro.nagp.repository.InventoryRepository;

public class InventoryHandler {

    private final InventoryRepository fragileInventoryRepository;
    private final InventoryRepository durableInventoryRepository;

    public InventoryHandler(final InventoryRepository fragileInventoryRepository,
                            final InventoryRepository durableInventoryRepository) {
        this.fragileInventoryRepository = fragileInventoryRepository;
        this.durableInventoryRepository = durableInventoryRepository;
    }

    public Inventory createInventory(final Inventory inventory) {

        if (inventory.getCategory() == Category.FRAGILE) {
            fragileInventoryRepository.save(inventory);
        } else if (inventory.getCategory() == Category.DURABLE) {
            durableInventoryRepository.save(inventory);
        } else {
            throw new InvalidRequestException("Wrong inventory type");
        }
        return new Inventory(Category.FRAGILE);

    }
}
