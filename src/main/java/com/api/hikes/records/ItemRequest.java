package com.api.hikes.records;

import java.util.Objects;

public record ItemRequest(String name, String season) {

    public ItemRequest {
        Objects.requireNonNull(name);
        Objects.requireNonNull(season);
    }
}
