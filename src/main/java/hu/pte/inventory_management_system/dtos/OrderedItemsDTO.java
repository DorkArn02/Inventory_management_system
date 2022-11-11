package hu.pte.inventory_management_system.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class OrderedItemsDTO {
    private Integer product_id;
    private String name;
    private String description;
    private Float price;
    private Integer quantity;
    private Float total_price;
    private String thumbnail;
}
