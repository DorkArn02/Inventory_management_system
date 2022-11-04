package hu.pte.inventory_management_system.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderedItemsId implements Serializable {
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Orders orders;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;
}
