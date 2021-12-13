package com.epam.esm.service.impl;

import com.epam.esm.database.dao.TagDao;
import com.epam.esm.database.entity.Tag;
import com.epam.esm.database.exception.EntityAlreadyExistsDaoException;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TagServiceImplTest {
    @Mock
    private TagDao tagDao;
    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    public void readTest() {
        List<Tag> daoReturnTags = new ArrayList<>();
        daoReturnTags.add(new Tag(1L, "tag name 1"));
        daoReturnTags.add(new Tag(2L, "tag name 2"));
        List<TagDto> expected = new ArrayList<>();
        expected.add(new TagDto(1L, "tag name 1"));
        expected.add(new TagDto(2L, "tag name 2"));
        doReturn(daoReturnTags).when(tagDao).read();
        List<TagDto> actual = tagService.read();
        assertEquals(expected, actual);
    }

    @Test
    public void readByTagDtoTest() throws EntityIsNotValidServiceException {
        TagDto tagDto = new TagDto(null, "tag name 1");
        Tag daoReadTag = new Tag(null, "tag name 1");
        Tag daoReturnTag = new Tag(1L, "tag name 1");
        List<Tag> daoReturnTagList = new ArrayList<>();
        daoReturnTagList.add(daoReturnTag);
        List<TagDto> expected = new ArrayList<>();
        expected.add(new TagDto(1L, "tag name 1"));
        doReturn(daoReturnTagList).when(tagDao).read(daoReadTag);
        List<TagDto> actual = tagService.read(tagDto);
        assertEquals(expected, actual);
    }

    @Test
    public void createTagTest() throws EntityAlreadyExistsServiceException, EntityIsNotValidServiceException, EntityAlreadyExistsDaoException {
        Long expected = 1L;
        when(tagDao.create(any())).thenReturn(expected);
        long actual = tagService.createTag(new TagDto(null, "tag name 1"));
        assertEquals(expected, actual);
    }

    @Test
    public void deleteTagTest() throws EntityIsNotValidServiceException {
        boolean expected = true;
        when(tagDao.delete(1L)).thenReturn(true);
        boolean actual = tagService.deleteTag(new TagDto(1L, null));
        assertTrue(actual);
    }


}
