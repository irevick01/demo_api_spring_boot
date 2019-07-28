package com.project.app.services;

import com.project.app.entities.models.UserModel;
import com.project.app.entities.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void save(UserModel userModel){
        userRepository.save(userModel);
    }


    public HashMap<String,Object> registerUser(UserModel userModel){

        UserModel existingUser = userRepository.findFirstByEmailOrPhone(userModel.getEmail(), userModel.getPhone());
        if( existingUser!=null )
            return getResponse(false, "Existing user!");

        else {
            save(userModel);
            return getResponse(true, "User registered successfully!");
        }

    }


    public HashMap<String,Object> updateUser(Long id, UserModel userModel){

        UserModel existingUser = userRepository.findById(id).orElse(null);
        if( existingUser!=null ){

            existingUser.setFirstName(userModel.getFirstName());
            existingUser.setLastName(userModel.getLastName());
            existingUser.setEmail(userModel.getEmail());
            existingUser.setPhone(userModel.getPhone());
            save(existingUser);

            return getResponse(true, "User updated successfully!");

        }
        else
            return getResponse(false, "No user found.");

    }





    public HashMap<String,Object> getUsers(){

        Iterable<UserModel> users = userRepository.findAll();
        HashMap<String,Object> response =  getResponse(true, "Successful!");
        response.put("users", users);
        return response;

    }





    public HashMap<String,Object> getUser(Long id){

        UserModel user = userRepository.findById(id).orElse(null);
        HashMap<String,Object> response =  getResponse(true, "Successful!");
        response.put("user", user);
        return response;

    }






    public HashMap<String,Object> deleteUser(Long id){

        userRepository.deleteById(id);
        return getResponse(true, "Successful!");
    }





    private HashMap<String,Object> getResponse(Boolean status, String message){

        HashMap<String,Object> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);
        return response;

    }



}
