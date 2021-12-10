package com.epam.esm.controller;

import com.epam.esm.controller.exception.EntityAlreadyExistsControllerException;
import com.epam.esm.controller.exception.EntityIsNotValidControllerException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/tags", produces = MediaType.APPLICATION_JSON_VALUE)
public class TagController {
    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> createTag(@RequestBody TagDto tagDto) throws EntityAlreadyExistsControllerException, EntityIsNotValidControllerException {
        try {
            Long result = tagService.createTag(tagDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (EntityAlreadyExistsServiceException exception) {
            throw new EntityAlreadyExistsControllerException(exception);
        } catch (EntityIsNotValidServiceException exception) {
            throw new EntityIsNotValidControllerException(exception);
        }

    }

    @GetMapping
    public ResponseEntity<List<TagDto>> findTags() {
        List<TagDto> tags = tagService.findTags();
        return !tags.isEmpty() ? ResponseEntity.ok(tags) : ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Boolean> deleteTag(@RequestBody TagDto tagDto) throws EntityIsNotValidControllerException {
        try {
            return ResponseEntity.ok(tagService.deleteTag(tagDto));
        } catch (EntityIsNotValidServiceException exception) {
            throw new EntityIsNotValidControllerException(exception);
        }
    }
}
