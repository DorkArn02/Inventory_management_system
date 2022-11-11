package hu.pte.inventory_management_system.services;

import hu.pte.inventory_management_system.models.Storage;
import hu.pte.inventory_management_system.models.StorageId;
import hu.pte.inventory_management_system.repositories.ProductRepository;
import hu.pte.inventory_management_system.repositories.StorageRepository;
import hu.pte.inventory_management_system.services.interfaces.IStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StorageService implements IStorageService {
    private final StorageRepository storageRepository;

    private final ProductRepository productRepository;

    public StorageService(StorageRepository storageRepository, ProductRepository productRepository) {
        this.storageRepository = storageRepository;
        this.productRepository = productRepository;
    }

    /**
     * Adds a new product to the storage
     * @param productId PathVariable
     * @param quantity RequestBody
     * @return Storage
     */
    @Override
    public Storage addProductToStorage(Integer productId, Integer quantity){
        if(productRepository.findById(productId).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if(quantity <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        StorageId storageId = new StorageId();
        storageId.setProductId(productId);

        if(storageRepository.findById(storageId).isEmpty()){
            Storage storage = new Storage();
            storage.setProduct(productRepository.findById(productId).get());
            storage.setQuantity(quantity);

            return storageRepository.save(storage);
        }

        Storage storage = storageRepository.findById(storageId).get();
        storage.setQuantity(storage.getQuantity()+quantity);
        return storageRepository.save(storage);
    }

    /**
     * Removes an existing product from the storage
     * @param productId PathVariable
     * @param quantity RequestBody
     */
    @Override
    public void removeProductFromStorage(Integer productId, Integer quantity){
        if(productRepository.findById(productId).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if(quantity <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        StorageId storageId = new StorageId();
        storageId.setProductId(productId);

        if(storageRepository.findById(storageId).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Storage storage = storageRepository.findById(storageId).get();
        storage.setQuantity(storage.getQuantity()-quantity);

        if(storage.getQuantity() == 0){
            storageRepository.deleteById(storageId);
        }else if(storage.getQuantity() < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        storageRepository.save(storage);
    }

    /**
     * Returns the storage's contents
     * @return List<Storage>
     */
    @Override
    public List<Storage> getProductsFromStorage(){
        return storageRepository.findAll();
    }
}
