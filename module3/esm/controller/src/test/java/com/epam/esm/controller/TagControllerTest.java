package com.epam.esm.controller;

import com.epam.esm.controller.TagController;
import com.epam.esm.controller.exception.EntityAlreadyExistsControllerException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.tag.TagDto;
import com.epam.esm.service.dto.tag.CreateTagDto;
import com.epam.esm.service.dto.tag.DeleteTagByIdDto;
import com.epam.esm.service.dto.tag.DeleteTagByNameDto;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TagControllerTest {
    @Mock
    private TagService tagService;
    @InjectMocks
    private TagController tagController;

    @Test
    public void createTagTest() throws EntityAlreadyExistsServiceException, EntityAlreadyExistsControllerException  {
        CreateTagDto expected1 = new CreateTagDto("tag name 1");
        Long expected2 = 1L;
        when(tagService.createTag(any())).thenReturn(expected2);
        ResponseEntity<Long> actual2 = tagController.createTag(expected1);
        ArgumentCaptor<CreateTagDto> argumentCaptor = ArgumentCaptor.forClass(CreateTagDto.class);
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
    public void deleteTagByIdTest() {
        DeleteTagByIdDto deleteTagByIdDto = new DeleteTagByIdDto(1L);
        doReturn(true).when(tagService).deleteTag(deleteTagByIdDto);
        ResponseEntity<Boolean> actual = tagController.deleteTagById(deleteTagByIdDto);
        assertEquals(true, actual.getBody());
    }

    @Test
    public void deleteTagByNameTest() {
        DeleteTagByNameDto deleteTagByIdDto = new DeleteTagByNameDto("tag name 1");
        doReturn(true).when(tagService).deleteTag(deleteTagByIdDto);
        ResponseEntity<Boolean> actual = tagController.deleteTagByName(deleteTagByIdDto);
        assertEquals(true, actual.getBody());
    }


}
