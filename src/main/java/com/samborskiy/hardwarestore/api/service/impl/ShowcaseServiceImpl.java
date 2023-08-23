package com.samborskiy.hardwarestore.api.service.impl;

import com.samborskiy.hardwarestore.api.dto.ShowcaseDTO;
import com.samborskiy.hardwarestore.api.service.ShowcaseService;
import com.samborskiy.hardwarestore.store.model.Showcase;
import com.samborskiy.hardwarestore.store.repository.ShowcaseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShowcaseServiceImpl implements ShowcaseService {
    ShowcaseRepository showcaseRepository;


    @Override
    public ResponseEntity<?> createShowcase(Showcase showcase) {
        Showcase showcaseToDatabase = new Showcase();
        showcaseToDatabase.setName(showcase.getName());
        showcaseToDatabase.setAddress(showcase.getAddress());
        showcaseToDatabase.setType(showcase.getType());
        showcaseToDatabase.setCreateAt(dateTimeFormat(LocalDateTime.now()));
        showcaseToDatabase.setLastUpdateAt(dateTimeFormat(LocalDateTime.now()));
        showcaseRepository.save(showcaseToDatabase);
        return ResponseEntity.status(HttpStatus.CREATED).body(showcaseToDatabase);
    }

    @Override
    public ResponseEntity<?> getAllShowcases() {
        List<ShowcaseDTO> showcases = showcaseRepository.findAll().stream()
                .map(showcase -> new ShowcaseDTO(showcase.getName(),
                        showcase.getAddress(),
                        showcase.getType(),
                        showcase.getCreateAt(),
                        showcase.getLastUpdateAt()))
                .collect(Collectors.toList());
       return ResponseEntity.ok(showcases);
    }

    @Override
    public ResponseEntity<?> updateShowcase(Showcase showcase, UUID id) {
        Optional<Showcase> showcaseFromDatabase = showcaseRepository.findById(id);
        showcaseFromDatabase.ifPresent(showcaseDB -> {
            showcaseDB.setName(showcase.getName());
            showcaseDB.setAddress(showcase.getAddress());
            showcaseDB.setType(showcase.getType());
            showcaseDB.setLastUpdateAt(dateTimeFormat(LocalDateTime.now()));
            showcaseRepository.save(showcaseDB);
        });
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> deleteShowcase(UUID id) {
        Optional<Showcase> showcaseFromDatabase = showcaseRepository.findById(id);
        if (showcaseFromDatabase.isPresent())  {
            showcaseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return ResponseEntity.badRequest().build();
    }

    private LocalDateTime dateTimeFormat(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = dateTime.format(formatter);
        return LocalDateTime.parse(formattedDateTime, formatter);
    }
}
