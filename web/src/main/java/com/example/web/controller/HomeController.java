package com.example.web.controller;

import com.example.web.model.dto.OfferDTO;
import com.example.web.service.TourOfferService;
import com.example.web.util.ValidatorUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("")
public class HomeController {
    private final TourOfferService tourOfferService;
    private final ValidatorUtil validatorUtil;

    @GetMapping("/search")
    public ResponseEntity<List<OfferDTO>> response(
            @RequestParam(name = "criteria", required = false) String criteria) {

        /** http://localhost:8091/search?criteria=France **/
        List<OfferDTO> list = tourOfferService.initialSearchResult(criteria);

        return this.validatorUtil.responseEntity(list);
        //should redirect to TourOfferController "/tourOffers" for more filters etc.
    }

}
