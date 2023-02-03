package com.example.web.controller;

import com.example.web.model.dto.OfferDTO;
import com.example.web.service.TourOfferService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("")
public class HomeController {
    private final TourOfferService tourOfferService;

    @GetMapping("/search")
    public ResponseEntity<List<OfferDTO>> response(
            @RequestParam(name = "criteria", required = false) String criteria) {

        /** http://localhost:8091/search?criteria=France **/
        List<OfferDTO> list = tourOfferService.initialSearchResult(criteria);

        if (list == null) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(list);
        }
        //should redirect to TourOfferController "/tourOffers" for more filters etc.
    }

}
