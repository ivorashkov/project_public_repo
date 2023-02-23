package com.example.web.service;

import com.example.web.model.dto.TourOfferCreateDTO;
import com.example.web.model.dto.TourOfferDocPathDTO;
import com.example.web.model.dto.TourOfferPagingDTO;
import com.example.web.model.dto.TourOfferFullDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface TourOfferService {

  Page<TourOfferPagingDTO> initialSearchResult(Integer pageNumber, Integer pageSize);

  Page<TourOfferPagingDTO> searchAndFilterOffers(Integer pageNumber, Integer pageSize, String country,
      String city, String... sorts);

  TourOfferFullDTO findById(Long id);

  TourOfferFullDTO getOfferWithPathsDTOs(Long offerId, Long userId, List<TourOfferDocPathDTO> pathDTO);

  TourOfferFullDTO getFullOfferDTOByUserIdAndOfferId(Long userId, Long offerId);

  TourOfferFullDTO createOffer(TourOfferCreateDTO offerDTO);

  TourOfferFullDTO saveOfferPath(TourOfferFullDTO importedOfferDTO,List<MultipartFile> files);

  void deleteOffer(Long userId, Long offerId);
}
