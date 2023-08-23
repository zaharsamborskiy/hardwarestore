package com.samborskiy.hardwarestore.api.controllers;

import com.samborskiy.hardwarestore.api.service.ShowcaseService;
import com.samborskiy.hardwarestore.store.model.Showcase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShowcaseController {

    ShowcaseService showcaseService;

    @GetMapping("/")
    public List<Showcase> getAllShowcases(){

       return null;
    }
}
