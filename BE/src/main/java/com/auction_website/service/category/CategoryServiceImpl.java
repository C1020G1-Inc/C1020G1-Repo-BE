package com.auction_website.service.category;

import com.auction_website.model.Category;
import com.auction_website.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * author: ThinhTHB
     * method: get category
     * */
    public Iterable<Category> getCategory(){
      return categoryRepository.getCategory();
    };
}
