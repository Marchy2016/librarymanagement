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
    public Category addCategory(String category) {
        Category category1 = null;
        if(category != null && !StringUtils.isEmpty(category)){
            category1 = new Category(category);
        }
        return categoryRepository.save(category1);
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }
    public  Category findCategoryById(Long id){
       return categoryRepository.findById(id).isPresent() ? categoryRepository.findById(id).get() : null;
    }
    public void deleteCategory(Long id){
        Category category = findCategoryById(id);
        categoryRepository.delete(category);
    }

    public  Category updateCategory(Category category){
        Category category1 = findCategoryById(category.getId());
        if(category1 != null){
            category1.setName(category.getName());
            categoryRepository.save(category1);
        }
        return category1;
    }
}
