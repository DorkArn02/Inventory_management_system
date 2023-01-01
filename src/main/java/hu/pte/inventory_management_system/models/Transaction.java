package hu.pte.inventory_management_system.models;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime timeOfTransaction;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;
    private Integer quantity;
    private Boolean isImport;
}
