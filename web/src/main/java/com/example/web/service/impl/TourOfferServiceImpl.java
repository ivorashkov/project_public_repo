package com.example.web.service.impl;

import com.example.web.model.dto.OfferDTO;
import com.example.web.repository.TourOfferRepository;
import com.example.web.service.TourOfferService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TourOfferServiceImpl implements TourOfferService {

    private final TourOfferRepository tourOfferRepository;
    private final ModelMapper mapper;

    @Override
    public List<OfferDTO> initialSearchResult(String criteria) {
        List<OfferDTO> offers = this.tourOfferRepository
                .findAll()
                .stream()
                .filter(Optional::isPresent)
                .map(e -> this.mapper.map(e, OfferDTO.class))
                .collect(Collectors.toList());

        return offers;
    }
}
