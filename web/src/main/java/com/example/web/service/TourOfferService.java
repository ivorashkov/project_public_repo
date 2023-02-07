package com.example.web.service;

import com.example.web.model.dto.OfferDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface TourOfferService {

  Page<OfferDTO> initialSearchResult(Integer pageNumber, Integer pageSize);

  Page<OfferDTO> searchAndFilterOffers(Integer pageNumber, Integer pageSize, String country,
      String city,String... sorts);


  OfferDTO editOffer(Long offerId, Long userId);

  OfferDTO saveOffer(Long offerId, Long userId, OfferDTO offerDTO);

}
