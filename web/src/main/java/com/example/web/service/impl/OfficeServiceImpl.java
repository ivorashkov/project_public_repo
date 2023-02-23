package com.example.web.service.impl;

import com.example.web.model.dto.OfficeDTO;
import com.example.web.model.entity.OfficeEntity;
import com.example.web.repository.OfficeRepository;
import com.example.web.service.OfficeService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OfficeServiceImpl implements OfficeService {

  private final OfficeRepository officeRepository;
  private final ModelMapper modelMapper;

  @Override
  public List<OfficeDTO> addOffice(OfficeDTO officeDTO) {

    return saveOfficeAndReturnAllOffices(officeDTO);
  }

  @Override
  public void deleteOffice(OfficeDTO officeDTO) {

    Optional<OfficeEntity> officeEntity = this.officeRepository.findByIdAndUserId
        (
            officeDTO.getId(),
            officeDTO.getUser().getId()
        );

    if (officeEntity.isPresent()) {
      officeEntity.get().setDeleted(true);
      this.officeRepository.save(officeEntity.get());
    }
  }

  @Override
  public List<OfficeDTO> editOffice(OfficeDTO officeDTO) {

    return saveOfficeAndReturnAllOffices(officeDTO);
  }

  private List<OfficeDTO> saveOfficeAndReturnAllOffices(OfficeDTO officeDTO){
    var officeEntity = this.modelMapper.map(officeDTO, OfficeEntity.class);

    this.officeRepository.save(officeEntity);

    return this.officeRepository.findAllOfficesByUserIdAsc(officeDTO.getUser().getId())
        .stream()
        .map(e -> this.modelMapper.map(e, OfficeDTO.class))
        .collect(Collectors.toList());
  }
}