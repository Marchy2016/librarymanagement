package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.config.BookProsConfig;
import com.roma.librarymanagment.model.Category;
import com.roma.librarymanagment.services.CategoryService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CategoryController {

    private CategoryService categoryService;
    private BookProsConfig bookProsConfig;

    public CategoryController(CategoryService categoryService, BookProsConfig bookProsConfig) {
        this.categoryService = categoryService;
        this.bookProsConfig = bookProsConfig;
    }

    @RequestMapping(path = "/savecategory", method = RequestMethod.POST)
    public String addCategory(Model model,@ModelAttribute Category category) {

        if(null != categoryService.findCategoryById(category.getId())) {
            throw new DuplicateKeyException("Author already exists");
        }else{
            final Category categry = categoryService.addCategory(category.getName());
            model.addAttribute("category",categry);
            return "category";
        }

    }

    @RequestMapping(path = "/savecategory", method = RequestMethod.GET)
    private String saveCategory(Model model){

        model.addAttribute("category",new Category());
        return "addCategory";
    }

    @RequestMapping(path = "/categories", method = RequestMethod.GET)
    public String listCategories(Model model){
        if(categoryService.listCategories() != null){
            model.addAttribute("categories", categoryService.listCategories());
        }
        return "categories";
    }
    @RequestMapping(path = "/deletecategory/{id}", method = RequestMethod.GET)
    public String deleteCategory(Model model, @PathVariable Long id){
       categoryService.deleteCategory(id);
        return bookProsConfig.getDisplayCategories();
    }

    @RequestMapping(path = "/findcategorybyid/{id}", method = RequestMethod.GET)
    public String findCategoryById(Model model, @PathVariable Long id){
        Category category = categoryService.findCategoryById(id);
        if(category != null){
            model.addAttribute("category", category);
        }
        return "updatecategory";
    }

    @RequestMapping(path = "/updatecategory", method = RequestMethod.POST)
    public String updateCategory(Model model, @ModelAttribute Category category){
        Category category_ = categoryService.findCategoryById(category.getId());
        if(category_ != null){
            category_.setName(category.getName());
            categoryService.updateCategory(category_);
            model.addAttribute("category", category_);
        }
        return "category";
    }
}
