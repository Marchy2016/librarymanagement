package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.model.Category;
import com.roma.librarymanagment.services.CategoryService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Configuration
@PropertySource("classpath:libraryconfig.properties")
@ConfigurationProperties(prefix = "book")
@Setter
@Getter
public class CategoryController {
    CategoryService categoryService;
    private String displayCategories;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(path = "/savecategory", method = RequestMethod.POST)
    public String addCategory(Model model,@ModelAttribute Category category){
        final Category category1 = categoryService.addCategory(category.getName());
        model.addAttribute("category",category1);
        return "category";
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
        return displayCategories;
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
