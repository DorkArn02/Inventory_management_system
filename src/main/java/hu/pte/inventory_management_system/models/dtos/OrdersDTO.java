package hu.pte.inventory_management_system.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrdersDTO {
    private Integer id;
    private LocalDateTime created;
    private LocalDateTime expectedDelivery;
    private Boolean completed;
    private List<OrderedItemsDTO> orderedItemsList;
}
