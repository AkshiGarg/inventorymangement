package com.nagarro.nagp.repository;

import com.nagarro.nagp.Inventory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@PrepareForTest(RepositoryHelper.class)
@RunWith(PowerMockRunner.class)
public class DurableInventoryRepositoryTest {

    @InjectMocks
    private DurableInventoryRepository durableInventoryRepository;

    @Mock
    private Inventory inventory;

    @Test
    public void shouldSaveDurableInventorySuccessfully() {
        PowerMockito.mockStatic(RepositoryHelper.class);

        durableInventoryRepository.save(inventory);

        PowerMockito.verifyStatic();
        RepositoryHelper.saveDurable(inventory);
    }
}