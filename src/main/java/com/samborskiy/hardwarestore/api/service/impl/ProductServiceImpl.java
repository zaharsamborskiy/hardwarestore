package com.samborskiy.hardwarestore.api.service.impl;

import com.samborskiy.hardwarestore.api.service.ProductService;
import com.samborskiy.hardwarestore.store.model.Product;
import com.samborskiy.hardwarestore.store.model.Showcase;
import com.samborskiy.hardwarestore.store.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    @Override
    public ResponseEntity<?> addProductOnShowcase(Product product) {
        Product productToDatabase = new Product();
        productToDatabase.setName(product.getName());
        productToDatabase.setPositionProduct(product.getPositionProduct());
        productToDatabase.setTypeProduct(product.getTypeProduct());
        productToDatabase.setPrice(product.getPrice());
        productToDatabase.setCreateAt(dateTimeFormat(LocalDateTime.now()));
        productToDatabase.setLastUpdateAt(dateTimeFormat(LocalDateTime.now()));
        productToDatabase.setShowcase(product.getShowcase());
        productRepository.save(productToDatabase);
        return ResponseEntity.status(HttpStatus.CREATED).body(productToDatabase);
    }

    @Override
    public ResponseEntity<?> updateProduct(Product product, Long id) {
        Optional<Product> productFromDatabase = productRepository.findById(id);
        productFromDatabase.ifPresent(productDB -> {
            productDB.setName(product.getName());
            productDB.setTypeProduct(product.getTypeProduct());
            productDB.setPositionProduct(product.getPositionProduct());
            productDB.setPrice(product.getPrice());
            productDB.setShowcase(product.getShowcase());
            productDB.setLastUpdateAt(dateTimeFormat(LocalDateTime.now()));
            productRepository.save(productDB);
        });
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<?> deleteProduct(Long id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent())  {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return ResponseEntity.badRequest().build();
    }

    private LocalDateTime dateTimeFormat(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = dateTime.format(formatter);
        return LocalDateTime.parse(formattedDateTime, formatter);
    }


}
