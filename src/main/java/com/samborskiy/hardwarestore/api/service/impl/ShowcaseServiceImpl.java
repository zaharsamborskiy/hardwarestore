package com.samborskiy.hardwarestore.api.service.impl;

import com.samborskiy.hardwarestore.api.dto.ShowcaseDTO;
import com.samborskiy.hardwarestore.api.dto.ShowcaseDTOMapper;
import com.samborskiy.hardwarestore.api.exceptions.BadRequestException;
import com.samborskiy.hardwarestore.api.service.ShowcaseService;
import com.samborskiy.hardwarestore.store.model.Showcase;
import com.samborskiy.hardwarestore.store.repository.ShowcaseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShowcaseServiceImpl implements ShowcaseService {
    ShowcaseRepository showcaseRepository;
    ShowcaseDTOMapper showcaseDTOMapper;


    @Override
    public ResponseEntity<?> createShowcase(Showcase showcase) {
        Showcase showcaseToDatabase = new Showcase();
        showcaseToDatabase.setName(showcase.getName());
        showcaseToDatabase.setAddress(showcase.getAddress());
        showcaseToDatabase.setType(showcase.getType());
        showcaseRepository.save(showcaseToDatabase);
        return ResponseEntity.status(HttpStatus.CREATED).body(showcaseToDatabase);
    }

    @Override
    public List<ShowcaseDTO> getAllShowcases() {
        return showcaseRepository.findAll().stream()
                .map(showcaseDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> updateShowcase(Showcase showcase, UUID id) {
        Showcase showcaseDB = showcaseRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Showcase with id " + id + " not found"));
        showcaseDB.setName(showcase.getName());
        showcaseDB.setAddress(showcase.getAddress());
        showcaseDB.setType(showcase.getType());
        showcaseRepository.save(showcaseDB);
        return ResponseEntity.status(HttpStatus.CREATED).body(showcaseDB);
    }

    @Override
    public ResponseEntity<?> deleteShowcase(UUID id) {
        Optional<Showcase> showcaseFromDatabase = showcaseRepository.findById(id);
        if (showcaseFromDatabase.isPresent())  {
            showcaseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else throw new BadRequestException("Showcase with id " + id + " not found");
    }
}
