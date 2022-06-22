package com.learn.ticketservice.service;

import com.learn.ticketservice.exception.DataNotFoundException;
import com.learn.ticketservice.exception.EntityExistsException;
import com.learn.ticketservice.model.User;
import com.learn.ticketservice.repository.UserRepository;
import com.learn.ticketservice.service.dto.UserDto;
import com.learn.ticketservice.web.dto.AddUserDto;
import com.learn.ticketservice.web.dto.UpdateUserDto;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MapperFacade mapper;

    @Transactional
    public UserDto getUserById(Long id) {
        return mapper.map(getUserEntity(id), UserDto.class);
    }

    @Transactional
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(it -> mapper.map(it, UserDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDto addNewUser(AddUserDto user) {
        userRepository.findUserByPassport(user.getPassport())
                .ifPresent(s -> {
                    throw new EntityExistsException("user already exists");
                });
        User saveUser = userRepository.save(mapper.map(user, User.class));
        return mapper.map(saveUser, UserDto.class);
    }

    @Transactional
    public UserDto updateUser(Long userId,
                           UpdateUserDto updateUserDto) {
        User user = getUserEntity(userId);
        mapper.map(updateUserDto, user);
        return mapper.map(user, UserDto.class);
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = getUserEntity(userId);
        user.setDeleted(true);
    }

    public User getUserEntity(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new DataNotFoundException("user with id " + userId + " does not exists"));
    }
}
