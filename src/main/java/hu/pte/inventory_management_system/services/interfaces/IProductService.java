package hu.pte.inventory_management_system.services.interfaces;

import hu.pte.inventory_management_system.models.dtos.ProductRequestDTO;
import hu.pte.inventory_management_system.models.dtos.ProductResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    ProductRequestDTO addProduct(ProductRequestDTO product);
    ProductResponseDTO getProductById(Integer id);
    void deleteProductById(Integer id);
    ProductRequestDTO updateProductById(Integer id, ProductRequestDTO productNew);
    List<ProductResponseDTO> getAllProducts();
    void setProductCategory(Integer productId, Integer categoryId);
    void deleteProductCategory(Integer productId, Integer categoryId);
    void uploadPictureToProduct(Integer id, MultipartFile file) throws IOException;
    ResponseEntity<?> getImage(Integer productId);
}
