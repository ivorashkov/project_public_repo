package com.example.web.service.impl;


import com.example.web.model.dto.AccountInfoDTO;
import com.example.web.model.dto.TourOfferImagePathDTO;
import com.example.web.model.dto.UserDTO;
import com.example.web.model.entity.AccountInfoEntity;
import com.example.web.model.entity.UserEntity;
import com.example.web.repository.AccountInfoRepository;
import com.example.web.service.AccountInfoService;
import com.example.web.service.UserService;
import com.example.web.util.ValidatorUtil;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountInfoServiceImpl implements AccountInfoService {

  private final ValidatorUtil validatorUtil;
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
    users
        .stream()
        .map(e -> this.mapper.map(e, UserDTO.class))
        .forEach(e -> saveFileUri(e, initPath));
  }

  @Override
  public List<AccountInfoDTO> findAllAccountDataPaths(UserDTO user) {

    List<AccountInfoEntity> accountInfoEntity =this.validatorUtil.getListFromOptionalList
            (
                this.additionalAccountInfoRepository.findAllByUserId(user.getId())
            );

    return this.validatorUtil.getDTOList(accountInfoEntity, AccountInfoDTO.class);
  }


}
