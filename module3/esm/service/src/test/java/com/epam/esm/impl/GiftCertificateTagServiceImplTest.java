package com.epam.esm.impl;

import com.epam.esm.database.dao.impl.CertificateDaoImpl;
import com.epam.esm.database.dao.impl.TagCertificateDaoImpl;
import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.database.entity.GiftCertificate;
import com.epam.esm.database.entity.Tag;
import com.epam.esm.service.dto.CertificateTagDto;
import com.epam.esm.service.dto.CreateGiftCertificateDto;
import com.epam.esm.service.dto.GiftCertificateDto;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.dto.certificate.CreateUpdateCertificateTagDto;
import com.epam.esm.service.dto.tag.CreateTagDto;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import com.epam.esm.service.impl.GiftCertificateTagServiceImpl;
import com.epam.esm.service.impl.TagServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

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


    private CertificateTagDto certificateTagDto;

    @BeforeEach
    public void init() {
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto(
                1L,
                "certificate name 1",
                "certificate description 1",
                BigDecimal.valueOf(1),
                1,
                LocalDateTime.parse("2021-01-01T09:10:12.100"),
                LocalDateTime.parse("2021-01-01T09:10:12.200")
        );
        GiftCertificate giftCertificate = new GiftCertificate(
                1L,
                "certificate name 1",
                "certificate description 1",
                BigDecimal.valueOf(1),
                1,
                LocalDateTime.parse("2021-01-01T09:10:12.100"),
                LocalDateTime.parse("2021-01-01T09:10:12.200")
        );
        List<TagDto> tagDtoList = new ArrayList<>();
        TagDto tagDto1 = new TagDto(null, "tag name 1");
        TagDto tagDto2 = new TagDto(null, "tag name 2");
        tagDtoList.add(tagDto1);
        tagDtoList.add(tagDto2);
        List<Tag> tagList = new ArrayList<>();
        Tag tag1 = new Tag(1L, "tag name 1");
        Tag tag2 = new Tag(2L, "tag name 2");
        tagList.add(tag1);
        tagList.add(tag2);
        certificateTagDto = new CertificateTagDto(giftCertificateDto, tagDtoList);
        CertificateTag certificateTag = new CertificateTag(giftCertificate, tagList);
    }


    @Test
    public void createTest() throws EntityIsNotValidServiceException {
        List<CreateTagDto> tagsDto = new ArrayList<>();
        tagsDto.add(new CreateTagDto("tag name 1"));
        CreateUpdateCertificateTagDto createUpdateCertificateTagDto = new CreateUpdateCertificateTagDto(
                new CreateGiftCertificateDto(
                        "certificate name 1", "certificate description 1", BigDecimal.valueOf(1), 1,
                        LocalDateTime.parse("2021-01-01T09:10:12.100"), LocalDateTime.parse("2021-01-01T09:10:12.200")
                ),
                tagsDto
        );
        Long expected = 1L;
        when(giftCertificateService.create(any())).thenReturn(expected);
        Long actual = giftCertificateTagService.create(createUpdateCertificateTagDto);
        assertEquals(expected, actual);
    }

    @Test
    public void updateTest() throws EntityIsNotValidServiceException {
        boolean expected = true;
        when(giftCertificateService.update(eq(1L), any())).thenReturn(true);
        boolean actual = giftCertificateTagService.update(1L, certificateTagDto);
        assertTrue(actual);
    }


}
