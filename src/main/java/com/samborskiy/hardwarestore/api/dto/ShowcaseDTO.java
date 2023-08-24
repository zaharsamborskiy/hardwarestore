package com.samborskiy.hardwarestore.api.dto;

import com.samborskiy.hardwarestore.store.model.enums.TypeShowcases;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;


@Data
@AllArgsConstructor
public class ShowcaseDTO {
    String name;
    String address;
    TypeShowcases type;
    LocalDate createAt;
    LocalDate lastUpdateAt;
}
