package hu.pte.inventory_management_system.services.interfaces;

import hu.pte.inventory_management_system.models.Storage;

import java.util.List;

public interface IStorageService {
    Storage addProductToStorage(Integer productId, Integer quantity);
    void removeProductFromStorage(Integer productId, Integer quantity);
    List<Storage> getProductsFromStorage();
}
