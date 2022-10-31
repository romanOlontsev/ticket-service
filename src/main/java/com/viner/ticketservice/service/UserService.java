package com.viner.ticketservice.service;

import com.viner.ticketservice.entity.User;
import com.viner.ticketservice.exceptions.DataNotFoundException;
import com.viner.ticketservice.exceptions.EntityExistsException;
import com.viner.ticketservice.mappers.UserMapper;
import com.viner.ticketservice.repository.UserRepository;
import com.viner.ticketservice.dto.AddOrUpdateUserDto;
import com.viner.ticketservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Transactional
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                             .stream()
                             .map(mapper::userToUserDto)
                             .collect(Collectors.toList());
    }

    @Transactional
    public UserDto getUserById(Long userId) {
        return mapper.userToUserDto(getUserEntity(userId));
    }

    @Transactional
    public UserDto addUser(AddOrUpdateUserDto user) {
        userRepository.findByPassport(user.getPassport())
                      .ifPresent(it -> {
                          throw new EntityExistsException("user already exists");
                      });
        User savedUser = userRepository.save(mapper.addOrUpdateUserDtoToUser(user));
        return mapper.userToUserDto(savedUser);
    }

    @Transactional
    public UserDto updateUser(Long userId, AddOrUpdateUserDto user) {
        User foundUser = getUserEntity(userId);
        foundUser.setName(user.getName());
        foundUser.setLastName(user.getLastName());
        foundUser.setPassport(user.getPassport());
        return mapper.userToUserDto(foundUser);
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = getUserEntity(userId);
        user.setDeleted(true);
    }

    private User getUserEntity(Long userId) {
        return userRepository.findById(userId)
                             .orElseThrow(() -> new DataNotFoundException("User with id=" + userId + " not found"));
    }
}
