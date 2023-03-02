package com.example.web.service;

import com.example.web.model.dto.TourOfferFilePathDTO;
import com.example.web.model.dto.TourOfferFullDTO;
import com.example.web.model.requestDto.TourOfferDeleteDTO;
import java.nio.file.Path;
import java.util.List;

public interface TourOfferFilePathService {

  void saveFileUri(TourOfferFullDTO offerDTO, Path initPath);

  List<TourOfferFilePathDTO> findAllOfferDataPaths(Long offerId);

  List<TourOfferFilePathDTO> getOfferPaths(TourOfferFullDTO tourOfferFullDTO);

  boolean deleteOfferFilePaths(TourOfferDeleteDTO tourOfferDeleteDTO);
}
