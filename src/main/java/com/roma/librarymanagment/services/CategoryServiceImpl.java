package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Category;
import com.roma.librarymanagment.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category add(String category) {
        Category category1 = null;
        if(category != null && !StringUtils.isEmpty(category)){
            category1 = new Category(category);
        }
        return categoryRepository.save(category1);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
