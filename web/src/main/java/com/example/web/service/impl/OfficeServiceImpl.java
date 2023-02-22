package com.example.web.service.impl;

import com.example.web.model.dto.OfficeDTO;
import com.example.web.model.entity.OfficeEntity;
import com.example.web.repository.OfficeRepository;
import com.example.web.service.OfficeService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OfficeServiceImpl implements OfficeService {

  private final OfficeRepository officeRepository;
  private final ModelMapper modelMapper;

  public String addOffice(OfficeDTO officeDTO) {
    try {
      this.officeRepository.save(this.modelMapper.map(officeDTO, OfficeEntity.class));
      return "office saved";
    } catch (Exception e) {
      return "error while trying to save: " + e.getMessage();
    }
  }

  public String deleteOffice(OfficeDTO officeDTO) {
    String message = null;
    try {
      Optional<OfficeEntity> officeForDelete =
          this.officeRepository.findByIdAndUserId(officeDTO.getId(), officeDTO.getUser().getId());
      if (officeForDelete.isPresent()) {
        officeForDelete.get().setDeleted(true);
        this.officeRepository.save(officeForDelete.get());
        message = "deleted";
      }
    } catch (Exception e) {
      message = "Error while deleting Office: Reason " + e.getMessage();
    }
    return message;
  }
}
