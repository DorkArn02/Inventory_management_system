package hu.pte.inventory_management_system.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
public class OrdersDTO {
    private Integer order_id;
    private LocalDateTime begin;
    private LocalDateTime expectedDelivery;
    private Boolean completed;
    private List<OrderedItemsDTO> orderedItemsDTOS;
}
