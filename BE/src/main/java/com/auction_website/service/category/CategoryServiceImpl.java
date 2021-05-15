package com.auction_website.service.category;

import com.auction_website.model.Category;
import com.auction_website.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Author : TungNT
     * Get category by id.
     */
    @Override
    public Category findById(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAllCategory();
    }
}
