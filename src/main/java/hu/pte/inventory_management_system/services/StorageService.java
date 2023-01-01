package hu.pte.inventory_management_system.services;

import hu.pte.inventory_management_system.models.Product;
import hu.pte.inventory_management_system.models.Transaction;
import hu.pte.inventory_management_system.models.dtos.StorageResponseDTO;
import hu.pte.inventory_management_system.repositories.ProductRepository;
import hu.pte.inventory_management_system.repositories.TransactionRepository;
import hu.pte.inventory_management_system.services.interfaces.IStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StorageService implements IStorageService {
    private final ProductRepository productRepository;
    private final TransactionRepository transactionRepository;
    public StorageService(ProductRepository productRepository, TransactionRepository transactionRepository) {
        this.productRepository = productRepository;
        this.transactionRepository = transactionRepository;
    }

    /**
     * Adds a new product to the storage
     *
     * @param productId PathVariable
     * @param quantity  RequestBody
     * @return Storage
     */
    @Override
    public StorageResponseDTO addProductToStorage(Integer productId, Integer quantity){
        if(productRepository.findById(productId).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Product product = productRepository.findById(productId).get();

        if(quantity <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        product.setQuantity(product.getQuantity() + quantity);

        Transaction t = Transaction.builder().isImport(true)
                .product(product).quantity(quantity)
                .timeOfTransaction(LocalDateTime.now())
                        .build();

        productRepository.save(product);

        transactionRepository.save(t);

        return new StorageResponseDTO(product);
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

       Product product = productRepository.findById(productId).get();

        product.setQuantity(product.getQuantity() - quantity);

        Transaction t = Transaction.builder().isImport(false)
                .product(product).quantity(quantity)
                .timeOfTransaction(LocalDateTime.now())
                .build();

        if(product.getQuantity() < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        productRepository.save(product);

        transactionRepository.save(t);
    }

    /**
     * Returns the storage's contents
     *
     * @return List<Storage>
     */
    @Override
    public List<StorageResponseDTO> getProductsFromStorage(){
        List<StorageResponseDTO> products = new ArrayList<>();

        productRepository.findAll().forEach(product -> products.add(new StorageResponseDTO(product)));

        return products;
    }
}
