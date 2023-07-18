package com.api.hikes.controllers;

import com.api.hikes.records.HikeRecommendations;
import com.api.hikes.records.HikeRequest;
import com.api.hikes.services.HikesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        path = "/api/v1/hikes",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class HikesController {

    private final HikesService hikesService;

    public HikesController(HikesService hikesService) {
        this.hikesService = hikesService;
    }

    @PostMapping
    public ResponseEntity<HikeRecommendations> getHikeRecommendations(
            @RequestBody HikeRequest hikeRequest
    ) {
        return new ResponseEntity<>(
                hikesService.getHikeRecommendations(hikeRequest),
                HttpStatus.OK);
    }
}
