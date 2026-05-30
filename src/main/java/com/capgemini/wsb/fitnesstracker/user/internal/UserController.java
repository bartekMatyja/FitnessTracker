package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller exposing CRUD and search operations on users.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    /**
     * Returns full details of all users.
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    /**
     * Returns basic information (identifier and name) for all users.
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllUsersSimple() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toSimpleDto)
                          .toList();
    }

    /**
     * Searches users by email fragment (case-insensitive) and returns identifier with email.
     */
    @GetMapping("/email")
    public List<UserEmailDto> getUsersByEmail(@RequestParam final String email) {
        return userService.findUsersByEmailFragment(email)
                          .stream()
                          .map(userMapper::toEmailDto)
                          .toList();
    }

    /**
     * Returns users born before the given date.
     */
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate time) {
        return userService.findUsersBornBefore(time)
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    /**
     * Returns full details of a user identified by the given identifier.
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable final Long id) {
        return userService.getUser(id)
                          .map(userMapper::toDto)
                          .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Creates a new user.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody final UserDto userDto) {
        userService.createUser(userMapper.toEntity(userDto));
    }

    /**
     * Updates an existing user.
     */
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable final Long userId, @RequestBody final UserDto userDto) {
        userService.updateUser(userId, userMapper.toEntity(userDto));
    }

    /**
     * Deletes a user identified by the given identifier.
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable final Long userId) {
        userService.deleteUser(userId);
    }

}
