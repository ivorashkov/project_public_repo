package com.example.web.service;

import com.example.web.model.dto.TourOfferCreateDTO;
import com.example.web.model.dto.TourOfferPagingDTO;
import com.example.web.model.dto.TourOfferFullDTO;
import com.example.web.model.dto.UserDTO;
import org.springframework.data.domain.Page;

public interface TourOfferService {

  Page<TourOfferPagingDTO> initialSearchResult(Integer pageNumber, Integer pageSize);

  Page<TourOfferPagingDTO> searchAndFilterOffers(Integer pageNumber, Integer pageSize, String country,
      String city, String... sorts);

  TourOfferFullDTO findByIdAndUserId(Long offerId, Long userId);

  TourOfferFullDTO saveOfferAndPath(TourOfferCreateDTO importedOfferDTO);

  void deleteOffer(Long userId, Long offerId);
}
