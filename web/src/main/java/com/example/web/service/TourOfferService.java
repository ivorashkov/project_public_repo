package com.example.web.service;

import com.example.web.model.requestDto.TourOfferCreateRequestDTO;
import com.example.web.model.requestDto.TourOfferEditRequestDTO;
import com.example.web.model.responseDTO.TourOfferPagingResponseDTO;
import com.example.web.model.dto.TourOfferFullDTO;
import org.springframework.data.domain.Page;

public interface TourOfferService {

  Page<TourOfferPagingResponseDTO> searchAndFilterOffers(
      Integer pageNumber,
      Integer pageSize,
      String location,
      String... sorts
  );

  TourOfferFullDTO findByIdAndUserId(Long offerId, Long userId);

  TourOfferFullDTO saveOfferAndPath(TourOfferCreateRequestDTO importedOfferDTO);

  boolean deleteOffer(Long userId, Long offerId);

  boolean deleteOfferFilePaths(TourOfferFullDTO offerFullDTO);

  TourOfferFullDTO setNewProperties(TourOfferEditRequestDTO editDTO, TourOfferFullDTO fullDTO);
}
