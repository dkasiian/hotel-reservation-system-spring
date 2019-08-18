package com.dkasiian.controllers;

import com.dkasiian.model.entities.User;
import com.dkasiian.model.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Hotel Reservation System", tags = "user")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority(T(com.dkasiian.model.entities.Role).ADMIN)")
    @ApiOperation("Get a list of users")
    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @ApiOperation("Add a new user")
    @PostMapping
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @ApiOperation("Update a user")
    @PutMapping
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority(T(com.dkasiian.model.entities.Role).ADMIN)")
    @ApiOperation("Get a user by Id")
    @GetMapping("{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId){
        return userService.getUserById(userId)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @ApiOperation("Delete a user by Id")
    @DeleteMapping("{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") Long userId){
        return userService.getUserById(userId)
                .map(value -> {
                    userService.deleteUserById(userId);
                    return new ResponseEntity(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity(HttpStatus.BAD_REQUEST));
    }


}
