package hu.pte.inventory_management_system.services.interfaces;

import hu.pte.inventory_management_system.models.dtos.CategoryRequestDTO;
import hu.pte.inventory_management_system.models.dtos.CategoryResponseDTO;

import java.util.List;

public interface ICategoryService {
    CategoryRequestDTO addCategory(CategoryRequestDTO category);
    CategoryResponseDTO getCategoryById(Integer id);
    List<CategoryResponseDTO> getCategories();
    void deleteCategoryById(Integer id);
    CategoryRequestDTO updateCategoryById(Integer id, CategoryRequestDTO categoryNew);

}
