package hu.pte.inventory_management_system.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(StorageId.class)
public class Storage implements Serializable{
    @Id
    @Column(name="product_id")
    @JsonIgnore
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable =false)
    @JsonUnwrapped
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @Min(1)
    @Digits(integer = 10, fraction = 0)
    private Integer quantity;
}
