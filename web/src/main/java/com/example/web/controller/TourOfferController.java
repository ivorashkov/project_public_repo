package com.example.web.controller;

import com.example.web.model.dto.OfferDTO;
import com.example.web.repository.TourOfferRepository;
import com.example.web.util.ValidatorUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/offer")
public class TourOfferController {
    private final TourOfferRepository tourOfferRepository;
    private final ModelMapper mapper;
    private ValidatorUtil validatorUtil;


    @GetMapping("")
    public ResponseEntity<List<OfferDTO>> findAllAndSort(){

        List<OfferDTO> allOffers =
                this.tourOfferRepository
                        .findAll()
                        .stream()
                        .map(e -> this.mapper.map(e, OfferDTO.class))
                        .collect(Collectors.toList());

        return this.validatorUtil.responseEntity(allOffers);
    }


    //edit = update
    //delete
    //create
    //findBy... ASC
}
