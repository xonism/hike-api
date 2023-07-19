package com.api.hikes.records;

import lombok.Builder;

import java.util.Objects;

@Builder
public record ItemRequest(String name, String season) {

    public ItemRequest {
        Objects.requireNonNull(name);
        Objects.requireNonNull(season);
    }
}
