package com.tokseg.storage.services;

import com.tokseg.storage.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UserService {
    @Autowired
    UserRepository repository;
    public String recoverPassword(String email){
        var user = repository.findByEmail(email);
        if(user != null){
            String newPassword = generateRandomPassword(6);
            String encryptedPassword = new BCryptPasswordEncoder().encode(newPassword);
            user.setPassword(encryptedPassword);
            repository.save(user);
            return newPassword;
        }
        return "Erro ao tentar recuperar senha";

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
