package com.example.web.service;

import com.example.web.model.dto.OfferDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface TourOfferService {

    ResponseEntity<Page<OfferDTO>> initialSearchResult(Integer pageNumber, Integer pageSize);

    ResponseEntity<Page<OfferDTO>> searchAndFilterOffers(Integer pageNumber,
                                                         Integer pageSize,
                                                         String country,
                                                         String city,
                                                         String... sorts);


    ResponseEntity<OfferDTO> editOffer(Long offerId, Long userId);

    ResponseEntity<OfferDTO> saveOffer(Long offerId, Long userId, OfferDTO offerDTO);

}
