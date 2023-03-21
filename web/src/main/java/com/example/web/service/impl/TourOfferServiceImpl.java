package com.example.web.service.impl;

import com.example.web.exception.PageWithOffersNotFoundException;
import com.example.web.exception.TourOfferNotFoundException;
import com.example.web.model.requestDto.TourOfferCreateRequestDTO;
import com.example.web.model.requestDto.TourOfferEditRequestDTO;
import com.example.web.model.responseDTO.TourOfferPagingResponseDTO;
import com.example.web.model.dto.TourOfferFullDTO;
import com.example.web.model.entity.TourOfferEntity;
import com.example.web.model.entity.UserEntity;
import com.example.web.model.requestDto.TourOfferDeleteRequestDTO;
import com.example.web.repository.TourOfferRepository;
import com.example.web.service.TourOfferFilePathService;
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

  private final TourOfferFilePathService tourOfferFilePathService;
  private final TourOfferRepository tourOfferRepository;
  private final UserService userService;
  private final ValidatorUtil validatorUtil;

  @Override
  public Page<TourOfferPagingResponseDTO> searchAndFilterOffers(
      Integer pageNumber,
      Integer pageSize,
      String location,
      String... sorts
  ) {
    log.info(" [INFO] Loading TourOfferServiceImpl {searchAndFilterOffers}");

    Page<TourOfferPagingResponseDTO> offers = null;
    try {
      final List<Sort.Order> orders = getOrderList(sorts);//added

      final Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(orders));

      final String criteria = getCriteriaParam(location);

      final Page<TourOfferEntity> offerEntities;

      if (criteria == null) {

        offerEntities = tourOfferRepository.findAll_TourOffers_ByDate(pageable);
        offers = this.validatorUtil.mapEntityPageIntoDtoPage(offerEntities,
            TourOfferPagingResponseDTO.class);
      } else {

        offerEntities = tourOfferRepository.findAllByCriteria(criteria, pageable);
        offers = this.validatorUtil.mapEntityPageIntoDtoPage(offerEntities,
            TourOfferPagingResponseDTO.class);
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
  public TourOfferFullDTO saveOfferAndPath(TourOfferCreateRequestDTO importedOfferDTO) {
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

      TourOfferDeleteRequestDTO dto = this.validatorUtil.getDTOFromEntity(offer, TourOfferDeleteRequestDTO.class);

      if (this.tourOfferFilePathService.deleteOfferFilePaths(dto)) {
        offer.setDeleted(true);
        return this.tourOfferRepository.save(offer).isPresent();
      }

    } catch (TourOfferNotFoundException e) {
      log.error(" [ERROR] Error while loading TourOfferServiceImpl { deleteOffer } {} ",
          e.getMessage());

      throw e;
    }

    return false;
  }

  @Override
  public boolean deleteOfferFilePaths(TourOfferFullDTO offerFullDTO) {
    log.info("[INFO] Loading TourOfferServiceImpl {deleteOfferFilePaths}");
    try {
      this.tourOfferFilePathService.deleteOfferFilePaths
          (this.validatorUtil.getDTOFromEntity(offerFullDTO, TourOfferDeleteRequestDTO.class));

      return true;
    } catch (Exception e) {
      log.error("[ERROR] while loading TourOfferServiceImpl {deleteOfferFilePaths}");
    }
    return false;
  }

  @Override
  public TourOfferFullDTO setNewProperties(TourOfferEditRequestDTO editDTO, TourOfferFullDTO fullDTO){
    fullDTO.setDiscount(editDTO.getDiscount());
    fullDTO.setTitle(editDTO.getTitle());
    fullDTO.setCountry(editDTO.getCountry());
    fullDTO.setCity(editDTO.getCity());
    fullDTO.setDuration(editDTO.getDuration());
    fullDTO.setStars(editDTO.getStars());
    fullDTO.setPrice(editDTO.getPrice());
    fullDTO.setDescription(editDTO.getDescription());
    fullDTO.setDiscount(editDTO.getDiscount());
    fullDTO.setTransportType(editDTO.getTransportType());
    return fullDTO;
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

  private String getCriteriaParam(String location) {
    return this.validatorUtil.getCriteriaParam(location);
  }
}
