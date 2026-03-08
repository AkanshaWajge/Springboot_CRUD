package com.codewith.firstApp.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewith.firstApp.repository.FoodItemRepository;  
import com.codewith.firstApp.entity.FoodItems;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodItemController {

    private FoodItemRepository foodItemRepository;

    public FoodItemController( FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }
  //Get all food items(Read)
    @GetMapping
    public List<FoodItems> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    //Create a new food item(Create)
    @PostMapping
    public FoodItems createFoodItem(FoodItems foodItem) {
        return foodItemRepository.save(foodItem);
    }

    //Get a food item by id(Read)
    @GetMapping("/{id}")
    public FoodItems getFoodItemById(@PathVariable int id) {
        FoodItems foodItem = foodItemRepository.findById(id).orElse(null);
        if (foodItem == null) {
            throw new RuntimeException("Food item not found with id: " + id);
        }
        return foodItem;
    }

    //Update a food item(Update)
    @PutMapping("/{id}")
    public FoodItems updateFoodItem(@PathVariable int id, @RequestBody FoodItems foodItemDetails) {
        FoodItems foodItem = foodItemRepository.findById(id).orElse(null);
        if (foodItem != null) {
            // Update the food item details
            foodItem.setName(foodItemDetails.getName());
            foodItem.setPrice(foodItemDetails.getPrice());
            return foodItemRepository.save(foodItem);
        }
        return null;
    }
    
    //Delete a food item(Delete)
    @DeleteMapping("/{id}")
    public void deleteFoodItem(@PathVariable int id) {
        foodItemRepository.deleteById(id);      
    }

}