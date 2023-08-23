package com.samborskiy.hardwarestore.api.controllers;

import com.samborskiy.hardwarestore.api.service.ShowcaseService;
import com.samborskiy.hardwarestore.store.model.Showcase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShowcaseController {

    ShowcaseService showcaseService;

    @GetMapping("/showcases")
    public ResponseEntity<?> getAllShowcases(){
       return showcaseService.getAllShowcases();
    }
    @PostMapping("/showcase")
    public ResponseEntity<?> createShowcase(@ModelAttribute Showcase showcase, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       return showcaseService.createShowcase(showcase);
    }
    @PatchMapping("/showcase/{id}")
    public ResponseEntity<?> updateShowcase(@ModelAttribute Showcase showcase, @PathVariable("id") UUID id, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return showcaseService.updateShowcase(showcase, id);
    }
    @DeleteMapping("/showcase/{id}")
    public ResponseEntity<?> deleteShowcase(@PathVariable("id") UUID id){
        return showcaseService.deleteShowcase(id);
    }

}

