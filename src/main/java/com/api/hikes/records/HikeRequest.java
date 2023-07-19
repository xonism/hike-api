package com.api.hikes.records;

import java.util.Objects;

public record HikeRequest(long lengthInKilometers, String season) {

    public HikeRequest { // TODO: return meaningful exception messages when null values are provided
        Objects.requireNonNull(season);
    }
}
