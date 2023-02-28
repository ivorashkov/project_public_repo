package com.example.web.service;

import com.example.web.model.dto.TourOfferFullDTO;
import com.example.web.model.dto.UserDTO;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileService {

  void handleAllFilesUpload
      (
          List<MultipartFile> files,
          Long userId,
          Long offerId
      );

  Stream<Path> loadAll();

  Path load(String filename);

  Resource loadAsResource(String filename);

}
