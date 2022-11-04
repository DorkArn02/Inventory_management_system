package hu.pte.inventory_management_system.controllers;

import hu.pte.inventory_management_system.models.Category;
import hu.pte.inventory_management_system.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<Category> addNewCategory(@RequestBody @Valid Category category){
        return new ResponseEntity<>(categoryService.addNewCategory(category), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Integer id){
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Integer id){
        categoryService.deleteCategoryById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategoryById(@PathVariable Integer id, @RequestBody @Valid Category category){
        return new ResponseEntity<>(categoryService.updateCategoryById(id, category), HttpStatus.OK);
    }
}
