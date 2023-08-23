package com.samborskiy.hardwarestore.api.service;

import com.samborskiy.hardwarestore.store.model.Showcase;
import org.springframework.http.ResponseEntity;

import java.util.UUID;


public interface ShowcaseService {
    ResponseEntity<?> createShowcase(Showcase showcase);
    ResponseEntity<?> getAllShowcases();
    ResponseEntity<?> updateShowcase(Showcase showcase, UUID id);
    ResponseEntity<?> deleteShowcase(UUID id);
}
