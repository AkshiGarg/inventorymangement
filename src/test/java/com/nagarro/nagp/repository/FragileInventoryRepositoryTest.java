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
public class FragileInventoryRepositoryTest {
    @InjectMocks
    private FragileInventoryRepository fragileInventoryRepository;

    @Mock
    private Inventory inventory;

    @Test
    public void shouldSaveFragileInventorySuccessfully() {
        PowerMockito.mockStatic(RepositoryHelper.class);

        fragileInventoryRepository.save(inventory);

        PowerMockito.verifyStatic();
        RepositoryHelper.saveFragile(inventory);
    }
}