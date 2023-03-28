package com.example.web.controller;

import com.example.web.model.dto.UserDTO;
import com.example.web.service.FileService;
import com.example.web.service.UserService;
import com.example.web.util.ValidatorUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class FileController {

  private final FileService fileService;
  private final UserService userService;
  private final ValidatorUtil validatorUtil;

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> response(@PathVariable("id") @Valid Long userId) {
    return this.validatorUtil.responseEntity(userService.findUserDTOById(userId));
  }

  @PostMapping("/upload/all/{userId}")
  public ResponseEntity<Object> handleAllFilesUpload(
      @RequestPart("file") List<MultipartFile> files,
//      @RequestParam("file") MultipartFile file,
      @PathVariable("userId") Long userId,
//      @RequestBody @Valid Request
      @RequestParam(name = "offerId", defaultValue = "-1") Long offerId
  ) {
    //localhost:8091/user/upload/all?userId=1&offerId=3
    //localhost:8091/user/upload/all?userId=1
    System.out.println();

    return this.validatorUtil.responseEntityBoolean
        (this.fileService.handleAllFilesUpload(files, userId, offerId));
  }

}
