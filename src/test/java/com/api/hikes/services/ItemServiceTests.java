package com.api.hikes.services;

import com.api.hikes.exceptions.InvalidSeasonException;
import com.api.hikes.exceptions.NotFoundByIdException;
import com.api.hikes.models.Item;
import com.api.hikes.records.ItemRequest;
import com.api.hikes.repositories.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTests {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService underTest;

    @Test
    void givenValidId_whenGetItemById_thenItemIsReturned() {
        long id = 1L;

        Item expectedResult = Item.builder().id(id).name("Item").season("Season").build();

        when(itemRepository.findById(id))
                .thenReturn(Optional.ofNullable(expectedResult));

        Item actualResult = underTest.getById(id);

        assertThat(actualResult).isNotNull().usingRecursiveComparison().isEqualTo(expectedResult);

        verify(itemRepository).findById(id);
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    void givenInvalidId_whenGetItemById_thenExceptionIsThrown() {
        long id = 1L;

        when(itemRepository.findById(id)).thenReturn(Optional.empty());

        String actualMessage =
                catchThrowableOfType(() -> underTest.getById(id), NotFoundByIdException.class)
                        .getMessage();

        String expectedMessage = new NotFoundByIdException("Item").getMessage();

        assertThat(actualMessage).isNotNull().isEqualTo(expectedMessage);

        verify(itemRepository).findById(id);
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    void givenItems_whenGetAll_ThenAllItemsAreReturned() {
        List<Item> expectedResult = List.of(
                Item.builder().id(1).name("Item 1").season("Season").build(),
                Item.builder().id(2).name("Item 2").season("Season").build(),
                Item.builder().id(3).name("Item 3").season("Season").build()
        );

        when(itemRepository.findAllByOrderByIdAsc())
                .thenReturn(expectedResult);

        List<Item> actualResult = underTest.getAll();

        assertThat(actualResult).isNotNull().usingRecursiveComparison().isEqualTo(expectedResult);

        verify(itemRepository).findAllByOrderByIdAsc();
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    void givenValidItemRequest_whenCreateItem_thenItemIsCreated() {
        ItemRequest itemRequest = ItemRequest.builder().name(" Item ").season("Summer").build();

        when(itemRepository.save(Mockito.any(Item.class)))
                .thenAnswer(answer -> answer.getArguments()[0]);

        Item actualResult = underTest.create(itemRequest);
        Item expectedResult = Item.builder().name("item").season("summer").build();

        assertThat(actualResult).isNotNull().usingRecursiveComparison().isEqualTo(expectedResult);

        verify(itemRepository).save(Mockito.any(Item.class));
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    void givenInvalidSeason_whenCreateItem_thenExceptionIsThrown() {
        ItemRequest itemRequest = ItemRequest.builder().name("Item").season("abc").build();

        String actualMessage =
                catchThrowableOfType(() -> underTest.create(itemRequest), InvalidSeasonException.class)
                        .getMessage();

        String expectedMessage = new InvalidSeasonException(itemRequest.season()).getMessage();

        assertThat(actualMessage).isNotNull().isEqualTo(expectedMessage);

        verifyNoInteractions(itemRepository);
    }

    @Test
    void givenId_whenDeleteItem_thenItemIsDeleted() {
        long id = 1L;

        underTest.delete(id);

        verify(itemRepository).deleteById(id);
        verifyNoMoreInteractions(itemRepository);
    }

    @ParameterizedTest
    @ValueSource(strings = {"winter", "spring", "summer", "autumn"})
    void givenValidSeason_WhenGetListOfSeasonItemNames_ThenListOfSeasonItemNamesIsReturned(String season) {
        List<Item> mockedItems = List.of(
                Item.builder().id(1).name("Item 1").season(season).build(),
                Item.builder().id(2).name("Item 2").season(season).build(),
                Item.builder().id(3).name("Item 3").season(season).build()
        );

        when(itemRepository.findBySeason(season))
                .thenReturn(mockedItems);

        List<String> actualResult = underTest.getListOfSeasonItemNames(season);
        List<String> expectedResult = mockedItems.stream().map(Item::getName).toList();

        assertThat(actualResult).isNotNull().usingRecursiveComparison().isEqualTo(expectedResult);

        verify(itemRepository).findBySeason(season);
        verifyNoMoreInteractions(itemRepository);
    }
}
