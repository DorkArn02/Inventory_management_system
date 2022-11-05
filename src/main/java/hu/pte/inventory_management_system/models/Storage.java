package hu.pte.inventory_management_system.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(StorageId.class)
public class Storage implements Serializable{
    @Id
    @GeneratedValue
    @Column(name="product_id")
    @JsonIgnore
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable =false)
    private Product product;

    private Integer quantity;
}
