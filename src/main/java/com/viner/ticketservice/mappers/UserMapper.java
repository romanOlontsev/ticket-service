package com.viner.ticketservice.mappers;

import com.viner.ticketservice.dto.AddOrUpdateUserDto;
import com.viner.ticketservice.dto.UserDto;
import com.viner.ticketservice.dto.UserWithoutTicketDto;
import com.viner.ticketservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto user);

    AddOrUpdateUserDto userToAddOrUpdateUserDto(User user);

    User addOrUpdateUserDtoToUser(AddOrUpdateUserDto user);

    User userWithoutTicketDtoToUser(UserWithoutTicketDto userWithoutTicketDto);

    List<UserDto> userListToUserDtoList(List<User> users);

    List<UserWithoutTicketDto> userListToUserWithoutTicketDtoList(List<User> users);

    void updateUserFromAddOrUpdateUserDto(AddOrUpdateUserDto addOrUpdateUserDto, @MappingTarget User user);
}
