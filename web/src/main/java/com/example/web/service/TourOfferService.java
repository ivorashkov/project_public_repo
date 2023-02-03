package com.example.web.service;

import com.example.web.model.dto.OfferDTO;

import java.util.List;

public interface TourOfferService {

    List<OfferDTO> initialSearchResult(String criteria);
}
