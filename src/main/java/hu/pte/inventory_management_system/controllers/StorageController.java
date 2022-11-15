package hu.pte.inventory_management_system.controllers;

import hu.pte.inventory_management_system.models.dtos.StorageResponseDTO;
import hu.pte.inventory_management_system.services.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/storage")
@Validated
public class StorageController {
    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<StorageResponseDTO> addProductToStorage(@PathVariable Integer productId, @RequestBody @Valid Integer quantity) {
        return new ResponseEntity<>(storageService.addProductToStorage(productId, quantity), HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}")
    public void removeProductFromStorage(@PathVariable Integer productId, @RequestBody @Valid Integer quantity) {
        storageService.removeProductFromStorage(productId, quantity);
    }

    @GetMapping("/")
    public List<StorageResponseDTO> getProductsFromStorage() {
        return storageService.getProductsFromStorage();
    }
}