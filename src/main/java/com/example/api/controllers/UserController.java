package com.example.api.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.api.models.ResponseObject;
import com.example.api.models.User;
import com.example.api.services.UserService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getUserById(@PathVariable Long id){
        Optional<User> findUser = userService.getUserById(id);
        return findUser.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("OK", HttpStatus.OK.value(), "Query user successfully", userService.getUserById(id))
        ) :
        ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("FAILED", HttpStatus.OK.value(), "Cannot find user with id =" + id, userService.getUserById(id))
        );
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertUser(@RequestBody User user) {
    try {
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("OK", HttpStatus.OK.value(), "Insert User successfully", userService.insertUser(user))
        );
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("ERROR", HttpStatus.BAD_REQUEST.value(), e.getMessage(), "")
            );
        }
    }

}
