package com.example.web.service;

import com.example.web.model.dto.ImportCreateOfferInfoDTO;
import com.example.web.model.dto.ResponseOfferInfoDTO;
import com.example.web.model.dto.TourOfferDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface TourOfferService {

  Page<ResponseOfferInfoDTO> initialSearchResult(Integer pageNumber, Integer pageSize);

  Page<ResponseOfferInfoDTO> searchAndFilterOffers(Integer pageNumber, Integer pageSize, String country,
      String city, String... sorts);

  ResponseOfferInfoDTO editOffer(Long offerId, Long userId);

  TourOfferDTO findById(Long id);

  TourOfferDTO findByTitle(String title);

  TourOfferDTO createOffer(ImportCreateOfferInfoDTO offerDTO);

  ResponseOfferInfoDTO saveOfferPath(TourOfferDTO importedOfferDTO,List<MultipartFile> files);

}
