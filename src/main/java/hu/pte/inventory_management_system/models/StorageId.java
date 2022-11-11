package hu.pte.inventory_management_system.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StorageId implements Serializable {
    private Integer productId;

    // Composite-id class should override equals() and hashCode()
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
