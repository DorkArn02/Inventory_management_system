package hu.pte.inventory_management_system.services.interfaces;

import hu.pte.inventory_management_system.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    Product addProduct(Product product);
    Product getProductById(Integer id);
    void deleteProductById(Integer id);
    Product updateProductById(Integer id, Product productNew);
    List<Product> getAllProducts();
    Product setProductCategory(Integer productId, Integer categoryId);
    void deleteProductCategory(Integer productId, Integer categoryId);
    void uploadPictureToProduct(Integer id, MultipartFile file) throws IOException;
    ResponseEntity<?> getImage(Integer productId) throws IOException;
}
