package com.example.web.service;

import com.example.web.model.dto.TourOfferFullDTO;
import com.example.web.model.dto.UserDTO;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileService {

  Stream<Path> loadAll();

  Path load(String filename);

  Resource loadAsResource(String filename);

  void deleteAll();

  void handleAllFilesUpload
      (
          List<MultipartFile> files,
          TourOfferFullDTO tourOfferFullDTO
      );

  void handleAllFilesUpload
      (
          List<MultipartFile> files,
          Long userId,
          Long offerId,
          UserDTO userDTO
      );

//  void store(MultipartFile file, Long userId, Path path);
//  Path pathInitialization(Long userId, Long offerId, String formatAddonTemplate);
}
