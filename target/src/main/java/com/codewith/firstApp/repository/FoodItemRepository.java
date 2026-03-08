package com.codewith.firstApp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.codewith.firstApp.entity.FoodItems;

public interface FoodItemRepository extends JpaRepository<FoodItems, Integer> {
    
}
