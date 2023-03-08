package com.example.web.service;

import com.example.web.model.requestDto.TourOfferCreateDTO;
import com.example.web.model.requestDto.TourOfferEditDTO;
import com.example.web.model.responseDTO.TourOfferPagingDTO;
import com.example.web.model.dto.TourOfferFullDTO;
import org.springframework.data.domain.Page;

public interface TourOfferService {

  Page<TourOfferPagingDTO> initialSearchResult(Integer pageNumber, Integer pageSize);

  Page<TourOfferPagingDTO> searchAndFilterOffers(Integer pageNumber, Integer pageSize, String country,
      String city, String... sorts);

  TourOfferFullDTO findByIdAndUserId(Long offerId, Long userId);

  TourOfferFullDTO saveOfferAndPath(TourOfferCreateDTO importedOfferDTO);

  boolean deleteOffer(Long userId, Long offerId);

  boolean deleteOfferFilePaths(TourOfferFullDTO offerFullDTO);

  TourOfferFullDTO setNewProperties(TourOfferEditDTO editDTO, TourOfferFullDTO fullDTO);
}
