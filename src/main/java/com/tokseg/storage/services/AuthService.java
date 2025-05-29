package com.tokseg.storage.services;

import com.tokseg.storage.domain.user.DTOs.RegisterDTO;
import com.tokseg.storage.domain.user.User;
import com.tokseg.storage.domain.user.UserRole;
import com.tokseg.storage.repositories.UserRepository;
import com.tokseg.storage.response.ApiResponse;
import com.tokseg.storage.services.email.EmailContentBuilder;
import com.tokseg.storage.services.email.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailContentBuilder emailContentBuilder;

    @Autowired
    EmailServices emailServices;

    public User registerUser(RegisterDTO data){
        if (userRepository.findByEmail(data.email().toLowerCase())!=null){
            return null;
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(UserRole.valueOf(data.role()), data.telephone(), data.name(), encryptedPassword, data.email().toLowerCase());

        return userRepository.save(newUser);
    }
    public ApiResponse recoverPassword(String email){
        var user = userRepository.findByEmail(email);
        if(user != null){
            String newPassword = generateRandomPassword(6);
            String encryptedPassword = new BCryptPasswordEncoder().encode(newPassword);
            user.setPassword(encryptedPassword);
            userRepository.save(user);


            boolean emailStats = emailServices.sendEmail(email,
                    "Tokseg | Storage - Nova senha!",
                    emailContentBuilder.buildPasswordRecoverNotification(user.getName(), newPassword),
                    true);
            if (emailStats){
                return ApiResponse.success(null,"Sua nova senha foi enviada para seu email");
            }

        }
        return ApiResponse.error("Erro ao tentar recuperar senha");

    }


    private static String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();

        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }
}
