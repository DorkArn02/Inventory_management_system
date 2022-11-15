package hu.pte.inventory_management_system.models.dtos;

import hu.pte.inventory_management_system.models.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class OrderRequestDTO {
    @NotNull
    private LocalDateTime expectedDelivery;

    public OrderRequestDTO(Orders orders){
        this.expectedDelivery = orders.getExpectedDelivery();
    }
}
