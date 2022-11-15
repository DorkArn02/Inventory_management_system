package hu.pte.inventory_management_system.controllers;

import hu.pte.inventory_management_system.models.dtos.CategoryRequestDTO;
import hu.pte.inventory_management_system.models.dtos.CategoryResponseDTO;
import hu.pte.inventory_management_system.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@Validated
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/")
    public ResponseEntity<CategoryRequestDTO> addNewCategory(@RequestBody @Valid CategoryRequestDTO category){
        return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<CategoryResponseDTO> getCategories(){
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO getCategoryById(@PathVariable Integer id){
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Integer id){
        categoryService.deleteCategoryById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryRequestDTO> updateCategoryById(@PathVariable Integer id, @RequestBody @Valid CategoryRequestDTO category){
        return new ResponseEntity<>(categoryService.updateCategoryById(id, category), HttpStatus.OK);
    }
}
