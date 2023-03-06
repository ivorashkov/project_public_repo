package com.example.web.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;


public interface FileService {

  boolean handleAllFilesUpload
      (
          List<MultipartFile> files,
          Long userId,
          Long offerId
      );

}
