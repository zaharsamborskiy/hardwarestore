package com.samborskiy.hardwarestore.api.dto;

import com.samborskiy.hardwarestore.store.model.enums.PositionProduct;
import com.samborskiy.hardwarestore.store.model.enums.TypeProduct;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ProductDTO {

    PositionProduct positionProduct;
    String name;
    TypeProduct typeProduct;
    Double price;
    LocalDate createAt;
    LocalDate lastUpdateAt;
}
