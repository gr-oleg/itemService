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

    @PostMapping("/add")
    public String add(@RequestBody item item){
        Optional<item> existingItem = itemService.findById(item.getId());
        itemService.saveItem(item);
        return "New item is added";
    }

    @GetMapping("/getAll")
    public List<item> getAllItem(){
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
        Optional<item> getUserById(@PathVariable int id){
        return itemService.findById(id);
    }

    private itemRepository itemRepository;
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        itemRepository.deleteById(id);
        return "Item with id " + id + " has been deleted successfully.";
    }
}