package com.api.hikes.services;

import com.api.hikes.entities.Item;
import com.api.hikes.repositories.ItemRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTests {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService underTest;

    @ParameterizedTest
    @ValueSource(strings = {"winter", "spring", "summer", "autumn"})
    void shouldGetListOfSeasonItemNames(String season) {
        List<Item> mockedItems = List.of(
                Item.builder().id(1).name("Item 1").season(season).build(),
                Item.builder().id(2).name("Item 2").season(season).build(),
                Item.builder().id(3).name("Item 3").season(season).build()
        );

        when(itemRepository.findBySeason(season)).thenReturn(mockedItems);

        List<String> actualResult = underTest.getListOfSeasonItemNames(season);
        List<String> expectedResult = mockedItems.stream().map(Item::getName).toList();

        assertThat(actualResult).isNotNull().isEqualTo(expectedResult);

        verify(itemRepository).findBySeason(season);
        verifyNoMoreInteractions(itemRepository);
    }
}
