package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(String category);
    List<Category> listCategories();
    Category findCategoryById(Long id);
    void deleteCategory(Long id);
    Category updateCategory(Category category);
}
