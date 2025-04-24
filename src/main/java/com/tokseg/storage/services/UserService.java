package com.tokseg.storage.services;

import com.tokseg.storage.domain.user.DTOs.UserDTO;
import com.tokseg.storage.domain.user.User;
import com.tokseg.storage.repositories.UserRepository;
import com.tokseg.storage.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public ApiResponse getAllUser(){
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();

        for(User user : users){
            userDTOs.add(new UserDTO(user.getId(), user.getName(), user.getEmail() , user.getRole() , user.getTelephone()));
        }


        return ApiResponse.success(userDTOs, "Todos os usuários");
    }

}
