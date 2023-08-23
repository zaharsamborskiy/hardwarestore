package com.samborskiy.hardwarestore.api.service;

import com.samborskiy.hardwarestore.store.repository.ShowcaseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShowcaseService {
    ShowcaseRepository showcaseRepository;
}
