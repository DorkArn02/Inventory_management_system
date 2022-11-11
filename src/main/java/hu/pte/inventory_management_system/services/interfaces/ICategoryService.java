package hu.pte.inventory_management_system.services.interfaces;

import hu.pte.inventory_management_system.models.Category;

import java.util.List;

public interface ICategoryService {
    Category addCategory(Category category);
    Category getCategoryById(Integer id);
    List<Category> getCategories();
    void deleteCategoryById(Integer id);
    Category updateCategoryById(Integer id, Category categoryNew);

}
