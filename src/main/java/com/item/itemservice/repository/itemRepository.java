package com.item.itemservice.repository;

import com.item.itemservice.model.item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface itemRepository extends JpaRepository<item, Long> {
    Optional<item> findById(int id);
}