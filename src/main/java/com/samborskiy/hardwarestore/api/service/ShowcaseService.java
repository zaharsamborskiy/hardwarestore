package com.samborskiy.hardwarestore.api.service;

import com.samborskiy.hardwarestore.api.dto.ShowcaseDTO;
import com.samborskiy.hardwarestore.store.model.Showcase;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;


public interface ShowcaseService {
    ResponseEntity<?> createShowcase(Showcase showcase);
    List<ShowcaseDTO> getAllShowcases();
    ResponseEntity<?> updateShowcase(Showcase showcase, UUID id);
    ResponseEntity<?> deleteShowcase(UUID id);
}
