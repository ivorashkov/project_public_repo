package com.example.web.controller;

import com.example.web.model.dto.OfferDTO;
import com.example.web.service.TourOfferService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/offer")
public class TourOfferController {
    private final TourOfferService tourOfferService;

    @GetMapping("")
    public ResponseEntity<Page<OfferDTO>> findAllAndSort(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                         @RequestParam(name = "pageSize", defaultValue = "30") Integer size,
                                                         @RequestParam(name = "country", required = false) String country,
                                                         @RequestParam(name = "city", required = false) String city,
                                                         @RequestParam(defaultValue = "date,desc") String... sort

    ) {

        /** http://localhost:8091/offer?sort=column1,direction1&sort=column2,direction2 provides
         * with 2 columns column1,direction1*/

        return this.tourOfferService.offerSearchAndFilter(page,size,country,city,sort);
    }

    //edit = update
    //delete
    //create
    //findBy... ASC
}
