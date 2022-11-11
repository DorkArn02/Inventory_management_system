package hu.pte.inventory_management_system.dtos;

import hu.pte.inventory_management_system.models.Category;
import lombok.Data;

import java.util.Set;

@Data
public class ProductDTO {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private String thumbnail;
    private Set<Category> productCategories;
}
