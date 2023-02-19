package com.example.web.service.impl;

import com.example.web.model.dto.OfferDataPathDTO;
import com.example.web.model.dto.TourOfferFullDTO;
import com.example.web.model.entity.OfferDataPathEntity;
import com.example.web.model.entity.TourOfferEntity;
import com.example.web.repository.OfferDataRepository;

import com.example.web.repository.TourOfferRepository;
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
  private final TourOfferRepository tourOfferRepository;

  @Override
  public void saveFileUri(Long offerId, Path initPath) {

    OfferDataPathEntity offerData =
        new OfferDataPathEntity(
            initPath.toString(),
            this.tourOfferRepository.findById(offerId).orElse(null)
        );

    this.offerDataRepository.save(offerData);
  }

  @Override
  public void saveAll(List<TourOfferEntity> offers, Path initPath) {
    offers.forEach(o -> saveFileUri(o.getId(), initPath));
  }

  @Override
  public List<OfferDataPathDTO> findAllOfferDataPaths(TourOfferFullDTO offer) {
    List<OfferDataPathEntity> offerDataPathEntities =
        this.offerDataRepository.findAllByOfferId(offer.getId()).orElse(null);

    return offerDataPathEntities.stream()
        .filter(Objects::nonNull)
        .map(e -> this.mapper.map(e, OfferDataPathDTO.class))
        .collect(Collectors.toList());
  }
}
