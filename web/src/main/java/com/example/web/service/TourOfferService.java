package com.example.web.service;

import com.example.web.model.dto.ImportCreateOfferInfoDTO;
import com.example.web.model.dto.TourOfferPagingDTO;
import com.example.web.model.dto.TourOfferFullDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface TourOfferService {

  Page<TourOfferPagingDTO> initialSearchResult(Integer pageNumber, Integer pageSize);

  Page<TourOfferPagingDTO> searchAndFilterOffers(Integer pageNumber, Integer pageSize, String country,
      String city, String... sorts);

  TourOfferFullDTO editOffer(Long offerId, Long userId);

  TourOfferFullDTO findById(Long id);

  TourOfferFullDTO createOffer(ImportCreateOfferInfoDTO offerDTO);

  TourOfferFullDTO saveOfferPath(TourOfferFullDTO importedOfferDTO,List<MultipartFile> files);

  boolean deleteOffer(Long userId, Long offerId);
}
