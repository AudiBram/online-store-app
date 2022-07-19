package com.project.service;

import com.project.entity.Category;
import com.project.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void updateCategory(Long id, Category categories) {
        Category category = categoryRepository.getReferenceById(id);
        category.setCategoryName(categories.getCategoryName());
        category.setDescription(categories.getDescription());
        categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        boolean exists = categoryRepository.existsById(id);
        if(!exists) {
            throw new IllegalArgumentException(String.format("Id %s is not found"));
        }
        categoryRepository.deleteById(id);
    }

    public boolean findById(Long id) {
        return categoryRepository.findById(id).isEmpty();
    }
}
