package com.auction_website.service.category;

import com.auction_website.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Integer categoryId);
}
