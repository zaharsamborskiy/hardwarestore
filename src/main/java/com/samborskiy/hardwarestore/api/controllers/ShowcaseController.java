package com.samborskiy.hardwarestore.api.controllers;

import com.samborskiy.hardwarestore.api.dto.ShowcaseDTO;
import com.samborskiy.hardwarestore.api.service.ShowcaseService;
import com.samborskiy.hardwarestore.store.model.Showcase;
import com.samborskiy.hardwarestore.store.model.enums.TypeShowcases;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShowcaseController {

    ShowcaseService showcaseService;

    @GetMapping("/showcases")
    public List<ShowcaseDTO> getAllShowcases(){
       return showcaseService.getAllShowcases();
    }

    @GetMapping("/showcases/type")
    public List<ShowcaseDTO> getAllShowcasesByType(@RequestParam(name = "type",required = false)TypeShowcases typeShowcases) {
        return showcaseService.getAllShowcasesByType(typeShowcases);
    }
    @GetMapping("/showcases/address")
    public List<ShowcaseDTO> getAllShowcasesByAddress(@RequestParam(name = "address", required = false) String address){
        return showcaseService.getAllShowcasesByAddress(address);
    }
    @GetMapping("/showcases/createAt")
    public List<ShowcaseDTO> getAllShowcasesByCreateDate(@RequestParam(required = false) String date){
        return showcaseService.getAllShowcasesByCreateAt(date);
    }
    @GetMapping("/showcases/updateAt")
    public List<ShowcaseDTO> getAllShowcasesByLastUpdatedDate(@RequestParam(required = false) String date){
        return showcaseService.getAllShowcasesByLastUpdatedDate(date);
    }

    @PostMapping("/showcase")
    public ResponseEntity<?> createShowcase(@ModelAttribute Showcase showcase){
       return showcaseService.createShowcase(showcase);
    }
    @PatchMapping("/showcase/{id}")
    public ResponseEntity<?> updateShowcase(@ModelAttribute Showcase showcase, @PathVariable("id") UUID id){
        return showcaseService.updateShowcase(showcase, id);
    }
    @DeleteMapping("/showcase/{id}")
    public ResponseEntity<?> deleteShowcase(@PathVariable("id") UUID id){
        return showcaseService.deleteShowcase(id);
    }

}

