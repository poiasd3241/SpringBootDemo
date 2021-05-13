package com.poiasd.restphonebooks.controller.v1.api;

import com.poiasd.restphonebooks.dto.model.UserDTO;
import com.poiasd.restphonebooks.service.UserService;
import com.poiasd.restphonebooks.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * The controller for interacting with the {@link UserDTO} persistence through a subordinate link, the {@link UserService}.
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Returns all users, or, if the are none, the helpful message.
     */
    @GetMapping(path = "/all")
    public Result<List<UserDTO>> findAll() {
        Optional<List<UserDTO>> users = userService.findAll();
        return users.map(Result::success).orElse(Result.failure("There are no users."));
    }

    /**
     * Returns the specified user.
     *
     * @param userUID The user UID.
     */
    @GetMapping("/{userUID}")
    public UserDTO getByUID(@PathVariable("userUID") String userUID) {
        return userService.getByUID(userUID);
    }

    /**
     * Returns users matching the specified user name, either full or partial.
     * If no such users are found, returns the helpful message.
     *
     * @param name       The user name.
     * @param isFullName {@code false} (default value) if the user name is partial; otherwise, {@code true}.
     */
    @GetMapping("/name/{name}")
    public Result<List<UserDTO>> findByName(@PathVariable("name") String name,
                                            @RequestParam(name = "fullName", defaultValue = "false") boolean isFullName) {
        Optional<List<UserDTO>> users = userService.findByName(name, isFullName);
        return users.map(Result::success).orElseGet(() -> {
            if (isFullName) {
                return Result.failure(String.format("No users with the name %s.", name));
            } else {
                return Result.failure(String.format("No users with the name matching %s.", name));
            }
        });
    }

    /**
     * Creates a new user using the specified user data.
     *
     * @param userDTO The user data to save.
     */
    @PostMapping()
    public String create(@Valid @RequestBody UserDTO userDTO) {
        userService.create(userDTO);
        return "User created successfully.";
    }

    /**
     * Updates the specified user, using the specified updated user data.
     *
     * @param userUID The user UID.
     * @param userDTO The updated user data.
     */
    @PutMapping("/{userUID}")
    public String update(@PathVariable("userUID") String userUID,
                         @Valid @RequestBody UserDTO userDTO) {
        userService.update(userUID, userDTO);
        return "User updated successfully.";

    }

    /**
     * Deletes the specified user.
     *
     * @param userUID The user UID.
     */
    @DeleteMapping("/{userUID}")
    public String delete(@PathVariable("userUID") String userUID) {
        userService.delete(userUID);
        return "User deleted successfully.";
    }
}
