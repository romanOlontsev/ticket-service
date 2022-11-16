package com.viner.ticketservice.web;

import com.viner.ticketservice.service.UserService;
import com.viner.ticketservice.dto.AddOrUpdateUserDto;
import com.viner.ticketservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public UserDto addUser(@RequestBody AddOrUpdateUserDto user) {
        return userService.addUser(user);
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody AddOrUpdateUserDto user) {
        return userService.updateUser(userId, user);
    }

    @PatchMapping("/{userId}")
    public void markAsDeletedUser(@PathVariable Long userId) {
        userService.markAsDeletedUser(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
