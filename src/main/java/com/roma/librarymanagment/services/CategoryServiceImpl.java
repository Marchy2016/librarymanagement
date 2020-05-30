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
    public Category addCategory(String categoryName) {
         if(!"".equals(categoryName) && !StringUtils.isEmpty(categoryName)){
            Category  category = new Category(categoryName);
            return categoryRepository.save(category);
        }else{
            return null;
        }

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
        Category categry = findCategoryById(category.getId());
        if(categry != null){
            categry.setName(category.getName());
            return categoryRepository.save(categry);
        }else{
            return null;
        }
    }
}
