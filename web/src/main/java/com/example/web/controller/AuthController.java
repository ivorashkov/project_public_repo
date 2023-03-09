package com.example.web.controller;

import com.example.web.model.requestDto.UserRegistrationDTO;
import com.example.web.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDTO registrationDTO) {
    return this.authService.createUser(registrationDTO);
  }

  @GetMapping
  public ResponseEntity<?> helloMethod(){
    return ResponseEntity.ok("Hello");
  }
//  @PostMapping("/signout")
//  public ResponseEntity<?> logoutUser() {
//    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
//    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
//        .body(new MessageResponse("You've been signed out!"));
//  }

//  @PostMapping("/signin")
//  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//
//    Authentication authentication = authenticationManager
//        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//    SecurityContextHolder.getContext().setAuthentication(authentication);
//
//    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
//    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
//
//    List<String> roles = userDetails.getAuthorities().stream()
//        .map(item -> item.getAuthority())
//        .collect(Collectors.toList());
//
//    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
//        .body(new UserInfoResponse(userDetails.getId(),
//            userDetails.getUsername(),
//            userDetails.getEmail(),
//            roles));
//  }

}
