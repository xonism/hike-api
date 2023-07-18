package com.api.hikes.enums;

import lombok.Getter;

@Getter
public enum Season {

    WINTER(1, 1.5),
    SPRING(1.25, 1.25),
    SUMMER(1.5, 1),
    AUTUMN(1.25, 1.25);

    private final double waterMultiplier;
    private final double foodMultiplier;

    Season(double waterMultiplier, double foodMultiplier) {
        this.waterMultiplier = waterMultiplier;
        this.foodMultiplier = foodMultiplier;
    }
}
