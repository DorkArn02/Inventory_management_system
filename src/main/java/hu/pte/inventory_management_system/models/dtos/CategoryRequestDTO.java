package hu.pte.inventory_management_system.models.dtos;

import hu.pte.inventory_management_system.models.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CategoryRequestDTO {
    @NotBlank(message = "Category name should not be blank!")
    private String name;
    public CategoryRequestDTO(Category category) {
        this.name = category.getName();
    }
}
