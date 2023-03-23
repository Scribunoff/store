package com.example.store.mapper;

import com.example.store.domain.User;
import com.example.store.dto.UserDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
}
