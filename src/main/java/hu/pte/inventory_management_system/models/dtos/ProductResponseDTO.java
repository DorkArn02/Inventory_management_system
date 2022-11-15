package hu.pte.inventory_management_system.models.dtos;

import hu.pte.inventory_management_system.models.Category;
import hu.pte.inventory_management_system.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ProductResponseDTO {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private String thumbnail;
    private Set<Category> productCategories;

    public ProductResponseDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.thumbnail = product.getThumbnail();
        this.productCategories = product.getProductCategories();
    }

}
