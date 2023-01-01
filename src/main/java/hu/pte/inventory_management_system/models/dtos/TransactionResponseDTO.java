package hu.pte.inventory_management_system.models.dtos;

import hu.pte.inventory_management_system.models.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TransactionResponseDTO {
    private Integer id;
    private LocalDateTime timeOfTransaction;
    private Integer product_id;
    private Integer quantity;
    private Boolean isImport;

    public TransactionResponseDTO(Transaction t){
        this.id = t.getId();
        this.timeOfTransaction = t.getTimeOfTransaction();
        this.product_id = t.getProduct().getId();
        this.quantity = t.getQuantity();
        this.isImport = t.getIsImport();
    }

}
