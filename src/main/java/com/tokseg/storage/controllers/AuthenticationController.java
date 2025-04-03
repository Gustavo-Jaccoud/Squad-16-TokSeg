package com.tokseg.storage.controllers;


import com.tokseg.storage.Response.ApiResponse;
import com.tokseg.storage.domain.user.*;
import com.tokseg.storage.domain.user.DTOs.AuthenticationDTO;
import com.tokseg.storage.domain.user.DTOs.RecoverPasswordDTO;
import com.tokseg.storage.domain.user.DTOs.RegisterDTO;
import com.tokseg.storage.infra.security.TokenService;
import com.tokseg.storage.repositories.UserRepository;
import com.tokseg.storage.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    UserService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var auth = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.email().toLowerCase(), data.password()));

        var token = tokenService.genareteToken((User) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(new ResponseLogin(token), "Login feito com sucesso"));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){

        if (this.repository.findByEmail(data.email().toLowerCase())!=null){
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(UserRole.valueOf(data.role()), data.telephone(), data.name(), encryptedPassword, data.email().toLowerCase());

        this.repository.save(newUser);

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .body(ApiResponse.success(null, "Usuário Criado com sucesso"));
    }
    @PostMapping("/recoverpassword")
    public ResponseEntity recoverPassword(@RequestBody @Valid RecoverPasswordDTO data){
        String response = service.recoverPassword(data.email().toLowerCase());

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(response, "Email Enviado com sucesso"));
    }
    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("Rota /teste funcionando!");
    }
}
