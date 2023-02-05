package com.example.web.service.impl;

import com.example.web.model.dto.OfferDTO;
import com.example.web.model.entity.TourOfferEntity;
import com.example.web.repository.TourOfferRepository;
import com.example.web.service.TourOfferService;
import com.example.web.util.ValidatorUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TourOfferServiceImpl implements TourOfferService {
    private final TourOfferRepository tourOfferRepository;
    private final ModelMapper mapper;
    private ValidatorUtil validatorUtil;

    @Override
    public ResponseEntity<Page<OfferDTO>> initialSearchResult(Integer pageNumber, Integer pageSize) {

        Page<OfferDTO> offerDTOS = null;
        try {
            Page<TourOfferEntity> offerEntity = this.tourOfferRepository
                    .findAll_TourOffers_ByDate(PageRequest.of(pageNumber, pageSize));

            offerDTOS = this.validatorUtil.mapEntityPageIntoDtoPage(offerEntity, OfferDTO.class);
        } catch (Exception e) {

            return this.validatorUtil.responseEntity(offerDTOS);
        }

        return this.validatorUtil.responseEntity(offerDTOS);
    }

    @Override
    public ResponseEntity<Page<OfferDTO>> offerSearchAndFilter(Integer pageNumber,
                                                               Integer pageSize,
                                                               String country,
                                                               String city,
                                                               String... sorts
    ) {
        Page<OfferDTO> offers = null;
        try {
            final List<Sort.Order> orders = getOrderList(sorts);//added

            final Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(orders));

            final String criteria = getCriteriaParam(country, city);

            final Page<TourOfferEntity> offerEntities;

            if (criteria == null) {
                //works
                offerEntities = tourOfferRepository.findAll_TourOffers_ByDate(pageable);
                offers = this.validatorUtil.mapEntityPageIntoDtoPage(offerEntities, OfferDTO.class);
            } else {
                offerEntities = tourOfferRepository.findAllByCriteria(criteria, pageable);
                offers = this.validatorUtil.mapEntityPageIntoDtoPage(offerEntities, OfferDTO.class);
            }
        } catch (Exception e) {
            return this.validatorUtil.responseEntity(offers);
        }

        return this.validatorUtil.responseEntity(offers);
    }

    @Override
    public ResponseEntity<OfferDTO> editOffer(Long offerId, Long userId) {

        OfferDTO offerDTO = null;
        try {
            TourOfferEntity entity = this.tourOfferRepository.findByIdAndUserId(offerId, userId).orElse(null);

            if (Objects.isNull(entity)) {
                return this.validatorUtil.responseEntity(this.mapper.map(entity, OfferDTO.class));
            }

            offerDTO = this.mapper.map(entity, OfferDTO.class);

        }catch (Exception e){
            return this.validatorUtil.responseEntity(offerDTO);
        }

        return this.validatorUtil.responseEntity(offerDTO);
    }

    @Override
    public ResponseEntity<OfferDTO> saveOffer(Long offerId, Long userId, OfferDTO offerDTO){

        //TODO
        return null;
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
