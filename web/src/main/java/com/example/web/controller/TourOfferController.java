package com.example.web.controller;

import com.example.web.model.dto.OfferDTO;
import com.example.web.service.TourOfferService;
import com.example.web.util.ValidatorUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/offer")
public class TourOfferController {
    private final TourOfferService tourOfferService;
    private final ValidatorUtil validatorUtil;

    @GetMapping("")
    public ResponseEntity<Page<OfferDTO>> findAllAndSort(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                         @RequestParam(name = "pageSize", defaultValue = "30") Integer size,
                                                         @RequestParam(name = "country", required = false) String country,
                                                         @RequestParam(name = "city", required = false) String city,
                                                         @RequestParam(defaultValue = "date,desc") String... sort

    ) {

        /** http://localhost:8091/offer?sort=column1,direction1&sort=column2,direction2 provides
         * with 2 columns column1,direction1 */

        return this.validatorUtil.responseEntity(
                this.tourOfferService.searchAndFilterOffers(page, size, country, city, sort));
    }

    @GetMapping("/edit")
    public ResponseEntity<OfferDTO> editOffer(@RequestParam(name = "offerId", required = true) Long offerId,
                                              @RequestParam(name = "userId", required = true) Long userId
    ) {

        /** http://localhost:8091/offer/edit?offerId=1&userId=1 */
        return this.validatorUtil.responseEntity(this.tourOfferService.editOffer(offerId, userId));
    }

    @PostMapping("/save")
    public ResponseEntity<OfferDTO> saveOffer(
            @RequestParam(name = "offerId") Long offerId,
            @RequestParam(name = "userId") Long userId,
            @RequestBody OfferDTO offerDTO
    ) {
        //todo да говоря с кольо дали ще ми подава директно JSON обект или допълнително offerId, userId
        //TODO TO FINISH, HOW TO RECEIVE JSON OR PROCESS IT CORRECTLY
        OfferDTO offerDTO1 = offerDTO;
        return null;
    }

    //delete
    //create

}
