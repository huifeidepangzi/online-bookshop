package com.yi.online_bookshop.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yi.online_bookshop.api_response.GeneralApiResponse;
import com.yi.online_bookshop.dto.user.CreateUserDTO;
import com.yi.online_bookshop.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService bookshopUserService;

    @PostMapping("/create")
    public ResponseEntity<GeneralApiResponse> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        Map<String, String> responseData = Map.of(
                "email", createUserDTO.getEmail()
        );
        try {
            bookshopUserService.createUser(createUserDTO);
        } catch (DataIntegrityViolationException e) {
            GeneralApiResponse response = new GeneralApiResponse(
                    HttpStatus.BAD_REQUEST, "Email has been existing.", responseData
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        GeneralApiResponse response = new GeneralApiResponse(
                HttpStatus.CREATED, "Data created successfully.", responseData
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
