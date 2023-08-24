package com.samborskiy.hardwarestore.api.dto;

import com.samborskiy.hardwarestore.store.model.Showcase;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class ShowcaseDTOMapper implements Function<Showcase, ShowcaseDTO> {
    @Override
    public ShowcaseDTO apply(Showcase showcase) {
        return new ShowcaseDTO(showcase.getName(),
                showcase.getAddress(),
                showcase.getType(),
                showcase.getCreateAt(),
                showcase.getLastUpdateAt());
    }
}
