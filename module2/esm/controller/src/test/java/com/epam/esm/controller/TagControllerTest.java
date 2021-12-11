package com.epam.esm.controller;

import com.epam.esm.controller.exception.EntityAlreadyExistsControllerException;
import com.epam.esm.controller.exception.EntityIsNotValidControllerException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TagControllerTest {
    @Mock
    private TagService tagService;
    @InjectMocks
    private TagController tagController;

    @Test
    public void createTagTest() throws EntityAlreadyExistsServiceException, EntityIsNotValidServiceException, EntityAlreadyExistsControllerException, EntityIsNotValidControllerException {
        TagDto expected1 = new TagDto(null, "tag name 1");
        Long expected2 = 1L;
        when(tagService.createTag(any())).thenReturn(expected2);
        ResponseEntity<Long> actual2 = tagController.createTag(expected1);
        ArgumentCaptor<TagDto> argumentCaptor = ArgumentCaptor.forClass(TagDto.class);
        verify(tagService).createTag(argumentCaptor.capture());
        assertEquals(expected1, argumentCaptor.getValue());
        assertEquals(expected2, actual2.getBody());
    }

    @Test
    public void findTagsTest() {
        List<TagDto> expected = new ArrayList<>();
        expected.add(new TagDto(1L, "tag name 1"));
        expected.add(new TagDto(2L, "tag name 2"));
        doReturn(expected).when(tagService).read();
        ResponseEntity<List<TagDto>> responseEntity = tagController.findTags();
        assertEquals(expected, responseEntity.getBody());
    }

    @Test
    public void deleteTagTest() throws EntityIsNotValidServiceException, EntityIsNotValidControllerException {
        TagDto tagDto = new TagDto(1L, null);
        Boolean expected = true;
        doReturn(expected).when(tagService).deleteTag(tagDto);
        ResponseEntity<Boolean> actual = tagController.deleteTag(tagDto);
        assertEquals(expected, actual.getBody());
    }


}
