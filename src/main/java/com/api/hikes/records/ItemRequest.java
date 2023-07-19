package com.api.hikes.records;

import lombok.Builder;

import java.util.Objects;

@Builder
public record ItemRequest(String name, String season) {

    public ItemRequest { // TODO: return meaningful exception messages when null values are provided
        Objects.requireNonNull(name);
        Objects.requireNonNull(season);
    }
}
