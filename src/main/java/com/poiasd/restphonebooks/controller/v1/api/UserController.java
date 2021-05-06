package com.poiasd.restphonebooks.controller.v1.api;

import com.poiasd.restphonebooks.dto.model.UserDTO;
import com.poiasd.restphonebooks.service.UserService;
import com.poiasd.restphonebooks.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "{/all}")
    public Result<List<UserDTO>> getAll() {
        Optional<List<UserDTO>> users = userService.findAll();
        return users.map(Result::success).orElse(Result.failure("There are no users."));
    }

    @GetMapping("/{userUID}")
    public UserDTO getByUID(@PathVariable("userUID") String userUID) {
        return userService.getByUID(userUID);
    }

    @GetMapping("/name/{name}")
    public Result<List<UserDTO>> getByName(@PathVariable("name") String name,
                                           @RequestParam("fullName") boolean isFullName) {
        Optional<List<UserDTO>> users = userService.findByName(name, isFullName);
        return users.map(Result::success).orElseGet(() -> {
            if (isFullName) {
                return Result.failure("No users with the name \"" + name + "\".");
            } else {
                return Result.failure("No users with the name matching \"" + name + "\".");
            }
        });
    }

    @PostMapping()
    public String create(@Valid @RequestBody UserDTO userDTO) {
        userService.create(userDTO);
        return "User created successfully.";
    }

    @PutMapping("/{userUID}")
    public String update(@PathVariable("userUID") String userUID,
                         @Valid @RequestBody UserDTO userDTO) {
        userService.update(userUID, userDTO);
        return "User updated successfully.";

    }

    @DeleteMapping("/{userUID}")
    public String delete(@PathVariable("userUID") String userUID) {
        userService.delete(userUID);
        return "User deleted successfully.";
    }
}
