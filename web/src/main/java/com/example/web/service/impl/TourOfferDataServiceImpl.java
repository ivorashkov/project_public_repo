package com.example.web.service.impl;

import com.example.web.model.dto.TourOfferImagePathDTO;
import com.example.web.model.dto.TourOfferFullDTO;
import com.example.web.model.entity.TourOfferImagePathEntity;
import com.example.web.model.entity.TourOfferEntity;
import com.example.web.repository.OfferDataRepository;

import com.example.web.service.TourOfferDataService;
import com.example.web.util.ValidatorUtil;
import java.nio.file.Path;
import java.util.List;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class TourOfferDataServiceImpl implements TourOfferDataService {

  private final ValidatorUtil validatorUtil;
  private final ModelMapper mapper;
  private final OfferDataRepository offerDataRepository;

  @Override
  public void saveFileUri(TourOfferFullDTO offerDTO, Path initPath) {

    var tourOfferEntity = this.mapper.map(offerDTO,
        TourOfferEntity.class);

    var dataPathEntity = new TourOfferImagePathEntity(initPath.toString(), tourOfferEntity);

    this.offerDataRepository.save(dataPathEntity);
  }

  @Override
  public void saveAll(List<TourOfferEntity> offers, Path initPath) {
    offers
        .stream()
        .map(e -> this.mapper.map(e, TourOfferFullDTO.class))
        .forEach(e -> saveFileUri(e, initPath));
  }

  @Override
  public List<TourOfferImagePathDTO> findAllOfferDataPaths(Long offerId) {

    List<TourOfferImagePathEntity> offerDataPathEntities = this.validatorUtil.getListFromOptionalList
        (
            this.offerDataRepository.findAllByOfferId(offerId)
        );

    return this.validatorUtil.getDTOList(offerDataPathEntities, TourOfferImagePathDTO.class);
  }

  @Override
  public List<TourOfferImagePathDTO> getOfferPaths(TourOfferFullDTO tourOfferFullDTO) {

    List<TourOfferImagePathEntity> paths = this.validatorUtil.getListFromOptionalList
        (
            this.offerDataRepository.findAllByOfferId(tourOfferFullDTO.getId())
        );

    return this.validatorUtil.getDTOList(paths, TourOfferImagePathDTO.class);
  }
}
