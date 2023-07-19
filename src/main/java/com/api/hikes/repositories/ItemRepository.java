package com.api.hikes.repositories;

import com.api.hikes.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findBySeason(String season);

    List<Item> findAllByOrderByIdAsc();
}
