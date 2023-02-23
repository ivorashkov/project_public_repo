package com.example.web.service.impl;

import com.example.web.model.dto.TourOfferCreateDTO;
import com.example.web.model.dto.TourOfferDocPathDTO;
import com.example.web.model.dto.TourOfferPagingDTO;
import com.example.web.model.dto.TourOfferFullDTO;
import com.example.web.model.dto.UserDTO;
import com.example.web.model.entity.TourOfferEntity;
import com.example.web.model.entity.UserEntity;
import com.example.web.repository.TourOfferRepository;
import com.example.web.service.FileService;
import com.example.web.service.OfferDataService;
import com.example.web.service.TourOfferService;
import com.example.web.service.UserService;
import com.example.web.util.ValidatorUtil;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class TourOfferServiceImpl implements TourOfferService {

  private final TourOfferRepository tourOfferRepository;
  private final UserService userService;
  private final ModelMapper mapper;
  private ValidatorUtil validatorUtil;


  @Override
  public Page<TourOfferPagingDTO> initialSearchResult(Integer pageNumber, Integer pageSize) {

    Page<TourOfferPagingDTO> offerDTOS = null;
    try {
      Page<TourOfferEntity> offerEntity = this.tourOfferRepository
          .findAll_TourOffers_ByDate(PageRequest.of(pageNumber, pageSize));

      offerDTOS = this.validatorUtil.mapEntityPageIntoDtoPage(offerEntity,
          TourOfferPagingDTO.class);
      return offerDTOS;

    } catch (Exception e) {

      return offerDTOS;
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
    Page<TourOfferPagingDTO> offers = null;
    try {
      final List<Sort.Order> orders = getOrderList(sorts);//added

      final Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(orders));

      final String criteria = getCriteriaParam(country, city);

      final Page<TourOfferEntity> offerEntities;

      if (criteria == null) {
        //works
        offerEntities = tourOfferRepository.findAll_TourOffers_ByDate(pageable);
        offers = this.validatorUtil.mapEntityPageIntoDtoPage(offerEntities,
            TourOfferPagingDTO.class);
      } else {
        offerEntities = tourOfferRepository.findAllByCriteria(criteria, pageable);
        offers = this.validatorUtil.mapEntityPageIntoDtoPage(offerEntities,
            TourOfferPagingDTO.class);
      }
      return offers;

    } catch (Exception e) {
      return offers;
    }
  }

  @Override
  public TourOfferFullDTO getOfferWithPathsDTOs(Long offerId, Long userId, List<TourOfferDocPathDTO> pathDTO) {

    TourOfferFullDTO offerDTO = getFullOfferDTOByUserIdAndOfferId(userId,
        offerId);
    offerDTO.setPaths(pathDTO);

    return offerDTO;

  }

  @Override
  public TourOfferFullDTO getFullOfferDTOByUserIdAndOfferId(Long userId, Long offerId) {

    TourOfferEntity entity = this.tourOfferRepository.findByIdAndUserId(offerId, userId)
        .orElse(null);

    return this.mapper.map(entity, TourOfferFullDTO.class);
  }

  @Override
  public TourOfferFullDTO createOffer(TourOfferCreateDTO offerDTO) {

    var userEntity = this.mapper.map(this.userService.findUserDTOById(offerDTO.getUser().getId()),
        UserEntity.class);

    var tourOfferEntity = this.mapper.map(offerDTO, TourOfferEntity.class);

    tourOfferEntity.setUser(userEntity);

    return this.mapper.map(this.tourOfferRepository.save(tourOfferEntity), TourOfferFullDTO.class);
  }

  @Override
  public TourOfferFullDTO saveOfferPath(TourOfferFullDTO importedOfferDTO,
      List<MultipartFile> files) {

    var tourOfferEntity = this.tourOfferRepository.findById(importedOfferDTO.getId())
        .orElse(null);

    UserDTO userDTO = userService.findUserDTOById(importedOfferDTO.getUser().getId());

    tourOfferEntity.setUser(this.mapper.map(userDTO, UserEntity.class));

    return this.mapper.map(tourOfferEntity, TourOfferFullDTO.class);
  }

  @Override
  public void deleteOffer(Long userId, Long offerId) {
    var offerEntity =
        this.tourOfferRepository.findByIdAndUserId(offerId, userId).orElse(null);

    if (!Objects.isNull(offerEntity)) {
      offerEntity.setDeleted(true);
      this.tourOfferRepository.save(offerEntity);
    }
  }

  @Override
  public TourOfferFullDTO findById(Long id) {
    TourOfferEntity tourOfferEntity = this.tourOfferRepository.findById(id).orElse(null);
    return this.mapper.map(tourOfferEntity, TourOfferFullDTO.class);
  }

  private List<Sort.Order> getOrderList(String[] sort) {
    List<Sort.Order> orders = new ArrayList<>();

    for (String s : sort) {
      String[] columns = s.split(";");
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
