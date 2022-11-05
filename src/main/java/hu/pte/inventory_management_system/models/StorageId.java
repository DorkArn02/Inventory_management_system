package hu.pte.inventory_management_system.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StorageId implements Serializable {
    private Integer productId;
}
