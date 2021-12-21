package com.epam.esm.controller;

import com.epam.esm.controller.exception.EntityAlreadyExistsControllerException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.UserService;
import com.epam.esm.service.dto.tag.CreateTagDto;
import com.epam.esm.service.dto.tag.DeleteTagByIdDto;
import com.epam.esm.service.dto.tag.DeleteTagByNameDto;
import com.epam.esm.service.dto.tag.TagDto;
import com.epam.esm.service.dto.user.CreateUserDto;
import com.epam.esm.service.dto.user.PublicUserDto;
import com.epam.esm.service.dto.user.ReadPublicUserByNameDto;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates new tag with specified parameters
     *
     * @param createUserDto dto object for tag entity
     * @return {@long} id of created object
     * @throws EntityAlreadyExistsControllerException if entity already exists in the com.epam.esm.database
     */
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createUser(@RequestBody @Valid CreateUserDto createUserDto) throws EntityAlreadyExistsControllerException {
        try {
            Long result = userService.create(createUserDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (EntityAlreadyExistsServiceException exception) {
            throw new EntityAlreadyExistsControllerException(exception);
        }
    }

    /**
     * Reads all tags from com.epam.esm.database
     *
     * @return {@code List<TagDto>} with tags from com.epam.esm.database
     */
    @GetMapping("/search")
    public ResponseEntity<PublicUserDto> readUserPublicInfo(@RequestBody @Valid ReadPublicUserByNameDto readPublicUserDto) {
        List<PublicUserDto> publicUserDtoList = userService.read(readPublicUserDto);
        return !publicUserDtoList.isEmpty() ? ResponseEntity.ok(publicUserDtoList.get(0)) : ResponseEntity.noContent().build();
    }

}
