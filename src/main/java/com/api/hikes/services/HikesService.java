package com.api.hikes.services;

import com.api.hikes.constants.Constants;
import com.api.hikes.enums.Season;
import com.api.hikes.records.HikeRecommendations;
import com.api.hikes.records.HikeRequest;
import com.api.hikes.utils.SeasonUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HikesService {

    private final ItemService itemService;

    private HikesService(ItemService itemService) {
        this.itemService = itemService;
    }

    public HikeRecommendations getHikeRecommendations(HikeRequest hikeRequest) {
        Season season = SeasonUtils.getSeasonOrThrow(hikeRequest.season());

        int sleepoverCount = getSleepoverCount(hikeRequest.lengthInKilometers());
        int foodCalories = getFoodCalories(sleepoverCount, hikeRequest.lengthInKilometers(), season);
        double litersOfWater = getLitersOfWater(hikeRequest.lengthInKilometers(), season);
        List<String> items = itemService.getListOfSeasonItemNames(hikeRequest.season());

        return new HikeRecommendations(sleepoverCount, foodCalories, litersOfWater, items);
    }

    private int getSleepoverCount(long lengthInKilometers) {
        return (int) Math.floor((double) lengthInKilometers / Constants.KILOMETERS_BEFORE_SLEEP);
    }

    private double getLitersOfWater(long lengthInKilometers, Season season) {
        return (double) lengthInKilometers / Constants.KILOMETERS_PER_WATER_LITRE * season.getWaterMultiplier();
    }

    private int getFoodCalories(int sleepoverCount, long lengthInKilometers, Season season) {
        int baseDailyCalories = sleepoverCount * Constants.BASE_DAILY_CALORIES;
        int hikeCalories =
                (int) (lengthInKilometers * Constants.CALORIES_BURNED_PER_KILOMETER * season.getFoodMultiplier());
        return baseDailyCalories + hikeCalories;
    }
}
