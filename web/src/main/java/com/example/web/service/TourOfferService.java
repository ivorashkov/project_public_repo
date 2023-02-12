package com.example.web.service;

import com.example.web.model.dto.ImportCreateOfferInfoDTO;
import com.example.web.model.dto.ResponseOfferInfoDTO;
import org.springframework.data.domain.Page;

public interface TourOfferService {

  Page<ResponseOfferInfoDTO> initialSearchResult(Integer pageNumber, Integer pageSize);

  Page<ResponseOfferInfoDTO> searchAndFilterOffers(Integer pageNumber, Integer pageSize, String country,
      String city, String... sorts);


  ResponseOfferInfoDTO editOffer(Long offerId, Long userId);

  void saveOffer(ImportCreateOfferInfoDTO offerDTO);

}
