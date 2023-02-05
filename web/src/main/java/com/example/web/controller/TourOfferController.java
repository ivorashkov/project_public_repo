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

        //we can create new Sort object with List of Order objects:
        //List<Order> orders = new ArrayList<>();
        //Order order1 = new Order(Sort.Direction.DESC, "published");
        //orders.add(order1); etc..
        //List<Entity> entities = entityRepository.findAll(Sort.by(orders));


        /** http://localhost:8091/offer?sort=column1,direction1&sort=column2,direction2 provides
         * with 2 columns column1,direction1*/
        Page<OfferDTO> offers = null;
        try {
            List<Order> orders = new ArrayList<>();

            for (String s : sort) {
                String[] columns = s.split(",");
                String sortCriteria = columns[0];
                String directionCriteria = columns[1];

             //   orders.add(new Order(getDirectionOfSort(directionCriteria),"\"" +  sortCriteria + "\""));
                orders.add(new Order(getDirectionOfSort(directionCriteria),sortCriteria));
            }

            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));

            /**трябва ли да го мигрирам към дто или тук или в репото? или към Slice<DTO>*/
            Slice<Optional<OfferDTO>> pageOffers;
            if (getCriteriaParam(country, city, price) == null){
                //todo защо не работи с  Pageable pageable = PageRequest.of(page, size, Sort.by(orders)); когато имаме лист с ордъри?
                Page<TourOfferEntity> offerEntities= tourOfferRepository.findAll_TourOffers_ByDate(pageable);

               offers = this.validatorUtil.mapEntityPageIntoDtoPage(offerEntities, OfferDTO.class);
                System.out.println();
            }else{
        //TODO:https://www.bezkoder.com/spring-boot-pagination-sorting-example/?fbclid=IwAR0TFgnSfDmxgQwrpm8jKEVztmWShdM0pbpGwKWEnmZWyHiFf27Ke4_fazM
            }


        } catch (Exception e) {
            return this.validatorUtil.responseEntity(offers);
        }


        //Pageable paging = PageRequest.of(page,size, sort);

        //вземаме кой параметър е пълен за да търсим по него в репото "критерия"
//        String param = getCriteriaParam(country, city, price);

        //отдолу сглабяме Pageable и тн.
//

//        Pageable three = PageRequest.of(page, size,
//                direction.equals("ASC") ? Sort.by("targetCity").ascending()
//                        : direction.equals("DESC") ? Sort.by("targetCity").descending()
//                        : null);


//
//        List<OfferDTO> allOffers =
//                this.tourOfferRepository
//                        .findAll()
//                        .stream()
//                        .map(e -> {
//                            this.mapper.map(e, TourOfferEntity.class)
//                            this.mapper.map(e, OfferDTO.class)
//                        })
//                        .collect(Collectors.toList());

//        List<OfferDTO> offers = this.tourOfferRepository.findAll(city)
//                .stream()
//                .map(e -> this.mapper.map(e, OfferDTO.class))
//                .collect(Collectors.toList());


        return this.validatorUtil.responseEntity(offers);
    }

    private Sort.Direction getDirectionOfSort(String directionCriteria) {
        //Order order1 = new Order(Sort.Direction.DESC, "published");
        if ("ASC".equalsIgnoreCase(directionCriteria)){
            return Sort.Direction.ASC;
        }else if ("DESC".equalsIgnoreCase(directionCriteria)){
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
