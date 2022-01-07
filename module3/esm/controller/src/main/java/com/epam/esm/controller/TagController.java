package com.epam.esm.controller;

import com.epam.esm.controller.exception.EntityAlreadyExistsControllerException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.tag.*;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createTag(@RequestBody @Valid CreateTagDto createTagDto) throws EntityAlreadyExistsControllerException {
        try {
            Long result = tagService.save(createTagDto);
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
    @GetMapping
    public ResponseEntity<List<TagDto>> findTags() {
        List<TagDto> tags = tagService.findAll();
        return !tags.isEmpty() ? ResponseEntity.ok(tags) : ResponseEntity.noContent().build();
    }

    @Profile(value = "development")
    // TODO delete, this is just test example
    @GetMapping(path = "/name")
    public ResponseEntity<List<TagDto>> findTagsByName(@RequestBody @Valid ReadTagByNameDto readTagByNameDto) {
        List<TagDto> tags = tagService.findByName(readTagByNameDto);
        return !tags.isEmpty() ? ResponseEntity.ok(tags) : ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/search/most/used")
    public ResponseEntity<List<TagCostDto>> readMostWidelyUsedTag(@RequestBody @Valid ReadMostWidelyUsedTagDto readMostWidelyUsedTagDto) {
        List<TagCostDto> tags = tagService.read(readMostWidelyUsedTagDto);
        return !tags.isEmpty() ? ResponseEntity.ok(tags) : ResponseEntity.noContent().build();
    }

    /**
     * Deletes tag in the com.epam.esm.database with specified id
     *
     * @param deleteTagByIdDto dto object for tag id
     * @return
     */
    //TODO return boolean value
    @DeleteMapping(path = "/delete/id")
    public ResponseEntity<Boolean> deleteTagById(@RequestBody @Valid DeleteTagByIdDto deleteTagByIdDto) {
        tagService.deleteById(deleteTagByIdDto);
        return ResponseEntity.ok(true);
    }

    /**
     * Deletes tag in the com.epam.esm.database with specified id
     *
     * @param deleteTagByNameDto dto object for tag id
     * @return
     */
    //TODO return boolean value
    @DeleteMapping(path = "/delete/name")
    public ResponseEntity<Boolean> deleteTagByName(@RequestBody @Valid DeleteTagByNameDto deleteTagByNameDto) {
        tagService.deleteByName(deleteTagByNameDto);
        return ResponseEntity.ok(true);
    }

}
