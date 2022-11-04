package hu.pte.inventory_management_system.services;

import hu.pte.inventory_management_system.models.Product;
import hu.pte.inventory_management_system.repositories.CategoryRepository;
import hu.pte.inventory_management_system.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Value("${images_location}")
    private String FILE_PATH;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Registers a new product to the database
     * @param product RequestBody Product object
     * @return Product
     */
    public Product addNewProduct(Product product){
        try {
            return productRepository.save(product);
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Gets a specified product by id
     * @param id PathVariable id of the product
     * @return Product
     */
    public Product getProductById(Integer id){
        if(productRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return productRepository.findById(id).get();
    }

    /**
     * Deletes a specified product by id
     * @param id PathVariable id of the product
     */
    public void deleteProductById(Integer id){
        if(productRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(id);
    }

    /**
     * Updates a specified product by id
     * @param id PathVariable id of the product
     * @param productNew RequestBody new product
     * @return Product
     */
    public Product updateProductById(Integer id, Product productNew){
        if(productRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Product product = productRepository.findById(id).get();
        product.setName(productNew.getName());
        product.setDescription(productNew.getDescription());
        product.setPrice(productNew.getPrice());
        product.setThumbnail(productNew.getThumbnail());

        return productRepository.save(product);
    }

    /**
     * Gets all products from the DB
     * @return List of products
     */
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    /**
     * Assigns a category to a specified product
     * @param productId PathVariable
     * @param categoryId PathVariable
     * @return Updated Product
     */
    public Product setProductCategory(Integer productId, Integer categoryId){
        if(productRepository.findById(productId).isEmpty() ||
        categoryRepository.findById(categoryId).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Product product = productRepository.findById(productId).get();

        if(product.getProductCategories().contains(categoryRepository.findById(categoryId).get())){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        product.getProductCategories().add(categoryRepository.findById(categoryId).get());

        return productRepository.save(product);
    }

    /**
     * Deletes a specified category from the product
     * @param productId PathVariable
     * @param categoryId PathVariable
     */
    public void deleteProductCategory(Integer productId, Integer categoryId){
        if(productRepository.findById(productId).isEmpty() ||
                categoryRepository.findById(categoryId).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Product product = productRepository.findById(productId).get();

        if(!product.getProductCategories().contains(categoryRepository.findById(categoryId).get())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        product.getProductCategories().remove(categoryRepository.findById(categoryId).get());

        productRepository.save(product);
    }

    /**
     * Upload thumbnail to a specified product
     * @param id PathVariable id
     * @param file RequestBody MultipartFile
     */
    public void uploadPictureToProduct(Integer id, MultipartFile file) throws IOException {

        if(productRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if(file.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Product product = productRepository.findById(id).get();

        String ext = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];

        product.setThumbnail(product.getName() + "." + ext);

        file.transferTo(new File(FILE_PATH + product.getName() + "." + ext));

        productRepository.save(product);
    }

    /**
     * Get the thumbnail from specified product
     * @param productId PathVariable id
     * @return Product's thumbnail
     */
    public ResponseEntity<?> getImage(Integer productId) throws IOException {
        if(productRepository.findById(productId).isPresent()){
            Product product = productRepository.findById(productId).get();
            byte[] d = Files.readAllBytes(new File(FILE_PATH + product.getThumbnail()).toPath());
            MediaType m = product.getThumbnail().split("\\.")[1].equals("jpg") ? MediaType.IMAGE_JPEG : MediaType.IMAGE_PNG;
            return ResponseEntity.status(HttpStatus.OK).contentType(m).body(d)  ;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


    }

}
