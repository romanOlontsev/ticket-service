package com.learn.ticketservice.web;

import com.learn.ticketservice.service.UserService;
import com.learn.ticketservice.service.dto.UserDto;
import com.learn.ticketservice.web.dto.AddUserDto;
import com.learn.ticketservice.web.dto.UpdateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public UserDto addUser(@RequestBody @Valid AddUserDto user) {
        return userService.addNewUser(user);
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable("userId") Long userId,
                           @RequestBody @Valid UpdateUserDto updateUserDto) {
       return userService.updateUser(userId, updateUserDto);
    }

    @PatchMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }


}
