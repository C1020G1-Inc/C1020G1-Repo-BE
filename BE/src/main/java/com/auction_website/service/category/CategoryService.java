package com.auction_website.service.category;

import com.auction_website.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category findById(Integer categoryId);
}
