package com.api.hikes.records;

import java.util.Objects;

public record HikeRequest(long lengthInKilometers, String season) {

    public HikeRequest {
        Objects.requireNonNull(season);
    }
}
