package com.example.web.service.impl;


import com.example.web.model.dto.AccountInfoDTO;
import com.example.web.model.dto.UserDTO;
import com.example.web.model.entity.AccountInfoEntity;
import com.example.web.model.entity.UserEntity;
import com.example.web.repository.AccountInfoRepository;
import com.example.web.service.AccountInfoService;
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
public class AccountInfoServiceImpl implements AccountInfoService {

  private final AccountInfoRepository additionalAccountInfoRepository;
  private final ModelMapper mapper;

  @Override
  public void saveFileUri(UserDTO userDTO, Path initPath) {
    /**
     * to check if its working correctly after i removed userservice.findEntityById
     * with DTO method.
     */
    var userEntity = this.mapper.map(userDTO, UserEntity.class);

    var additionalInfoEntity = new AccountInfoEntity(initPath.toString(), userEntity);

    this.additionalAccountInfoRepository.save(additionalInfoEntity);
  }

  @Override
  public void saveAll(List<UserEntity> users, Path initPath) {
    //todo to check if its working
    users
        .stream()
        .map(e -> this.mapper.map(e, UserDTO.class))
        .forEach(e -> saveFileUri(e, initPath));
  }

  @Override
  public List<AccountInfoDTO> findAllAccountDataPaths(UserDTO user) {
    List<AccountInfoEntity> accountInfoEntity =
        this.additionalAccountInfoRepository.findAllByUserId(user.getId()).orElse(null);

    return accountInfoEntity.stream()
        .filter(Objects::nonNull)
        .map(e -> this.mapper.map(e, AccountInfoDTO.class))
        .collect(Collectors.toList());
  }


}