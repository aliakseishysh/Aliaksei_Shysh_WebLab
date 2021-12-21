package com.epam.esm.service.util;

import com.epam.esm.database.entity.SearchData;
import com.epam.esm.database.entity.User;
import com.epam.esm.service.dto.SearchDataDto;
import com.epam.esm.service.dto.user.PublicUserDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static PublicUserDto toDto(User user) {
        return new PublicUserDto(
                user.getId(),
                user.getUsername()
        );
    }

    public static List<PublicUserDto> toDto(List<User> users) {
        return users.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

}
