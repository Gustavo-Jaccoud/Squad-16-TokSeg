package com.tokseg.storage.controllers.auth;


import com.tokseg.storage.response.ApiResponse;
import com.tokseg.storage.domain.user.*;
import com.tokseg.storage.domain.user.DTOs.AuthenticationDTO;
import com.tokseg.storage.domain.user.DTOs.RecoverPasswordDTO;
import com.tokseg.storage.domain.user.DTOs.RegisterDTO;
import com.tokseg.storage.infra.security.TokenService;
import com.tokseg.storage.repositories.UserRepository;
import com.tokseg.storage.services.AuthService;
import com.tokseg.storage.services.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    AuthService service;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var auth = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.email().toLowerCase(), data.password()));

        var token = tokenService.genareteToken((User) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(new ResponseLogin(token), "Login feito com sucesso"));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){

        var user = service.registerUser(data);

        if(user==null){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .body(ApiResponse.success(null, "Usuário Criado com sucesso"));
    }

    @PostMapping("/recoverpassword")
    public ResponseEntity recoverPassword(@RequestBody @Valid RecoverPasswordDTO data){
        String response = service.recoverPassword(data.email().toLowerCase());

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(response, "Email Enviado com sucesso"));
    }
}
