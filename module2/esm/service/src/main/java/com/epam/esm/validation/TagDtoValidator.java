package com.epam.esm.validation;

import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;

public class TagDtoValidator {

    private static boolean validateInternal(String name) {
        return name != null;
    }

    private static boolean validateInternal(Long id) {
        return id != null;
    }

    public static String validate(String name) throws EntityIsNotValidServiceException {
        if (validateInternal(name)) {
            return name;
        } else {
            throw new EntityIsNotValidServiceException("Entity TagDto name=" + name + " is not valid.");
        }
    }

    public static Long validate(Long tagId) throws EntityIsNotValidServiceException {
        if (validateInternal(tagId)) {
            return tagId;
        } else {
            throw new EntityIsNotValidServiceException("Entity TagDto id=" + tagId + " is not valid.");
        }
    }

    public static TagDto validate(TagDto tagDto) throws EntityIsNotValidServiceException {
        if (tagDto != null && validateInternal(tagDto.getId()) && validateInternal(tagDto.getName())) {
            return tagDto;
        } else {
            throw new EntityIsNotValidServiceException("Entity TagDto " + tagDto + " is not valid.");
        }
    }



}
