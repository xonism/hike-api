package com.api.hikes.utils;

import com.api.hikes.enums.Season;
import com.api.hikes.exceptions.InvalidSeasonException;

public class SeasonUtils {

    public static Season getSeasonIfValid(String season) {
        try {
            return Season.valueOf(season.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new InvalidSeasonException(season);
        }
    }
}
