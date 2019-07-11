package com.nagarro.nagp.repository;

import com.nagarro.nagp.Inventory;

public class FragileInventoryRepository implements InventoryRepository {

    @Override
    public void save(final Inventory inventory) {
        RepositoryHelper.saveFragile(inventory);
    }
}
