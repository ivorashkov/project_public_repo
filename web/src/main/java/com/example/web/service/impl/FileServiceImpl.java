package com.example.web.service.impl;

import com.example.web.constant.ConstantMessages;
import com.example.web.constant.StorageException;
import com.example.web.constant.StorageFileNotFoundException;
import com.example.web.constant.StorageProperties;
import com.example.web.model.dto.TourOfferFullDTO;
import com.example.web.model.dto.UserDTO;
import com.example.web.service.AccountInfoService;
import com.example.web.service.FileService;
import com.example.web.service.OfferDataService;
import com.example.web.service.TourOfferService;
import com.example.web.service.UserService;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;


@Service
public class FileServiceImpl implements FileService {


  private final OfferDataService offerDataService;
  private final AccountInfoService additionalInfoService;
  private final Path rootLocation;

  public FileServiceImpl
      (
          OfferDataService offerDataService,
          StorageProperties properties,
          AccountInfoService additionalInfoService
      ) {
    this.offerDataService = offerDataService;
    this.rootLocation = Paths.get(properties.getLocation());
    this.additionalInfoService = additionalInfoService;
  }

  @Override
  public void handleAllFilesUpload
      (
          List<MultipartFile> files,
          Long userId,
          Long offerId,
          TourOfferFullDTO tourOfferFullDTO
      ) {

    files.forEach(file -> {

      Path path = handleSingleFileUpload(file, userId, offerId);

      this.offerDataService.saveFileUri(tourOfferFullDTO, path);

    });
  }

  @Override
  public void handleAllFilesUpload
      (
          List<MultipartFile> files,
          Long userId,
          Long offerId,
          UserDTO userDTO
      ) {

    files.forEach(file -> {

      Path path = handleSingleFileUpload(file, userId, offerId);

      this.additionalInfoService.saveFileUri(userDTO, path);

    });
  }


  public Path handleSingleFileUpload(MultipartFile file, Long userId, Long offerId) {

    /** http://localhost:8091/home/upload?userId=1&offerId=-1 */
    Path initPath = pathInitialization(userId, offerId,
        ConstantMessages.FORMAT_ADDON_TEMPLATE);

    store(file, initPath);

    return initPath;
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

    } else if (offerId >= 0) {

      /** then we should create Offer directory with offerId */
      stringDirectory
          .append(ConstantMessages.DIRECTORY_SEPARATOR)
          .append(String.format(stringFormat, ConstantMessages.OFFER, offerId));

      new File(stringDirectory.toString()).mkdirs();
    }

    return Paths.get(stringDirectory.toString());
  }

  private void store(MultipartFile file, Path pathFromInitialization) {
    try {
      if (file.isEmpty()) {
        throw new StorageException("Failed to store empty file.");
      }

      Path destinationFile = pathFromInitialization
          .resolve(Paths.get(file.getOriginalFilename()))
          .normalize().toAbsolutePath();

      if (!destinationFile.getParent().equals(pathFromInitialization.toAbsolutePath())) {
        // This is a security check
        throw new StorageException(
            "Cannot store file outside current directory.");
      }

      try (InputStream inputStream = file.getInputStream()) {
        Files.copy(inputStream, destinationFile,
            StandardCopyOption.REPLACE_EXISTING);
      }
    } catch (IOException e) {
      throw new StorageException("Failed to store file.", e);
    }
  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.rootLocation, 1)
          .filter(path -> !path.equals(this.rootLocation))
          .map(this.rootLocation::relativize);
    } catch (IOException e) {
      throw new StorageException("Failed to read stored files", e);
    }
  }

  @Override
  public Path load(String filename) {
    return rootLocation.resolve(filename);
  }

  @Override
  public Resource loadAsResource(String filename) {
    try {
      Path file = load(filename);
      Resource resource = new UrlResource(file.toUri());
      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new StorageFileNotFoundException("Could not read file: " + filename);
      }
    } catch (MalformedURLException e) {
      throw new StorageFileNotFoundException("Could not read file: " + filename, e);
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(rootLocation.toFile());
  }

}
