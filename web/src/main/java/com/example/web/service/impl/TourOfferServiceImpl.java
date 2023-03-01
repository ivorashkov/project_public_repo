package com.example.web.service.impl;

import com.example.web.exception.MapEntityPageIntoDtoPageException;
import com.example.web.exception.PageWithOffersNotFoundException;
import com.example.web.exception.TourOfferNotFoundException;
import com.example.web.model.dto.TourOfferCreateDTO;
import com.example.web.model.dto.TourOfferPagingDTO;
import com.example.web.model.dto.TourOfferFullDTO;
import com.example.web.model.entity.TourOfferEntity;
import com.example.web.model.entity.UserEntity;
import com.example.web.repository.TourOfferRepository;
import com.example.web.service.TourOfferService;
import com.example.web.service.UserService;
import com.example.web.util.ValidatorUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class TourOfferServiceImpl implements TourOfferService {

  private final TourOfferRepository tourOfferRepository;
  private final UserService userService;
  private final ValidatorUtil validatorUtil;

  @Override
  public Page<TourOfferPagingDTO> initialSearchResult(Integer pageNumber, Integer pageSize) {

    Page<TourOfferEntity> offerEntity = null;
    try {
      log.info(" [INFO]  TourOfferServiceImpl { initialSearchResult }  {find offers page}");
      offerEntity = this.tourOfferRepository
          .findAll_TourOffers_ByDate(PageRequest.of(pageNumber, pageSize));

    } catch (PageWithOffersNotFoundException e) {
      log.error(" [ERROR] Issue while trying to extract Offers initialSearchResult {}",
          e.getMessage());
    }

    try {
      log.info(" [INFO]  TourOfferServiceImpl { initialSearchResult }  { mapToDTO Page }");

      return this.validatorUtil.mapEntityPageIntoDtoPage(offerEntity,
          TourOfferPagingDTO.class);

    } catch (MapEntityPageIntoDtoPageException e) {
      log.error(" [ERROR] Issue while trying to extract Offers initialSearchResult {}",
          e.getMessage());

      throw e;
    }
  }

  @Override
  public Page<TourOfferPagingDTO> searchAndFilterOffers(
      Integer pageNumber,
      Integer pageSize,
      String country,
      String city,
      String... sorts
  ) {
    log.info(" [INFO] Loading TourOfferServiceImpl {searchAndFilterOffers}");

    Page<TourOfferPagingDTO> offers = null;
    try {
      final List<Sort.Order> orders = getOrderList(sorts);//added

      final Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(orders));

      final String criteria = getCriteriaParam(country, city);

      final Page<TourOfferEntity> offerEntities;

      if (criteria == null) {

        offerEntities = tourOfferRepository.findAll_TourOffers_ByDate(pageable);
        offers = this.validatorUtil.mapEntityPageIntoDtoPage(offerEntities,
            TourOfferPagingDTO.class);
      } else {

        offerEntities = tourOfferRepository.findAllByCriteria(criteria, pageable);
        offers = this.validatorUtil.mapEntityPageIntoDtoPage(offerEntities,
            TourOfferPagingDTO.class);
      }

      return offers;
    } catch (PageWithOffersNotFoundException e) {
      log.error(" [ERROR] Error while trying to extract filtered Offers Page {}", e.getMessage());

      throw e;
    }
  }

  @Override
  public TourOfferFullDTO findByIdAndUserId(Long offerId, Long userId) {
    log.info(" [INFO] TourOfferServiceImpl  { findByIdAndUserId }");

    TourOfferEntity tourEntity = null;
    try {

      tourEntity = this.tourOfferRepository.findByIdAndUserId(offerId, userId)
          .orElseThrow(TourOfferNotFoundException::new);

      var userEntity = this.validatorUtil.getEntityFromDTO
          (this.userService.findUserDTOById(userId), UserEntity.class);

      tourEntity.setUser(userEntity);

      return this.validatorUtil.getDTOFromEntity(tourEntity, TourOfferFullDTO.class);
    } catch (TourOfferNotFoundException e) {
      log.error(" [ERROR] Error TourOfferServiceImpl { findByIdAndUserId } {}", e.getMessage());

      throw e;
    }
  }

  @Override
  public TourOfferFullDTO saveOfferAndPath(TourOfferCreateDTO importedOfferDTO) {
    log.info(" [INFO] TourOfferServiceImpl {saveOfferAndPath}");

    try {

      var userEntity = this.validatorUtil.getEntityFromDTO
          (this.userService.findUserDTOById(importedOfferDTO.getUser().getId()), UserEntity.class);

      var tourOfferEntity = this.validatorUtil.getEntityFromDTO
          (importedOfferDTO, TourOfferEntity.class);

      tourOfferEntity.setUser(userEntity);

      return this.validatorUtil.getDTOFromEntity
          (this.tourOfferRepository.save(tourOfferEntity), TourOfferFullDTO.class);

    } catch (TourOfferNotFoundException e) {
      log.error(" [ERROR] Exception while trying to save Offer {}", e.getMessage());

      throw e;
    }
  }

  @Override
  public boolean deleteOffer(Long userId, Long offerId) {
    log.info(" [INFO] Loading TourOfferServiceImpl { deleteOffer }");

    try {
      TourOfferEntity offer =
          this.tourOfferRepository.findByIdAndUserId(offerId, userId)
              .orElseThrow(TourOfferNotFoundException::new);

      offer.setDeleted(true);

      return this.tourOfferRepository.save(offer).isPresent();
    } catch (TourOfferNotFoundException e) {
      log.error(" [ERROR] Error while loading TourOfferServiceImpl { deleteOffer } {} ",
          e.getMessage());

      throw e;
      //return false
    }
  }

  private List<Sort.Order> getOrderList(String[] sort) {
    List<Sort.Order> orders = new ArrayList<>();

    for (String s : sort) {
      String[] columns = s.split(";");
      String sortCriteria = columns[0];
      String directionCriteria = columns[1];

      Direction directionOfSort = getDirectionOfSort(directionCriteria);
      orders.add(new Sort.Order(directionOfSort, sortCriteria));

    }
    return orders;
  }

  private Sort.Direction getDirectionOfSort(String directionCriteria) {

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
