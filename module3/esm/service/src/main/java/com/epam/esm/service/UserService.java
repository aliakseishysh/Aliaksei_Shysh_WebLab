package com.epam.esm.service;

import com.epam.esm.service.dto.user.CreateUserDto;
import com.epam.esm.service.dto.user.PublicUserDto;
import com.epam.esm.service.dto.user.ReadPublicUserByNameDto;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;

import java.util.List;

public interface UserService {
    Long create(CreateUserDto createUserDto) throws EntityAlreadyExistsServiceException;

    List<PublicUserDto> read(ReadPublicUserByNameDto readPublicUserDto);
}
