package hu.pte.inventory_management_system.models.dtos;

import hu.pte.inventory_management_system.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class StorageResponseDTO {
    private Integer product_id;
    private String product_name;
    private Integer quantity;

    public StorageResponseDTO(Product product){
        this.product_id = product.getId();
        this.product_name = product.getName();
        this.quantity = product.getQuantity();
    }
}
