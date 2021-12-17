package com.epam.esm.controller;

import com.epam.esm.controller.exception.EntityAlreadyExistsControllerException;
import com.epam.esm.controller.exception.EntityIsNotValidControllerException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.dto.tag.CreateTagDto;
import com.epam.esm.service.dto.tag.DeleteTagByIdDto;
import com.epam.esm.service.dto.tag.DeleteTagByNameDto;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;
import com.epam.esm.service.util.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
@RequestMapping(value = "/tags", produces = MediaType.APPLICATION_JSON_VALUE)
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Creates new tag with specified parameters
     *
     * @param createTagDto dto object for tag entity
     * @return {@long} id of created object
     * @throws EntityAlreadyExistsControllerException if entity already exists in the com.epam.esm.database
     * @throws EntityIsNotValidControllerException    if {@tagDto} object is not valid
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createTag(@RequestBody @Valid CreateTagDto createTagDto) throws EntityAlreadyExistsControllerException, EntityIsNotValidControllerException {
        try {
            Long result = tagService.createTag(createTagDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (EntityAlreadyExistsServiceException | EntityIsNotValidServiceException exception) {
            throw new EntityAlreadyExistsControllerException(exception);
        }

    }

    /**
     * Reads all tags from com.epam.esm.database
     *
     * @return {@code List<TagDto>} with tags from com.epam.esm.database
     */
    @GetMapping
    public ResponseEntity<List<TagDto>> findTags() {
        List<TagDto> tags = tagService.read();
        return !tags.isEmpty() ? ResponseEntity.ok(tags) : ResponseEntity.noContent().build();
    }

    /**
     * Deletes tag in the com.epam.esm.database with specified id
     *
     * @param deleteTagByIdDto dto object for tag id
     * @return
     * @throws EntityIsNotValidControllerException
     */
    @DeleteMapping(path = "/delete/id")
    public ResponseEntity<Boolean> deleteTagById(@RequestBody @Valid DeleteTagByIdDto deleteTagByIdDto) throws EntityIsNotValidControllerException {
        try {
            return ResponseEntity.ok(tagService.deleteTag(deleteTagByIdDto));
        } catch (EntityIsNotValidServiceException exception) {
            throw new EntityIsNotValidControllerException(exception);
        }
    }

    /**
     * Deletes tag in the com.epam.esm.database with specified id
     *
     * @param deleteTagByNameDto dto object for tag id
     * @return
     * @throws EntityIsNotValidControllerException
     */
    @DeleteMapping(path = "/delete/name")
    public ResponseEntity<Boolean> deleteTagByName(@RequestBody @Valid DeleteTagByNameDto deleteTagByNameDto) throws EntityIsNotValidControllerException {
        try {
            return ResponseEntity.ok(tagService.deleteTag(deleteTagByNameDto));
        } catch (EntityIsNotValidServiceException exception) {
            throw new EntityIsNotValidControllerException(exception);
        }
    }

}
