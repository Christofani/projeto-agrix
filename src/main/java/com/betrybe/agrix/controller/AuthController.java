package com.betrybe.agrix.controller;
import com.betrybe.agrix.controller.dto.*;
import com.betrybe.agrix.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;


  @Autowired
  public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  @PostMapping("/login")
  public TokenDto login(@RequestBody AuthDto authDto) {
    UsernamePasswordAuthenticationToken usernamePassword =
            new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password());
   Authentication auth = authenticationManager.authenticate(usernamePassword);

   String token = tokenService.generateToken(authDto.username());
   return new TokenDto(token);
  }
}

// configurar Jwt