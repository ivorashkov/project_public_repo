package com.example.web.service.impl;

import com.example.web.model.dto.OfferDTO;
import com.example.web.repository.TourOfferRepository;
import com.example.web.service.TourOfferService;
import com.example.web.util.ValidatorUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
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
    private ValidatorUtil validatorUtil;

    @Override
    public Page<OfferDTO> initialSearchResult(String criteria) {
//        List<OfferDTO> offers = this.tourOfferRepository
//                .findAll()
//                .stream()
//                .map(e -> this.mapper.map(e, OfferDTO.class))
//                .collect(Collectors.toList());

        return null;//offers;
    }

    @Override
    public Page<OfferDTO> offerSearchAndFilter(String criteria, String[] sorts) {
        return null;
    }


}
