package com.example.web.service.impl;

import com.example.web.constant.ConstantMessages;
import com.example.web.exception.StorageException;
import com.example.web.constant.StorageProperties;
import com.example.web.model.dto.TourOfferFullDTO;
import com.example.web.model.dto.UserDTO;
import com.example.web.service.AccountInfoService;
import com.example.web.service.FileService;
import com.example.web.service.TourOfferDataService;
import com.example.web.service.TourOfferService;
import com.example.web.service.UserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

  private final TourOfferService tourOfferService;
  private final UserService userService;
  private final AccountInfoService additionalInfoService;
  private final TourOfferDataService offerDataService;
  private final Path rootLocation;

  public FileServiceImpl
      (
          TourOfferService tourOfferService,
          UserService userService, TourOfferDataService offerDataService,
          StorageProperties properties,
          AccountInfoService additionalInfoService
      ) {
    this.tourOfferService = tourOfferService;
    this.userService = userService;
    this.offerDataService = offerDataService;
    this.rootLocation = Paths.get(properties.getLocation());
    this.additionalInfoService = additionalInfoService;
  }

  @Override
  public boolean handleAllFilesUpload(List<MultipartFile> files, Long userId, Long offerId) {
    log.info("FileServiceImpl { handleAllFilesUpload } {}, {}",userId, offerId);

    files.forEach(file -> {
      Path path = handleSingleFileUpload(file, userId, offerId);

      if (offerId > 0) {
        TourOfferFullDTO offerDTO = this.tourOfferService.findByIdAndUserId(offerId, userId);
        this.offerDataService.saveFileUri(offerDTO, path);

      } else {
        UserDTO userDTO = this.userService.findUserDTOById(userId);
        this.additionalInfoService.saveFileUri(userDTO, path);
      }
    });
      return true;
  }

  public Path handleSingleFileUpload(MultipartFile file, Long userId, Long offerId) {

    /** http://localhost:8091/home/upload?userId=1&offerId=-1 */
    Path initPath = pathInitialization(userId, offerId,
        ConstantMessages.FORMAT_ADDON_TEMPLATE);

    return store(file, initPath);
  }

  private Path pathInitialization(Long userId, Long offerId, String stringFormat) {
    StringBuilder stringDirectory = new StringBuilder();

    stringDirectory
        .append(rootLocation)
        .append(ConstantMessages.DIRECTORY_SEPARATOR)
        .append(String.format(stringFormat, ConstantMessages.USER, userId));

    if (!Files.exists(Paths.get(stringDirectory.toString()))) {
      /** if the user has no directory already -> \\ creating folders using template %s_%d **/
      /** creating folder if such does not exist **/
      new File(stringDirectory.toString()).mkdirs();

    }

    if (offerId >= 0) {

      /** then we should create Offer directory with offerId */
      stringDirectory
          .append(ConstantMessages.DIRECTORY_SEPARATOR)
          .append(String.format(stringFormat, ConstantMessages.OFFER, offerId));

      new File(stringDirectory.toString()).mkdirs();
    }

    return Paths.get(stringDirectory.toString());
  }

  private Path store(MultipartFile file, Path pathFromInitialization) {
    Path destinationFile=null;

    try {
      if (file.isEmpty()) {
        throw new StorageException("Failed to store empty file.");
      }

      destinationFile = pathFromInitialization
          .resolve(Paths.get(file.getOriginalFilename()))
          .normalize().toAbsolutePath();

      if (!destinationFile.getParent().equals(pathFromInitialization.toAbsolutePath())) {
        // This is a security check
        throw new StorageException("Cannot store file outside current directory.");
      }

      try (InputStream inputStream = file.getInputStream()) {
        Files.copy(inputStream, destinationFile,
            StandardCopyOption.REPLACE_EXISTING);
      }

    } catch (IOException e) {
      log.error("Failed to store file in FileServiceImpl {}", e.getMessage());
    }

    return destinationFile;
  }

}
