package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.model.Category;
import com.roma.librarymanagment.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class CategoryController {
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(path = "/savecategory", method = RequestMethod.POST)
    public String addCategory(Model model,@ModelAttribute Category category){
        final Category category1 = categoryService.add(category.getName());
        model.addAttribute("categories",new ArrayList<Category>(){{add(category1);}});
        return "menu";
    }

    @RequestMapping(path = "/savecategory", method = RequestMethod.GET)
    private String saveCategory(Model model){

        model.addAttribute("category",new Category());
        return "addCategory";
    }



    @RequestMapping(path = "/categories", method = RequestMethod.GET)
    public String listCategories(Model model){
        if(categoryService.findAll() != null){
            model.addAttribute("categories", categoryService.findAll());
        }
        return "categories";
    }
}
