package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Category;
import com.roma.librarymanagment.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private Category category;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(String category_) {

        if(category_ != null && !StringUtils.isEmpty(category_)){
            category = new Category(category_);
            categoryRepository.save(category);
        }
        return category;
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }
    public  Category findCategoryById(Long id){
       return categoryRepository.findById(id).isPresent() ? categoryRepository.findById(id).get() : null;
    }
    public void deleteCategory(Long id){
        category = findCategoryById(id);
        categoryRepository.delete(category);
    }

    public  Category updateCategory(Category category_){
        category = findCategoryById(category_.getId());
        if(category != null){
            category.setName(category_.getName());
            categoryRepository.save(category);
        }
        return category;
    }
}
