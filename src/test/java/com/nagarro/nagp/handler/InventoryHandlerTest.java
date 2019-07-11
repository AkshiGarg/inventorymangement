package com.nagarro.nagp.handler;

import com.nagarro.nagp.Inventory;
import com.nagarro.nagp.domain.Category;
import com.nagarro.nagp.exception.InvalidRequestException;
import com.nagarro.nagp.repository.InventoryRepository;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InventoryHandlerTest {

    private InventoryHandler inventoryHandler;

    @Mock
    private InventoryRepository fragileInventoryRepository;

    @Mock
    private InventoryRepository durableInventoryRepository;

    @Mock
    private Inventory inventory;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        inventoryHandler = new InventoryHandler(fragileInventoryRepository, durableInventoryRepository);
    }

    @Test
    public void shouldReturnCreatedInventorySuccessfullyWhenInventoryCategoryIsFragile() {
        when(inventory.getCategory()).thenReturn(Category.FRAGILE);

        final Inventory result = inventoryHandler.createInventory(inventory);

        verify(fragileInventoryRepository, times(1)).save(inventory);
        verify(durableInventoryRepository, never()).save(inventory);
        assertThat(result.getCategory(), Is.is(Category.FRAGILE));
    }

    @Test
    public void shouldReturnCreatedInventorySuccessfullyWhenInventoryCategoryIsDurable() {
        when(inventory.getCategory()).thenReturn(Category.DURABLE);

        final Inventory result = inventoryHandler.createInventory(inventory);

        verify(durableInventoryRepository, times(1)).save(inventory);
        verify(fragileInventoryRepository, never()).save(inventory);
        assertThat(result.getCategory(), Is.is(Category.FRAGILE));
    }

    @Test
    public void shouldThrowInvalidRequestExceptionWhenInventoryIsNull() {
        expectedException.expect(InvalidRequestException.class);
        expectedException.expectMessage("Wrong inventory type");

        inventoryHandler.createInventory(inventory);
    }
}