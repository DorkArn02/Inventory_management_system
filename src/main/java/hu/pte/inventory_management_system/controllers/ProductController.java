package hu.pte.inventory_management_system.controllers;

import hu.pte.inventory_management_system.dtos.ProductDTO;
import hu.pte.inventory_management_system.models.Product;
import hu.pte.inventory_management_system.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/")
    public ResponseEntity<ProductDTO> addNewProduct(@RequestBody @Valid Product product){
        return new ResponseEntity<>(modelMapper.map(productService.addProduct(product), ProductDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProductById(@PathVariable Integer id, @RequestBody @Valid Product product){
        return new ResponseEntity<>(modelMapper.map(productService.updateProductById(id, product), ProductDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Integer id){
        productService.deleteProductById(id);
    }

    @GetMapping("/")
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts().stream().map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id){
        return new ResponseEntity<>(modelMapper.map(productService.getProductById(id), ProductDTO.class), HttpStatus.OK);
    }

    @PutMapping("/{productId}/{categoryId}")
    public ResponseEntity<ProductDTO> setProductCategory(@PathVariable Integer productId, @PathVariable Integer categoryId){
        return new ResponseEntity<>(modelMapper.map(productService.setProductCategory(productId, categoryId), ProductDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}/{categoryId}")
    public void deleteProductCategory(@PathVariable Integer productId, @PathVariable Integer categoryId){
        productService.deleteProductCategory(productId, categoryId);
    }

    @PostMapping(path =  "/thumbnail/{id}", consumes = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public void uploadPictureToProduct(@PathVariable Integer id, @RequestBody @Valid MultipartFile file) throws IOException {
        productService.uploadPictureToProduct(id, file);
    }

    @GetMapping(path = "/thumbnail/{productId}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public ResponseEntity<?> getImage(@PathVariable Integer productId) throws IOException {
        return productService.getImage(productId);
    }
}
