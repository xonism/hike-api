package com.api.hikes.services;

import com.api.hikes.entities.Item;
import com.api.hikes.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    private ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<String> getListOfSeasonItemNames(String season) {
        return itemRepository.findBySeason(season.toLowerCase()).stream()
                .map(Item::getName)
                .toList();
    }
}
