package com.example.web.service;

import com.example.web.model.dto.TourOfferCreateDTO;
import com.example.web.model.dto.TourOfferImagePathDTO;
import com.example.web.model.dto.TourOfferPagingDTO;
import com.example.web.model.dto.TourOfferFullDTO;
import com.example.web.model.dto.UserDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface TourOfferService {

  Page<TourOfferPagingDTO> initialSearchResult(Integer pageNumber, Integer pageSize);

  Page<TourOfferPagingDTO> searchAndFilterOffers(Integer pageNumber, Integer pageSize, String country,
      String city, String... sorts);

  TourOfferFullDTO getOfferWithPathsDTOs(Long offerId, Long userId, List<TourOfferImagePathDTO> pathDTO);

  TourOfferFullDTO getFullOfferDTOByUserIdAndOfferId(Long userId, Long offerId);

//  TourOfferFullDTO createOffer(TourOfferCreateDTO offerDTO);

  TourOfferFullDTO saveOfferAndPath(TourOfferCreateDTO importedOfferDTO);

  TourOfferFullDTO findById(Long id, UserDTO userDTO);

  void deleteOffer(Long userId, Long offerId);
}
