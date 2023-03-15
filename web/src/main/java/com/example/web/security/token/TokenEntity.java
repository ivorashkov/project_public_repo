package com.example.web.security.token;

import com.example.web.model.entity.BaseEntity;
import com.example.web.model.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tokens")
public class TokenEntity extends BaseEntity {

  private String token;

  private TokenType tokenType;

  private boolean expired;

  private boolean revoked;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;

}
