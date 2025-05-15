package com.item.itemservice.controller;

import com.item.itemservice.model.item;
import com.item.itemservice.repository.itemRepository;
import com.item.itemservice.service.itemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class itemController {

    @Autowired
    private itemService itemService;

    @Autowired
    private itemRepository itemRepository;

    @PostMapping("/add")
    public String add(@RequestBody item item) {
        itemService.saveItem(item);
        return "New item is added";
    }

    @GetMapping("/getAll")
    public List<item> getAllItem() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Optional<item> getItemById(@PathVariable int id) {
        return itemService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemRepository.deleteById(id);
        return "Item with id " + id + " has been deleted successfully.";
    }

    // Нова функція для оновлення товару
    @PutMapping("/{id}")
    public String updateItem(@PathVariable Long id, @RequestBody item updatedItem) {
        Optional<item> existingItem = itemService.findById(id.intValue());
        if (existingItem.isPresent()) {
            updatedItem.setId(id.intValue());
            itemService.saveItem(updatedItem);
            return "Item with id " + id + " has been updated.";
        } else {
            return "Item not found!";
        }
    }
}
