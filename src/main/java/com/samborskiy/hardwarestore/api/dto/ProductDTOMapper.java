package com.samborskiy.hardwarestore.api.dto;

import com.samborskiy.hardwarestore.store.model.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductDTOMapper implements Function<Product, ProductDTO> {
    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getPositionProduct(),
                product.getName(),
                product.getTypeProduct(),
                product.getPrice(),
                product.getCreateAt(),
                product.getLastUpdateAt());
    }
}
