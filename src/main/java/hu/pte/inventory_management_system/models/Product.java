package hu.pte.inventory_management_system.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank
    private String description;

    @Column(nullable = false, precision = 2)
    @NotNull
    private Float price;

    @Column(nullable = false)
    @NotBlank
    private String thumbnail;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JoinTable(
            name = "ProductCategories",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private Set<Category> productCategories;
}
