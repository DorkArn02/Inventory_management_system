package hu.pte.inventory_management_system.controllers;

import hu.pte.inventory_management_system.models.Product;
import hu.pte.inventory_management_system.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Product> addNewProduct(@RequestBody @Valid Product product){
        return new ResponseEntity<>(productService.addNewProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable Integer id, @RequestBody @Valid Product product){
        return new ResponseEntity<>(productService.updateProductById(id, product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Integer id){
        productService.deleteProductById(id);
    }

    @GetMapping("/")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PutMapping("/{productId}/{categoryId}")
    public ResponseEntity<Product> setProductCategory(@PathVariable Integer productId, @PathVariable Integer categoryId){
        return new ResponseEntity<>(productService.setProductCategory(productId, categoryId), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}/{categoryId}")
    public void deleteProductCategory(@PathVariable Integer productId, @PathVariable Integer categoryId){
        productService.deleteProductCategory(productId, categoryId);
    }

    @PostMapping(path =  "/thumbnail/{id}", consumes = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public void uploadPictureToProduct(@PathVariable Integer id, @RequestBody MultipartFile file) throws IOException {
        productService.uploadPictureToProduct(id, file);
    }

    @GetMapping(path = "/thumbnail/{productId}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public ResponseEntity<?> getImage(@PathVariable Integer productId) throws IOException {
        return productService.getImage(productId);
    }
}
