package hu.pte.inventory_management_system.services.interfaces;

import hu.pte.inventory_management_system.models.dtos.StorageResponseDTO;

import java.util.List;

public interface IStorageService {
    StorageResponseDTO addProductToStorage(Integer productId, Integer quantity);
    void removeProductFromStorage(Integer productId, Integer quantity);
    List<StorageResponseDTO> getProductsFromStorage();
}
