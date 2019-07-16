package com.nagarro.nagp;

import com.nagarro.nagp.domain.Category;
import com.nagarro.nagp.exception.InvalidRequestException;
import com.nagarro.nagp.handler.InventoryHandler;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class InventoryResourceTest {

    private InventoryResource inventoryResource;

    private InventoryHandler inventoryHandler = mock(InventoryHandler.class);

    @Parameterized.Parameter()
    public Inventory inventory;

    @Parameterized.Parameter(1)
    public Inventory result;

    public static Object[][] input() {
        return new Object[][]
                {
                        {new Inventory(Category.DURABLE), new Inventory(Category.DURABLE)},
                        {new Inventory(Category.FRAGILE), new Inventory(Category.FRAGILE)}
                };
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        inventoryResource = new InventoryResource(inventoryHandler);
    }

    @Test
    @Parameters(method = "input")
    public void shouldReturnCreatedInventorySuccessfullyWhenInventoryIsNotNull(Inventory inventory, Inventory result) {

        inventoryResource.createInventory(inventory);

        verify(inventoryHandler, times(1)).createInventory(inventory);
    }

    @Test
    public void shouldThrowInvalidRequestExceptionWhenInventoryIsNull() {
        expectedException.expect(InvalidRequestException.class);
        expectedException.expectMessage("Inventory must not be null");

        inventoryResource.createInventory(null);
    }

}