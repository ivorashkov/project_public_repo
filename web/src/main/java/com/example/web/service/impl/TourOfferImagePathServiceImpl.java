package com.example.web.service.impl;

import com.example.web.exception.TourOfferNotFoundException;
import com.example.web.model.dto.TourOfferFilePathDTO;
import com.example.web.model.dto.TourOfferFullDTO;
import com.example.web.model.entity.TourOfferFilePathEntity;
import com.example.web.model.entity.TourOfferEntity;
import com.example.web.repository.OfferDataRepository;

import com.example.web.service.TourOfferDataService;
import com.example.web.util.ValidatorUtil;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TourOfferImagePathServiceImpl implements TourOfferDataService {

  private final OfferDataRepository offerDataRepository;
  private final ValidatorUtil validatorUtil;

  @Override
  public void saveFileUri(TourOfferFullDTO offerDTO, Path absoluteDocumentLocation) {
    Optional<TourOfferFilePathEntity> isAlreadyInDB =
        this.offerDataRepository.findByDocumentLocation(absoluteDocumentLocation.toString());

    if (isAlreadyInDB.isEmpty()) {
      var tourOfferEntity =
          this.validatorUtil.getEntityFromDTO(offerDTO, TourOfferEntity.class);

      var dataPathEntity =
          new TourOfferFilePathEntity(absoluteDocumentLocation.toString(), tourOfferEntity);

      this.offerDataRepository.save(dataPathEntity);
    }
  }

  @Override
  public List<TourOfferFilePathDTO> findAllOfferDataPaths(Long offerId) {
    List<TourOfferFilePathEntity> offerDataPathEntities = null;

    try {
      log.info("TourOfferImagePathServiceImpl {findAllOfferDataPaths}");

      offerDataPathEntities =
          this.validatorUtil.getListFromOptionalList(
              this.offerDataRepository.findAllByOfferId(offerId));

    } catch (TourOfferNotFoundException e) {

      log.error("Error while searching for Offer files {}", e.getMessage());
    }

    return this.validatorUtil.getDTOList(offerDataPathEntities, TourOfferFilePathDTO.class);
  }

  @Override
  public List<TourOfferFilePathDTO> getOfferPaths(TourOfferFullDTO tourOfferFullDTO) {
    List<TourOfferFilePathEntity> paths = null;

    try {
      log.info("Loading Offer Files");

      paths = this.validatorUtil.getListFromOptionalList
          (this.offerDataRepository.findAllByOfferId(tourOfferFullDTO.getId()));

    }catch (TourOfferNotFoundException e){
      log.error("TourOfferImagePathServiceImpl {getOfferPaths} {}", e.getMessage());
    }

    return this.validatorUtil.getDTOList(paths, TourOfferFilePathDTO.class);
  }
}
