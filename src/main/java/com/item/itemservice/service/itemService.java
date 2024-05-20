package com.item.itemservice.service;

import com.item.itemservice.model.item;

import java.util.List;
import java.util.Optional;

public interface itemService {
    public item saveItem(item item);

    item saveitem(item item);

    List<item> getAllItems();
    Optional<item> findById(int id);


}