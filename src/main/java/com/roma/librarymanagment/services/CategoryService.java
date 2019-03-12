package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Category;

import java.util.List;

public interface CategoryService {
    Category add(String category);
    List<Category> findAll();
}
