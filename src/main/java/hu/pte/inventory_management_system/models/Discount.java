package hu.pte.inventory_management_system.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, precision = 2)
    @NotNull
    private Float percent;

    @Column(nullable = false)
    @NotNull
    private Date begin;

    @Column(nullable = false)
    @NotNull
    private Date end;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;
}
