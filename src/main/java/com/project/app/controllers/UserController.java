package com.project.app.controllers;

import com.project.app.entities.models.UserModel;
import com.project.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = { "/v1", "/api/v1" })

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method= RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Map<String, Object>> registerUser (@RequestBody UserModel userModel) {

        return ResponseEntity.ok(userService.registerUser(userModel));

    }

    @RequestMapping(value = "/users", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Map<String, Object>> getUsers () {

        return ResponseEntity.ok(userService.getUsers());

    }

    @RequestMapping(value = "/users/{id}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable("id") String id) {

        return ResponseEntity.ok(userService.getUser(Long.parseLong(id)));

    }

    @RequestMapping(value = "/users/{id}", method= RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable("id") String id, @RequestBody UserModel userModel) {

        return ResponseEntity.ok(userService.updateUser( Long.parseLong(id), userModel ));

    }

    @RequestMapping(value = "/users/{id}", method= RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable("id") String id) {

        return ResponseEntity.ok(userService.deleteUser(Long.parseLong(id)));

    }


}
