package com.samborskiy.hardwarestore.api.controllers;

import com.samborskiy.hardwarestore.api.dto.ProductDTO;
import com.samborskiy.hardwarestore.api.service.ProductService;
import com.samborskiy.hardwarestore.store.model.Product;
import com.samborskiy.hardwarestore.store.model.Showcase;
import com.samborskiy.hardwarestore.store.model.enums.TypeProduct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;

    @GetMapping("/products/{showcase_id}")
    public List<ProductDTO> getAllProductsOnShowcase(@PathVariable("showcase_id") Showcase showcase){
        return productService.getAllProductsOnShowcase(showcase);
    }

    @GetMapping("/product/type")
    public List<ProductDTO> getAllProductsByType(@RequestParam(name = "type", required = false) TypeProduct typeProduct) {
        return productService.getAllProductsByType(typeProduct);
    }
    @GetMapping("/product/greaterPrice")
    public List<ProductDTO> getAllProductsByGreaterPrice(@RequestParam(required = false) Double price) {
        return productService.getAllProductsByGreaterPrice(price);
    }
    @GetMapping("/product/lessPrice")
    public List<ProductDTO> getAllProductsByLessPrice(@RequestParam(required = false) Double price) {
        return productService.getAllProductsByLessPrice(price);
    }
    @GetMapping("/product/price")
    public List<ProductDTO> getAllProductsByPrice(@RequestParam(name = "lessPrice", required = false) Double less,
                                                  @RequestParam(name = "greaterPrice", required = false) Double greater) {
        return productService.getAllProductsByFilterPrice(less, greater);
    }


    @PostMapping("/product")
    public ResponseEntity<?> addProductOnShowcase(@ModelAttribute Product product) {
        return productService.addProductOnShowcase(product);
    }
    @PatchMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@ModelAttribute Product product, @PathVariable("id") Long id){
        return productService.updateProduct(product, id);
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }

}
