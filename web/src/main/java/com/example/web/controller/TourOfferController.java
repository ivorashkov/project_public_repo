package com.example.web.controller;

import com.example.web.model.dto.ImportCreateOfferInfoDTO;
import com.example.web.model.dto.ResponseOfferInfoDTO;
import com.example.web.service.FileService;
import com.example.web.service.TourOfferService;
import com.example.web.util.ValidatorUtil;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("/offer")
public class TourOfferController {

  private final TourOfferService tourOfferService;
  private final FileService fileService;

  private final ValidatorUtil validatorUtil;


  @GetMapping("/edit")
  public ResponseEntity<ResponseOfferInfoDTO> editOffer(
      @RequestParam(name = "offerId", required = true) Long offerId,
      @RequestParam(name = "userId", required = true) Long userId
  ) {

    /** http://localhost:8091/offer/edit?offerId=1&userId=1 */
    return this.validatorUtil.responseEntity(this.tourOfferService.editOffer(offerId, userId));
  }

  @PostMapping("/save")
  public ResponseEntity<ResponseOfferInfoDTO> saveOffer(
      @RequestParam(name = "offerId") Long offerId,
      @RequestParam(name = "userId") Long userId,
      @RequestBody ResponseOfferInfoDTO offerDTO
  ) {
    //todo да говоря с кольо дали ще ми подава директно JSON обект или допълнително offerId, userId
    //TODO TO FINISH, HOW TO RECEIVE JSON OR PROCESS IT CORRECTLY

    ResponseOfferInfoDTO offerDTO1 = offerDTO;
    return null;
  }

  @PostMapping("/delete")
  public void deleteOffer() {
    //delete

  }

  @PostMapping("/create")
  public ResponseEntity<ResponseOfferInfoDTO> createOffer(
      @RequestParam(value = "file", required = false) List<MultipartFile> files,
      @RequestBody ImportCreateOfferInfoDTO createOfferDTO) {

    /** DTO + FILES **/

    Long offerId = this.tourOfferService.createOffer(createOfferDTO);

    this.fileService.saveAllFiles(files, createOfferDTO.getUser().getId(), offerId);

    //create
    return null;
  }


}
