package com.example.web.controller;

import com.example.web.model.dto.OfferDTO;
import com.example.web.model.entity.TourOfferEntity;
import com.example.web.repository.TourOfferRepository;
import com.example.web.util.ValidatorUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/offer")
public class TourOfferController {
    private final TourOfferRepository tourOfferRepository;
    private final ModelMapper mapper;
    private ValidatorUtil validatorUtil;


    @GetMapping("")
    public ResponseEntity<Page<OfferDTO>> findAllAndSort(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                         @RequestParam(name = "pageSize", defaultValue = "30") Integer size,
                                                         @RequestParam(name = "country", required = false) String country,
                                                         @RequestParam(name = "city", required = false) String city,
                                                         @RequestParam(name = "price", required = false) String price,
                                                         @RequestParam(defaultValue = "date,desc") String... sort

    ) {

        /** http://localhost:8091/offer?sort=column1,direction1&sort=column2,direction2 provides
         * with 2 columns column1,direction1*/


        Page<OfferDTO> offers = null;
        try {
            final List<Order> orders = getOrderList(sort);

            final Pageable pageable = PageRequest.of(page, size, Sort.by(orders));

            final String criteria = getCriteriaParam(country, city, price);

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

    private List<Order> getOrderList(String[] sort) {
        List<Order> orders = new ArrayList<>();

        for (String s : sort) {
            String[] columns = s.split(",");
            String sortCriteria = columns[0];
            String directionCriteria = columns[1];

            orders.add(new Order(getDirectionOfSort(directionCriteria), sortCriteria));
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

    private String getCriteriaParam(String country, String city, String price) {
        if (country != null) {
            return country;
        } else if (city != null) {
            return city;
        } else if (price != null) {
            return price;
        }
        return null;
    }


    //edit = update
    //delete
    //create
    //findBy... ASC
}
