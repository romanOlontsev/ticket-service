package com.learn.ticketservice.service;

import com.learn.ticketservice.exception.EntityExistsException;
import com.learn.ticketservice.model.User;
import com.learn.ticketservice.repository.UserRepository;
import com.learn.ticketservice.service.dto.UserDto;
import com.learn.ticketservice.web.dto.AddUserDto;
import com.learn.ticketservice.web.dto.UpdateUserDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    @Spy
    private final MapperFacade mapper = mapperFactory.getMapperFacade();

    private List<User> userList;

    @BeforeEach
    void setUp() {
        User user1 = User.builder()
                .id(1L)
                .tickets(List.of())
                .name("Abdula")
                .lastname("Smith")
                .passport("332 222")
                .isDeleted(false)
                .build();
        User user2 = User.builder()
                .id(2L)
                .tickets(List.of())
                .name("Omar")
                .lastname("Dene")
                .passport("488 432")
                .isDeleted(true)
                .build();

        userList = List.of(user1, user2);
    }

    @Test
    void getUserByIdTest() {
        User user = userList.get(0);

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        UserDto userById = userService.getUserById(1L);

        assertThat(userById).isNotNull();
        assertThat(userById).isEqualTo(mapper.map(user, UserDto.class));
        assertThat(userById).extracting("passport").isEqualTo("332 222");
    }

    @Test
    void getUsersTest() {
        List<User> users = userList;

        when(userRepository.findAll()).thenReturn(users);
        List<UserDto> userServiceUsers = userService.getUsers();

        assertThat(userServiceUsers).isNotNull();
        assertThat(userServiceUsers).hasSize(2);
        assertThat(userServiceUsers.get(1)).extracting("isDeleted").isEqualTo(true);

    }

    @Test
    void addNewUserTest() {
        AddUserDto addUserDto = AddUserDto.builder()
                .name("Omar")
                .lastname("Dene")
                .passport("488 432")
                .build();
        User user = userList.get(1);

        when(userRepository.save(any())).thenReturn(user);
        UserDto userDto = userService.addNewUser(addUserDto);

        assertThat(userDto).isNotNull();
        assertThat(userDto).extracting("name").isEqualTo("Omar");
    }

    @Test
    void addNewUserTest_shouldThrowException() {
        AddUserDto addUserDto = AddUserDto.builder()
                .name("Omar")
                .lastname("Dene")
                .passport("488 432")
                .build();
        User user = userList.get(1);

        when(userRepository.findUserByPassport(anyString()))
                .thenReturn(Optional.of(user));

        assertThatThrownBy(() -> userService.addNewUser(addUserDto)).isInstanceOf(EntityExistsException.class);
    }

    @Test
    void updateUserTest() {
        UpdateUserDto updateUserDto = UpdateUserDto.builder()
                .name("Abdula")
                .lastname("Smith")
                .passport("332 222")
                .build();
        User user = userList.get(1);

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        UserDto updateUser = userService.updateUser(1L, updateUserDto);

        assertThat(updateUser).isNotNull();
        assertThat(updateUser).extracting("passport").isEqualTo("332 222");
    }

    @Test
    void deleteUserTest() {
        User user = userList.get(0);

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        userService.deleteUser(1L);

        assertThat(user.isDeleted()).isEqualTo(true);
    }
}