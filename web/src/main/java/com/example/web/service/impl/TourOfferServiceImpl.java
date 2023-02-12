package com.example.web.service.impl;

import com.example.web.model.dto.ImportCreateOfferInfoDTO;
import com.example.web.model.dto.ResponseOfferInfoDTO;
import com.example.web.model.entity.TourOfferEntity;
import com.example.web.model.entity.UserEntity;
import com.example.web.repository.TourOfferRepository;
import com.example.web.repository.UserRepository;
import com.example.web.service.TourOfferService;
import com.example.web.util.ValidatorUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TourOfferServiceImpl implements TourOfferService {

  private final TourOfferRepository tourOfferRepository;
  private final UserRepository userRepository;
  private final ModelMapper mapper;
  private ValidatorUtil validatorUtil;

  @Override
  public Page<ResponseOfferInfoDTO> initialSearchResult(Integer pageNumber, Integer pageSize) {

    Page<ResponseOfferInfoDTO> offerDTOS = null;
    try {
      Page<TourOfferEntity> offerEntity = this.tourOfferRepository
          .findAll_TourOffers_ByDate(PageRequest.of(pageNumber, pageSize));

      offerDTOS = this.validatorUtil.mapEntityPageIntoDtoPage(offerEntity, ResponseOfferInfoDTO.class);
    } catch (Exception e) {

      return offerDTOS;
    }

    return offerDTOS;
  }

  @Override
  public Page<ResponseOfferInfoDTO> searchAndFilterOffers(
      Integer pageNumber,
      Integer pageSize,
      String country,
      String city,
      String... sorts
  ) {
    Page<ResponseOfferInfoDTO> offers = null;
    try {
      final List<Sort.Order> orders = getOrderList(sorts);//added

      final Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(orders));

      final String criteria = getCriteriaParam(country, city);

      final Page<TourOfferEntity> offerEntities;

      if (criteria == null) {
        //works
        offerEntities = tourOfferRepository.findAll_TourOffers_ByDate(pageable);
        offers = this.validatorUtil.mapEntityPageIntoDtoPage(offerEntities, ResponseOfferInfoDTO.class);
      } else {
        offerEntities = tourOfferRepository.findAllByCriteria(criteria, pageable);
        offers = this.validatorUtil.mapEntityPageIntoDtoPage(offerEntities, ResponseOfferInfoDTO.class);
      }
      return offers;

    } catch (Exception e) {
      return offers;
    }
  }

  @Override
  public ResponseOfferInfoDTO editOffer(Long offerId, Long userId) {
//todo to fix this crap code
    ResponseOfferInfoDTO offerDTO = null;
    try {
      TourOfferEntity entity = this.tourOfferRepository.findByIdAndUserId(offerId, userId)
          .orElse(null);

      if (Objects.isNull(entity)) {
        return this.mapper.map(entity, ResponseOfferInfoDTO.class);
      }
      offerDTO = this.mapper.map(entity, ResponseOfferInfoDTO.class);

      return offerDTO;

    } catch (Exception e) {
      return offerDTO;
    }
  }

  @Override
  public Long createOffer(ImportCreateOfferInfoDTO offerDTO) {
    TourOfferEntity tourOfferEntity = this.mapper.map(offerDTO, TourOfferEntity.class);

    //orElse-> exception throw
    UserEntity user = userRepository.findById(offerDTO.getUser().getId()).orElse(null);

    tourOfferEntity.setUser(user);

    return this.tourOfferRepository.findById(this.tourOfferRepository.save(tourOfferEntity));
  }

  private List<Sort.Order> getOrderList(String[] sort) {
    List<Sort.Order> orders = new ArrayList<>();

    for (String s : sort) {
      String[] columns = s.split(",");
      String sortCriteria = columns[0];
      String directionCriteria = columns[1];

      orders.add(new Sort.Order(getDirectionOfSort(directionCriteria), sortCriteria));
    }
    return orders;
  }

  private Sort.Direction getDirectionOfSort(String directionCriteria) {
    //Order order1 = new Order(Sort.Direction.DESC, "published");
    if ("ASC".equalsIgnoreCase(directionCriteria)) {
      return Sort.Direction.ASC;
    } else if ("DESC".equalsIgnoreCase(directionCriteria)) {
      return Sort.Direction.DESC;
    }
    return null;
  }

  private String getCriteriaParam(String country, String city) {
    return this.validatorUtil.getCriteriaParam(country, city);
  }

}
