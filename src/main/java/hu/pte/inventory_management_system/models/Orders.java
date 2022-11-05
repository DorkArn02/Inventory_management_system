package hu.pte.inventory_management_system.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @Column(nullable = false)
    @DateTimeFormat
    private LocalDateTime created;

    @Column(nullable = false)
    @DateTimeFormat
    @NotNull
    private LocalDateTime expectedDelivery;

    @Column(nullable = false)
    private Boolean completed;

    @OneToMany(mappedBy = "orderedItemsId.orders")
    @JsonManagedReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<OrderedItems> orderedItemsList;
}
