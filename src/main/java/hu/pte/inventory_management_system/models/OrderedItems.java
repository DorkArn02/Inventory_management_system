package hu.pte.inventory_management_system.models;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderedItems {
    @EmbeddedId
    @JsonUnwrapped
    private OrderedItemsId orderedItemsId;

    @Min(value=1)
    private Integer quantity;
}
