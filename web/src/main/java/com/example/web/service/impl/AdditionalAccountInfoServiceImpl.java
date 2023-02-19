package com.example.web.service.impl;


import com.example.web.model.dto.AdditionalAccountInfoDTO;
import com.example.web.model.dto.UserDTO;
import com.example.web.model.entity.AdditionalAccountInfoEntity;
import com.example.web.model.entity.UserEntity;
import com.example.web.repository.AdditionalAccountInfoRepository;
import com.example.web.service.AdditionalAccountInfoService;
import com.example.web.service.UserService;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdditionalAccountInfoServiceImpl implements AdditionalAccountInfoService {

  private final UserService userService;
  private final AdditionalAccountInfoRepository additionalAccountInfoRepository;
  private final ModelMapper mapper;

  @Override
  public void saveFileUri(Long userId, Path initPath) {
    AdditionalAccountInfoEntity additionalInfo =
        new AdditionalAccountInfoEntity(initPath.toString(),
            this.userService.findById(userId));

    this.additionalAccountInfoRepository.save(additionalInfo);
  }

  @Override
  public void saveAll(List<UserEntity> users, Path initPath) {
    users.forEach(u -> saveFileUri(u.getId(), initPath));
  }

  @Override
  public List<AdditionalAccountInfoDTO> findAllAccountDataPaths(UserDTO user) {
    List<AdditionalAccountInfoEntity> additionalData =
        this.additionalAccountInfoRepository.findAllByUserId(user.getId()).orElse(null);

    return additionalData.stream()
        .filter(Objects::nonNull)
        .map(e -> this.mapper.map(e, AdditionalAccountInfoDTO.class))
        .collect(Collectors.toList());
  }


}
