package com.example.web.service;

import com.example.web.model.dto.TourOfferImagePathDTO;
import com.example.web.model.dto.TourOfferFullDTO;
import com.example.web.model.entity.TourOfferEntity;
import java.nio.file.Path;
import java.util.List;

public interface OfferDataService {

//  void saveFileUri(TourOfferEntity offer, Path initPath);

//  void saveFileUri(Long offerId, Path initPath);

  void saveFileUri(TourOfferFullDTO offerDTO, Path initPath);

  void saveAll(List<TourOfferEntity> offers, Path initPath);

  List<TourOfferImagePathDTO> findAllOfferDataPaths(Long offerId);
}
