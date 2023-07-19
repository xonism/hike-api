package com.api.hikes.services;

import com.api.hikes.exceptions.InvalidSeasonException;
import com.api.hikes.records.HikeRecommendations;
import com.api.hikes.records.HikeRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HikesServiceTests {

    @Mock
    private ItemService itemService;

    @InjectMocks
    private HikesService underTest;

    @Test
    void givenValidHikeRequest_WhenGetHikeRecommendations_ThenHikeRecommendationsAreReturned() {
        HikeRequest hikeRequest = new HikeRequest(200, "Summer");

        List<String> items = List.of("Item 1", "Item 2", "Item 3");

        when(itemService.getListOfSeasonItemNames(hikeRequest.season())).thenReturn(items);

        HikeRecommendations actualResult = underTest.getHikeRecommendations(hikeRequest);
        HikeRecommendations expectedResult = HikeRecommendations.builder()
                .sleepoverCount(4)
                .foodCalories(18000)
                .litersOfWater(30.0)
                .items(items)
                .build();

        assertThat(actualResult).isNotNull().usingRecursiveComparison().isEqualTo(expectedResult);

        verify(itemService).getListOfSeasonItemNames(hikeRequest.season());
        verifyNoMoreInteractions(itemService);
    }

    @Test
    void givenInvalidSeason_WhenGetHikeRecommendations_ThenInvalidSeasonExceptionIsThrown() {
        HikeRequest hikeRequest = new HikeRequest(200, "abc");

        String actualMessage =
                catchThrowableOfType(() -> underTest.getHikeRecommendations(hikeRequest), InvalidSeasonException.class)
                        .getMessage();

        String expectedMessage = new InvalidSeasonException(hikeRequest.season()).getMessage();

        assertThat(actualMessage).isNotNull().isEqualTo(expectedMessage);

        verifyNoInteractions(itemService);
    }
}