package com.tokseg.storage.controllers;


import com.tokseg.storage.domain.user.AuthenticationDTO;
import com.tokseg.storage.domain.user.RegisterDTO;
import com.tokseg.storage.domain.user.ResponseLogin;
import com.tokseg.storage.domain.user.User;
import com.tokseg.storage.infra.security.TokenService;
import com.tokseg.storage.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody  AuthenticationDTO data){
        var auth = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.email(), data.password()));

        var token = tokenService.genareteToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new ResponseLogin(token));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data){

        if (this.repository.findByEmail(data.email())!=null){
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.email(),encryptedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
