package com.samborskiy.hardwarestore.api.service.impl;

import com.samborskiy.hardwarestore.api.dto.ProductDTO;
import com.samborskiy.hardwarestore.api.dto.ProductDTOMapper;
import com.samborskiy.hardwarestore.api.exceptions.BadRequestException;
import com.samborskiy.hardwarestore.api.service.ProductService;
import com.samborskiy.hardwarestore.store.model.Product;
import com.samborskiy.hardwarestore.store.model.Showcase;
import com.samborskiy.hardwarestore.store.model.enums.TypeProduct;
import com.samborskiy.hardwarestore.store.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    ProductDTOMapper productDTOMapper;

    private Product saveProduct(Product productDatabase, Product productFromApi){
        productDatabase.setName(productFromApi.getName());
        productDatabase.setTypeProduct(productFromApi.getTypeProduct());
        productDatabase.setPositionProduct(productFromApi.getPositionProduct());
        productDatabase.setPrice(productFromApi.getPrice());
        productDatabase.setShowcase(productFromApi.getShowcase());
        return productDatabase;
    }
    @Override
    public ResponseEntity<?> addProductOnShowcase(Product product) {
        Product productToDatabase = saveProduct(new Product(), product);
        productRepository.save(productToDatabase);
        return ResponseEntity.status(HttpStatus.CREATED).body(productToDatabase);
    }

    @Override
    public ResponseEntity<?> updateProduct(Product product, Long id) {
        Product productFromDatabase = productRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Product with id " + id + " not found"));
        Product toSave = saveProduct(productFromDatabase, product);
        productRepository.save(toSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(toSave);
    }
    @Override
    public ResponseEntity<?> deleteProduct(Long id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent())  {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else throw new BadRequestException("Product with id " + id + " not found");
    }

    @Override
    public List<ProductDTO> getAllProductsOnShowcase(Showcase showcase) {
        return productRepository.findAllByShowcase(showcase)
                .stream().map(productDTOMapper).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllProductsByType(TypeProduct typeProduct) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getTypeProduct().equals(typeProduct))
                .map(productDTOMapper).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllProductsByGreaterPrice(Double price) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getPrice() <= price)
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllProductsByLessPrice(Double price) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getPrice() >= price)
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllProductsByFilterPrice(Double less, Double greater) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getPrice() >= less
                        && product.getPrice() <= greater)
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }
}
