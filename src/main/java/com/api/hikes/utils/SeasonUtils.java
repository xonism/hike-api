package com.api.hikes.utils;

import com.api.hikes.enums.Season;
import com.api.hikes.exceptions.InvalidSeasonException;

public class SeasonUtils {

    private SeasonUtils() {

    }

    public static Season getSeasonOrThrow(String season) {
        try {
            return Season.valueOf(season.trim().toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new InvalidSeasonException(season);
        }
    }
}
