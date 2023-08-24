package com.samborskiy.hardwarestore.api.service;


import com.samborskiy.hardwarestore.api.dto.ProductDTO;
import com.samborskiy.hardwarestore.store.model.Product;
import com.samborskiy.hardwarestore.store.model.Showcase;
import com.samborskiy.hardwarestore.store.model.enums.TypeProduct;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ProductService {
    ResponseEntity<?> addProductOnShowcase(Product product);

    ResponseEntity<?> updateProduct(Product product, Long id);
    ResponseEntity<?> deleteProduct(Long id);
    List<ProductDTO> getAllProductsOnShowcase(Showcase showcase);

    List<ProductDTO> getAllProductsByType(TypeProduct typeProduct);

    List<ProductDTO> getAllProductsByGreaterPrice(Double price);

    List<ProductDTO> getAllProductsByLessPrice(Double price);

    List<ProductDTO> getAllProductsByFilterPrice(Double less, Double greater);
}
