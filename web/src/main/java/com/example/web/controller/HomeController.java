package com.example.web.controller;

import com.example.web.model.dto.OfferDTO;
import com.example.web.service.TourOfferService;
import com.example.web.util.ValidatorUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("")
public class HomeController {

  private final TourOfferService tourOfferService;
  private final ValidatorUtil validatorUtil;

  @GetMapping("/search")
  public ResponseEntity<Page<OfferDTO>> response(
      @RequestParam(name = "page", defaultValue = "0") Integer page,
      @RequestParam(name = "pageSize", defaultValue = "30") Integer size
  ) {

    /** http://localhost:8091/search?criteria=France **/
    return this.validatorUtil.responseEntity(tourOfferService.initialSearchResult(page, size));
    //should redirect to TourOfferController "/tourOffers" for more filters etc.
  }

}
