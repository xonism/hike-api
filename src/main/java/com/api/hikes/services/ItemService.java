package com.api.hikes.services;

import com.api.hikes.entities.Item;
import com.api.hikes.enums.Season;
import com.api.hikes.records.ItemRequest;
import com.api.hikes.repositories.ItemRepository;
import com.api.hikes.utils.SeasonUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    private ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item create(ItemRequest itemRequest) {
        Season season = SeasonUtils.getSeasonOrThrow(itemRequest.season());

        Item item = Item.builder()
                .name(itemRequest.name().trim().toLowerCase())
                .season(season.name().toLowerCase())
                .build();

        return itemRepository.save(item);
    }

    public void delete(long id) {
        itemRepository.deleteById(id);
    }

    public List<String> getListOfSeasonItemNames(String season) {
        return itemRepository.findBySeason(season.toLowerCase()).stream()
                .map(Item::getName)
                .toList();
    }
}
