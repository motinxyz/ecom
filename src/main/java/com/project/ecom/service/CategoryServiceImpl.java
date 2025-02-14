package com.project.ecom.service;

import com.project.ecom.model.Category;
import com.project.ecom.repositories.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Category Id"));

        categoryRepository.delete(category);
        return "Category with id " + categoryId + " is deleted";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {

        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Category Id"));

        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);

        return savedCategory;
    }

//    private List<Category> categories = new ArrayList<>();
//    private Long nextId = 1L;
//
//    @Override
//    public List<Category> getAllCategories() {
//        return categories;
//    }

//    @Override
//    public void createCategory(Category category) {
//        category.setCategoryId(nextId++);
//        categories.add(category);
//    }
//
//    @Override
//    public String deleteCategory(Long categoryId) {
//        Category cat = categories.stream()
//                .filter(e -> e.getCategoryId().equals(categoryId))
//                .findFirst()
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found!"));
//
//        categories.remove(cat);
//        return "Category "  +cat.getCategoryId() +" successfully deleted";
//    }
//
//    @Override
//    public Category updateCategory(Category category, Long categoryId) {
//
//        Optional<Category> optionalCategory = categories.stream()
//                .filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst();
//        if(optionalCategory.isPresent()){
//            Category oldCat = optionalCategory.get();
//            oldCat.setCategoryName(category.getCategoryName());
//            return oldCat;
//        }else{
//            throw new ResponseStatusException((HttpStatus.NOT_FOUND), "Resource Not Found");
//        }
//    }
}
