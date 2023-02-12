package com.example.web.service;

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

  void saveAllFiles(List<MultipartFile> files, Long userId, Long offerId);

   Path handleFileUpload(MultipartFile file, Long userId, Long offerId);
//  void store(MultipartFile file, Long userId, Path path);
//  Path pathInitialization(Long userId, Long offerId, String formatAddonTemplate);
}
