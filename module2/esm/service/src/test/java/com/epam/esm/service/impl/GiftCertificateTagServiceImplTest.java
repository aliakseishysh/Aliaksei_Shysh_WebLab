package com.epam.esm.service.impl;

import com.epam.esm.database.dao.TagCertificateDao;
import com.epam.esm.database.dao.impl.CertificateDaoImpl;
import com.epam.esm.database.dao.impl.TagCertificateDaoImpl;
import com.epam.esm.database.dao.impl.TagDaoImpl;
import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.database.entity.GiftCertificate;
import com.epam.esm.database.entity.Tag;
import com.epam.esm.database.exception.EntityAlreadyExistsDaoException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.CertificateTagDto;
import com.epam.esm.service.dto.GiftCertificateDto;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.security.cert.Certificate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GiftCertificateTagServiceImplTest {

    @Mock
    private CertificateDaoImpl certificateDao;
    @Mock
    private TagCertificateDaoImpl tagCertificateDao;
    @Mock
    private TagServiceImpl tagService;
    @Mock
    private GiftCertificateServiceImpl giftCertificateService;
    @InjectMocks
    private GiftCertificateTagServiceImpl giftCertificateTagService;


    private GiftCertificateDto giftCertificateDto;
    private GiftCertificate giftCertificate;
    private CertificateTagDto certificateTagDto;
    private CertificateTag certificateTag;
    private TagDto tagDto1;
    private TagDto tagDto2;
    private Tag tag1;
    private Tag tag2;

    @BeforeEach
    public void init() {
        giftCertificateDto = new GiftCertificateDto(
                1L,
                "certificate name 1",
                "certificate description 1",
                BigDecimal.valueOf(1),
                1,
                LocalDateTime.parse("2021-01-01T09:10:12.100"),
                LocalDateTime.parse("2021-01-01T09:10:12.200")
        );
        giftCertificate = new GiftCertificate(
                1L,
                "certificate name 1",
                "certificate description 1",
                BigDecimal.valueOf(1),
                1,
                LocalDateTime.parse("2021-01-01T09:10:12.100"),
                LocalDateTime.parse("2021-01-01T09:10:12.200")
        );
        List<TagDto> tagDtoList = new ArrayList<>();
        tagDto1 = new TagDto(null, "tag name 1");
        tagDto2 = new TagDto(null, "tag name 2");
        tagDtoList.add(tagDto1);
        tagDtoList.add(tagDto2);
        List<Tag> tagList = new ArrayList<>();
        tag1 = new Tag(1L, "tag name 1");
        tag2 = new Tag(2L, "tag name 2");
        tagList.add(tag1);
        tagList.add(tag2);
        certificateTagDto = new CertificateTagDto(giftCertificateDto, tagDtoList);
        certificateTag = new CertificateTag(giftCertificate, tagList);
    }


    @Test
    public void createTest() throws EntityIsNotValidServiceException {
        Long expected = 1L;
        when(giftCertificateService.create(any())).thenReturn(expected);
        Long actual = giftCertificateTagService.create(certificateTagDto);
        assertEquals(expected, actual);
    }

    @Test
    public void updateTest() throws EntityIsNotValidServiceException {
        boolean expected = true;
        when(giftCertificateService.update(eq(1L), any())).thenReturn(expected);
        boolean actual = giftCertificateTagService.update(1L, certificateTagDto);
        assertEquals(expected, actual);
    }


}
