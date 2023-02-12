package com.example.web.service.impl;

import com.example.web.model.entity.OfferDataEntity;
import com.example.web.repository.OfferDataRepository;
import com.example.web.service.OfferDataService;
import java.nio.file.Path;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OfferDataServiceImpl implements OfferDataService {
  private final OfferDataRepository offerDataRepository;

  @Override
  public void saveFileUri(Long offerId, Path initPath) {

//    this.offerDataRepository.save(offerData);
  }

  @Override
  public void saveAll() {

  }
}
