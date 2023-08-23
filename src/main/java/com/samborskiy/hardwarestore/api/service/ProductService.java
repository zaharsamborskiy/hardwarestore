package com.samborskiy.hardwarestore.api.service;


import com.samborskiy.hardwarestore.store.model.Product;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<?> addProductOnShowcase(Product product);

    ResponseEntity<?> updateProduct(Product product, Long id);
    ResponseEntity<?> deleteProduct(Long id);
}
