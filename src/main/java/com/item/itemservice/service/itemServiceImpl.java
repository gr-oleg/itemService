package com.item.itemservice.service;

import com.item.itemservice.model.item;
import com.item.itemservice.repository.itemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class itemServiceImpl implements itemService {

    @Autowired
    private itemRepository itemRepository;

    @Override
    public item saveItem(item item) {
        return itemRepository.save(item);
    }

    @Override
    public item saveitem(item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Optional<item> findById(int id) {
       return itemRepository.findById(id);
    }
}