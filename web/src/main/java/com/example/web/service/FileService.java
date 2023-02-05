package com.example.web.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileService {


    void store(MultipartFile file, Long userId, Path path);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    Path initialization(Long userId, Long offerId, String formatAddonTemplate);
}
