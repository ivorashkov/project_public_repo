package com.example.web.constant;

import com.example.web.model.entity.RoleEntity;
import com.example.web.model.enums.RoleType;

public class UserRegistrationConstants {

  public static final boolean NOT_ACTIVE = false;
  public static final boolean SET_ACTIVE = true;

  public static final RoleEntity USER_ROLE = new RoleEntity(RoleType.user);
  public static final RoleEntity ADMIN_ROLE = new RoleEntity(RoleType.admin);

  public static final int APPROVED_BY_INITIAL_ID = 0;

  public static final boolean HAS_NO_STAR = false;
  public static final boolean HAS_A_STAR = true;
  public static final long REGULAR_USER_ROLE_ID = 2;


}
