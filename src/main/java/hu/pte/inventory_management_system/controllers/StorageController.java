package hu.pte.inventory_management_system.controllers;

import hu.pte.inventory_management_system.models.Storage;
import hu.pte.inventory_management_system.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;

    @PostMapping("/{productId}")
    public ResponseEntity<Storage> addProductToStorage(@PathVariable Integer productId, @RequestBody Integer quantity) {
        return new ResponseEntity<>(storageService.addProductToStorage(productId, quantity), HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}")
    public void removeProductFromStorage(@PathVariable Integer productId, @RequestBody Integer quantity) {
        storageService.removeProductFromStorage(productId, quantity);
    }

    @GetMapping("/")
    public List<Storage> getProductsFromStorage() {
        return storageService.getProductsFromStorage();
    }
}