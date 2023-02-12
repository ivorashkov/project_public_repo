package com.example.web.service;

import java.nio.file.Path;

public interface OfferDataService {
   void saveFileUri(Long offerId, Path initPath);
   void saveAll();
}
