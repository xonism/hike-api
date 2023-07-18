package com.api.hikes.records;

import lombok.Builder;

import java.util.List;
import java.util.Objects;

@Builder
public record HikeRecommendations(int sleepoverCount, int foodCalories, double litersOfWater, List<String> items) {

    public HikeRecommendations {
        Objects.requireNonNull(items);
    }
}
