package com.samborskiy.hardwarestore.api.service.impl;

import com.samborskiy.hardwarestore.api.dto.ShowcaseDTO;
import com.samborskiy.hardwarestore.api.dto.ShowcaseDTOMapper;
import com.samborskiy.hardwarestore.api.exceptions.BadRequestException;
import com.samborskiy.hardwarestore.api.service.ShowcaseService;
import com.samborskiy.hardwarestore.store.model.Showcase;
import com.samborskiy.hardwarestore.store.model.enums.TypeShowcases;
import com.samborskiy.hardwarestore.store.repository.ProductRepository;
import com.samborskiy.hardwarestore.store.repository.ShowcaseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShowcaseServiceImpl implements ShowcaseService {
    ShowcaseRepository showcaseRepository;
    ShowcaseDTOMapper showcaseDTOMapper;
    ProductRepository productRepository;

    private Showcase saveShowcase(Showcase showcaseDatabase, Showcase showcaseFromApi){
        showcaseDatabase.setName(showcaseFromApi.getName());
        showcaseDatabase.setAddress(showcaseFromApi.getAddress());
        showcaseDatabase.setType(showcaseFromApi.getType());
        return showcaseDatabase;
    }

    @Override
    public ResponseEntity<?> createShowcase(Showcase showcase) {
        Showcase showcaseToDatabase = saveShowcase(new Showcase(), showcase);
        showcaseRepository.save(showcaseToDatabase);
        return ResponseEntity.status(HttpStatus.CREATED).body(showcaseToDatabase);
    }

    @Override
    public List<ShowcaseDTO> getAllShowcases() {
        return showcaseRepository.findAll()
                .stream()
                .map(showcaseDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> updateShowcase(Showcase showcase, UUID id) {
        Showcase showcaseDB = showcaseRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Showcase with id " + id + " not found"));
        Showcase saveShowcase = saveShowcase(showcaseDB, showcase);
        showcaseRepository.save(saveShowcase);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveShowcase);
    }

    @Override
    public ResponseEntity<?> deleteShowcase(UUID id) {
        Showcase showcaseFromDatabase = showcaseRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Showcase with id " + id + " not found"));
        productRepository.deleteAll(productRepository.findAllByShowcase(showcaseFromDatabase));
        showcaseRepository.delete(showcaseFromDatabase);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public List<ShowcaseDTO> getAllShowcasesByType(TypeShowcases typeShowcases) {
        return showcaseRepository.findAll()
                .stream()
                .filter(showcase -> showcase.getType().equals(typeShowcases))
                .map(showcaseDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowcaseDTO> getAllShowcasesByAddress(String address) {
        return showcaseRepository.findAll()
                .stream()
                .filter(showcase -> showcase.getAddress().equals(address))
                .map(showcaseDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowcaseDTO> getAllShowcasesByCreateAt(String createAt) {
        return showcaseRepository.findAll()
                .stream()
                .filter(showcase -> {
                    if (createAt == null) throw new BadRequestException("date can`t be a null");
                    else return showcase.getCreateAt().isEqual(converter(createAt));
                })
                .map(showcaseDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowcaseDTO> getAllShowcasesByLastUpdatedDate(String updateAt) {
        return showcaseRepository.findAll()
                .stream()
                .filter(showcase -> {
                    if (updateAt == null) throw new BadRequestException("date can`t be a null");
                    else return showcase.getLastUpdateAt().isEqual(converter(updateAt));
                })
                .map(showcaseDTOMapper)
                .collect(Collectors.toList());
    }
    private LocalDate converter(String date){
        return LocalDate.parse(date);
    }
}
