package com.epam.esm.service.impl;

import com.epam.esm.database.dao.UserDao;
import com.epam.esm.database.exception.EntityAlreadyExistsDaoException;
import com.epam.esm.service.UserService;
import com.epam.esm.service.dto.user.CreateUserDto;
import com.epam.esm.service.dto.user.PublicUserDto;
import com.epam.esm.service.dto.user.ReadPublicUserByNameDto;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;
import com.epam.esm.service.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public Long create(CreateUserDto createUserDto) throws EntityAlreadyExistsServiceException {
        try {
            return userDao.create(createUserDto.getUsername(), createUserDto.getPassword());
        } catch (EntityAlreadyExistsDaoException e) {
            throw new EntityAlreadyExistsServiceException(e);
        }
    }

    @Override
    public List<PublicUserDto> read(ReadPublicUserByNameDto readPublicUserDto) {
        return UserMapper.toDto(userDao.read(readPublicUserDto.getUsername()));
    }
}
