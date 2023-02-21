package com.example.web.service;

import com.example.web.model.dto.AdditionalAccountInfoDTO;
import com.example.web.model.dto.UserDTO;
import com.example.web.model.entity.UserEntity;
import java.nio.file.Path;
import java.util.List;

public interface AdditionalAccountInfoService {

  void saveFileUri(Long userId, Path path);

  void saveAll(List<UserEntity> users, Path initPath);

  //this could be used in Admin panel
  List<AdditionalAccountInfoDTO> findAllAccountDataPaths(UserDTO user);
}
