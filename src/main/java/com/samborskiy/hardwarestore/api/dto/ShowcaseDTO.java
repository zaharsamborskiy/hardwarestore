package com.samborskiy.hardwarestore.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.samborskiy.hardwarestore.store.model.enums.TypeShowcases;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.Date;


@Data
@AllArgsConstructor
public class ShowcaseDTO {
    String name;
    String address;
    TypeShowcases type;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    Date createAt;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    Date lastUpdateAt;
}
