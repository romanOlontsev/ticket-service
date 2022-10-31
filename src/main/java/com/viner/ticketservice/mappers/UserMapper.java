package com.viner.ticketservice.mappers;

import com.viner.ticketservice.entity.User;
import com.viner.ticketservice.dto.AddOrUpdateUserDto;
import com.viner.ticketservice.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto user);

    AddOrUpdateUserDto userToAddOrUpdateUserDto(User user);

    User addOrUpdateUserDtoToUser(AddOrUpdateUserDto user);
}
