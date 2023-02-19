package com.example.web.controller;

import com.example.web.model.dto.ImportCreateOfferInfoDTO;
import com.example.web.model.dto.ResponseOfferInfoDTO;
import com.example.web.model.dto.TourOfferDTO;
import com.example.web.model.dto.UserDTO;
import com.example.web.model.entity.UserEntity;
import com.example.web.model.enums.TransportType;
import com.example.web.repository.UserRepository;
import com.example.web.service.FileService;
import com.example.web.service.OfferDataService;
import com.example.web.service.TourOfferService;
import com.example.web.service.UserService;
import com.example.web.util.ValidatorUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@AllArgsConstructor
@RequestMapping("/offer")
public class TourOfferController {

  private final UserRepository userRepository;
  private final ModelMapper modelMapper;

  private final TourOfferService tourOfferService;
  private final OfferDataService offerDataService;

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
      @RequestParam(value = "file", required = false) List<MultipartFile> files
//      @RequestBody(required = false) ImportCreateOfferInfoDTO createOfferDTO
  ) {
    /** incoming JSON object ImportCreateOfferInfoDTO */
//    UserDTO user = new UserDTO(3L, "ivor", "1234213235", "ivo", "rashkov");
    Optional<UserEntity> userE = this.userRepository.findById(3L);
    UserDTO user = this.modelMapper.map(userE, UserDTO.class);

    ImportCreateOfferInfoDTO createOfferDTO =
        new ImportCreateOfferInfoDTO(
            user,
            "new Title",
            LocalDateTime.now(),
            "Testoniq",
            "Testanqn",
            5,
            4.0,
            BigDecimal.valueOf(11.11),
            "testche",
            0,
            TransportType.airplane);

    /** DTO + FILES **/
    this.tourOfferService.saveOfferPath(
        this.tourOfferService.createOffer(createOfferDTO), files);

//    this.offerDataService.


    return null;
  }


}
