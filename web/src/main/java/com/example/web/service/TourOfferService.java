package com.example.web.service;

import com.example.web.model.requestDto.TourOfferCreateRequestDTO;
import com.example.web.model.requestDto.TourOfferEditRequestDTO;
import com.example.web.model.responseDTO.TourOfferPagingResponseDTO;
import com.example.web.model.dto.TourOfferFullDTO;
import org.springframework.data.domain.Page;

public interface TourOfferService {

  Page<TourOfferPagingResponseDTO> initialSearchResult(Integer pageNumber, Integer pageSize);

  Page<TourOfferPagingResponseDTO> searchAndFilterOffers(Integer pageNumber, Integer pageSize, String country,
      String city, String... sorts);

  TourOfferFullDTO findByIdAndUserId(Long offerId, Long userId);

  TourOfferFullDTO saveOfferAndPath(TourOfferCreateRequestDTO importedOfferDTO);

  boolean deleteOffer(Long userId, Long offerId);

  boolean deleteOfferFilePaths(TourOfferFullDTO offerFullDTO);

  TourOfferFullDTO setNewProperties(TourOfferEditRequestDTO editDTO, TourOfferFullDTO fullDTO);
}
