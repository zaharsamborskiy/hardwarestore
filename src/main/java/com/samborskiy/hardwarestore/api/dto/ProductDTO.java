package com.samborskiy.hardwarestore.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.samborskiy.hardwarestore.store.model.enums.PositionProduct;
import com.samborskiy.hardwarestore.store.model.enums.TypeProduct;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ProductDTO {

    PositionProduct positionProduct;
    String name;
    TypeProduct typeProduct;
    Double price;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    Date createAt;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    Date lastUpdateAt;
}
