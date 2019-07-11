package com.nagarro.nagp;

import com.nagarro.nagp.domain.Category;
import com.nagarro.nagp.exception.InvalidRequestException;
import com.nagarro.nagp.handler.InventoryHandler;
import org.hamcrest.core.Is;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InventoryResourceTest {

    @InjectMocks
    private InventoryResource inventoryResource;

    @Mock
    private InventoryHandler inventoryHandler;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldReturnCreatedInventorySuccessfullyWhenInventoryIsNotNull() {
        final Inventory inventory = new Inventory(Category.DURABLE);
        when(inventoryHandler.createInventory(inventory))
                .thenReturn(inventory);

        final Inventory result = inventoryResource.createInventory(inventory);

        verify(inventoryHandler, times(1)).createInventory(inventory);
        assertThat(result.getCategory(), Is.is(inventory.getCategory()));
    }

    @Test
    public void shouldThrowInvalidRequestExceptionWhenInventoryIsNull() {
        expectedException.expect(InvalidRequestException.class);
        expectedException.expectMessage("Inventory must not be null");

        inventoryResource.createInventory(null);
    }

}