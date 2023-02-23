package com.example.web.service.impl;

import com.example.web.model.dto.TourOfferDocPathDTO;
import com.example.web.model.dto.TourOfferFullDTO;
import com.example.web.model.entity.OfferDataPathEntity;
import com.example.web.model.entity.TourOfferEntity;
import com.example.web.repository.OfferDataRepository;

import com.example.web.service.OfferDataService;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class OfferDataServiceImpl implements OfferDataService {

  private final ModelMapper mapper;
  private final OfferDataRepository offerDataRepository;

  @Override
  public void saveFileUri(TourOfferFullDTO offerDTO, Path initPath) {

    var tourOfferEntity = this.mapper.map(offerDTO,
        TourOfferEntity.class);

    var dataPathEntity = new OfferDataPathEntity(initPath.toString(),tourOfferEntity);

    this.offerDataRepository.save(dataPathEntity);
  }

  @Override
  public void saveAll(List<TourOfferEntity> offers, Path initPath) {
    //todo check if its working
    offers
        .stream()
        .map(e -> this.mapper.map(e, TourOfferFullDTO.class))
        .forEach(e -> saveFileUri(e, initPath));
  }

  @Override
  public List<TourOfferDocPathDTO> findAllOfferDataPaths(Long offerId) {
    List<OfferDataPathEntity> offerDataPathEntities =
        this.offerDataRepository.findAllByOfferId(offerId).orElse(null);

    return offerDataPathEntities.stream()
        .filter(Objects::nonNull)
        .map(e -> this.mapper.map(e, TourOfferDocPathDTO.class))
        .collect(Collectors.toList());
  }
}
