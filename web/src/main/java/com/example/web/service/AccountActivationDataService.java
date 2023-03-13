package com.example.web.service;

import com.example.web.model.dto.AccountInfoDTO;
import com.example.web.model.dto.UserDTO;
import java.nio.file.Path;
import java.util.List;

public interface AccountActivationDataService {

  void saveFileUri(UserDTO userDTO, Path initPath);

  //this could be used in Admin panel
  List<AccountInfoDTO> findAllAccountDataPaths(UserDTO user);
}
