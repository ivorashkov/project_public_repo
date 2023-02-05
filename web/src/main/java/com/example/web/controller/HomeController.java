package com.example.web.controller;

import com.example.web.model.dto.OfferDTO;
import com.example.web.service.TourOfferService;
import com.example.web.util.ValidatorUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("")
public class HomeController {
    private final TourOfferService tourOfferService;
    private final ValidatorUtil validatorUtil;

    @GetMapping("/search")
    public ResponseEntity<Page<OfferDTO>> response(
            @RequestParam(name = "criteria", required = false) String criteria) {

        /** http://localhost:8091/search?criteria=France **/
        Page<OfferDTO> offers = tourOfferService.initialSearchResult(criteria);

        return this.validatorUtil.responseEntity(offers);
        //should redirect to TourOfferController "/tourOffers" for more filters etc.
    }

}
