package hu.pte.inventory_management_system.services;

import hu.pte.inventory_management_system.models.Category;
import hu.pte.inventory_management_system.models.dtos.CategoryRequestDTO;
import hu.pte.inventory_management_system.models.dtos.CategoryResponseDTO;
import hu.pte.inventory_management_system.repositories.CategoryRepository;
import hu.pte.inventory_management_system.services.interfaces.ICategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Adds a new category to the DB
     * @param category RequestBody category
     * @return Created category
     */
    @Override
    public CategoryRequestDTO addCategory(CategoryRequestDTO category){
        // No category sent in request body
        if(category.getName() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        // Category name is unique
        if(categoryRepository.findByName(category.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        Category cat = new Category();
        cat.setName(category.getName());

        return new CategoryRequestDTO(categoryRepository.save(cat));
    }

    /**
     * Gets a specified category by id
     *
     * @param id PathVariable category's id
     * @return Category
     */
    @Override
    public CategoryResponseDTO getCategoryById(Integer id){
        if(categoryRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new CategoryResponseDTO(categoryRepository.findById(id).get());
    }

    /**
     * Gets list of categories
     *
     * @return List of categories
     */
    @Override
    public List<CategoryResponseDTO> getCategories(){
        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();
        categoryRepository.findAll().forEach(category -> categoryResponseDTOS.add(new CategoryResponseDTO(category)));
        return categoryResponseDTOS;
    }

    /**
     * Deletes a specified category
     * @param id PathVariable category's id
     */
    @Override
    public void deleteCategoryById(Integer id){
        if(categoryRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        // PHPMYADMIN -> PRODUCT_CATEGORIES -> STRUCTURE -> TWO FOREIGN KEYS ON CASCADE DELETE
        categoryRepository.deleteById(id);
    }

    /**
     * Updates a category with specified id
     * @param id PathVariable category's id
     * @param categoryNew RequestBody Category
     * @return Updated category
     */
    @Override
    public CategoryRequestDTO updateCategoryById(Integer id, CategoryRequestDTO categoryNew){
        // No category sent in request body
        if(categoryNew.getName() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        // No category found
        if(categoryRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        // Category name is unique
        if(categoryRepository.findByName(categoryNew.getName()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        Category category = categoryRepository.findById(id).get();
        category.setName(categoryNew.getName());

        return new CategoryRequestDTO(categoryRepository.save(category));
    }
}
