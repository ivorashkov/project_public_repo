package com.example.web.service;

import com.example.web.model.dto.TourOfferFilePathDTO;
import com.example.web.model.dto.TourOfferFullDTO;
import java.nio.file.Path;
import java.util.List;

public interface TourOfferDataService {

  void saveFileUri(TourOfferFullDTO offerDTO, Path initPath);

  List<TourOfferFilePathDTO> findAllOfferDataPaths(Long offerId);

  List<TourOfferFilePathDTO> getOfferPaths(TourOfferFullDTO tourOfferFullDTO);
}
