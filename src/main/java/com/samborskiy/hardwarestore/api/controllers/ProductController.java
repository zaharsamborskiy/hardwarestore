package com.samborskiy.hardwarestore.api.controllers;

import com.samborskiy.hardwarestore.api.service.ProductService;
import com.samborskiy.hardwarestore.store.model.Product;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;




@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<?> addProductOnShowcase(@ModelAttribute Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return productService.addProductOnShowcase(product);
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@ModelAttribute Product product, BindingResult bindingResult, @PathVariable("id") Long id){
        if (bindingResult.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return productService.updateProduct(product, id);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }


}
