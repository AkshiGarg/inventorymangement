package com.nagarro.nagp.repository;

import com.nagarro.nagp.Inventory;

public class DurableInventoryRepository implements InventoryRepository {
    @Override public void save(final Inventory inventory) {
        RepositoryHelper.saveDurable(inventory);
    }
}
