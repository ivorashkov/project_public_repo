package com.example.web.service;

import com.example.web.model.dto.OfferDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TourOfferService {

    Page<OfferDTO> initialSearchResult(String criteria);
    Page<OfferDTO> offerSearchAndFilter(String criteria, String[] sorts);
}
