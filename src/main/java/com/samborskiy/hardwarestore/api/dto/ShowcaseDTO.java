package com.samborskiy.hardwarestore.api.dto;

import com.samborskiy.hardwarestore.store.model.enums.TypeShowcases;

import lombok.AllArgsConstructor;
import lombok.Data;



import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class ShowcaseDTO {
    String name;
    String address;
    TypeShowcases type;
    LocalDateTime createAt;
    LocalDateTime lastUpdateAt;
}
