package com.epam.esm.service.impl;

import com.epam.esm.database.dao.impl.CertificateDaoImpl;
import com.epam.esm.database.dao.impl.TagCertificateDaoImpl;
import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.database.entity.Certificate;
import com.epam.esm.database.entity.Tag;
import com.epam.esm.service.dto.CertificateTagDto;
import com.epam.esm.service.dto.certificate.CertificateDto;
import com.epam.esm.service.dto.tag.TagDto;
import com.epam.esm.service.dto.certificate.CreateCertificateDto;
import com.epam.esm.service.dto.certificate.CreateCertificateTagDto;
import com.epam.esm.service.dto.certificate.UpdateCertificateDto;
import com.epam.esm.service.dto.certificate.UpdateCertificateTagDto;
import com.epam.esm.service.dto.tag.CreateTagDto;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.epam.esm.service.impl.CertificateServiceImpl;
import com.epam.esm.service.impl.CertificateTagServiceImpl;
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
public class CertificateTagServiceImplTest {
//
//    @Mock
//    private CertificateDaoImpl certificateDao;
//    @Mock
//    private TagCertificateDaoImpl tagCertificateDao;
//    @Mock
//    private TagServiceImpl tagService;
//    @Mock
//    private CertificateServiceImpl giftCertificateService;
//    @InjectMocks
//    private CertificateTagServiceImpl giftCertificateTagService;
//
//
//    private CertificateTagDto certificateTagDto;
//
//    @BeforeEach
//    public void init() {
//        CertificateDto certificateDto = new CertificateDto(
//                1L,
//                "certificate name 1",
//                "certificate description 1",
//                BigDecimal.valueOf(1),
//                1,
//                LocalDateTime.parse("2021-01-01T09:10:12.100"),
//                LocalDateTime.parse("2021-01-01T09:10:12.200")
//        );
//        Certificate certificate = new Certificate(
//                1L,
//                "certificate name 1",
//                "certificate description 1",
//                BigDecimal.valueOf(1),
//                1,
//                LocalDateTime.parse("2021-01-01T09:10:12.100"),
//                LocalDateTime.parse("2021-01-01T09:10:12.200")
//        );
//        List<TagDto> tagDtoList = new ArrayList<>();
//        TagDto tagDto1 = new TagDto(null, "tag name 1");
//        TagDto tagDto2 = new TagDto(null, "tag name 2");
//        tagDtoList.add(tagDto1);
//        tagDtoList.add(tagDto2);
//        List<Tag> tagList = new ArrayList<>();
//        Tag tag1 = new Tag(1L, "tag name 1");
//        Tag tag2 = new Tag(2L, "tag name 2");
//        tagList.add(tag1);
//        tagList.add(tag2);
//        certificateTagDto = new CertificateTagDto(certificateDto, tagDtoList);
//        CertificateTag certificateTag = new CertificateTag(certificate, tagList);
//    }
//
//
//    @Test
//    public void createTest() {
//        List<CreateTagDto> tagsDto = new ArrayList<>();
//        tagsDto.add(new CreateTagDto("tag name 1"));
//        CreateCertificateTagDto createUpdateCertificateTagDto = new CreateCertificateTagDto(
//                new CreateCertificateDto(
//                        "certificate name 1", "certificate description 1", BigDecimal.valueOf(1), 1,
//                        LocalDateTime.parse("2021-01-01T09:10:12.100"), LocalDateTime.parse("2021-01-01T09:10:12.200")
//                ),
//                tagsDto
//        );
//        Long expected = 1L;
//        when(giftCertificateService.create(any())).thenReturn(expected);
//        Long actual = giftCertificateTagService.create(createUpdateCertificateTagDto);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void updateTest() {
//        List<CreateTagDto> tagsDto = new ArrayList<>();
//        tagsDto.add(new CreateTagDto("tag name 1"));
//        UpdateCertificateTagDto updateCertificateTagDto = new UpdateCertificateTagDto(
//                1L,
//                new UpdateCertificateDto(
//                        "certificate name 1", "certificate description 1", BigDecimal.valueOf(1), 1,
//                        LocalDateTime.parse("2021-01-01T09:10:12.100"), LocalDateTime.parse("2021-01-01T09:10:12.200")
//                ),
//                tagsDto
//        );
//        when(giftCertificateService.update(eq(1L), any())).thenReturn(true);
//        boolean actual = giftCertificateTagService.update(updateCertificateTagDto);
//        assertTrue(actual);
//    }


}
