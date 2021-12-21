package com.epam.esm.service.impl;

import com.epam.esm.database.dao.impl.CertificateDaoImpl;
import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.database.entity.Certificate;
import com.epam.esm.database.entity.SearchData;
import com.epam.esm.database.entity.Tag;
import com.epam.esm.service.dto.CertificateTagDto;
import com.epam.esm.service.dto.certificate.CertificateDto;
import com.epam.esm.service.dto.SearchDataDto;
import com.epam.esm.service.dto.tag.TagDto;
import com.epam.esm.service.dto.certificate.CreateCertificateDto;
import com.epam.esm.service.dto.certificate.DeleteCertificateByIdDto;
import com.epam.esm.service.dto.certificate.UpdateCertificateDto;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;
import com.epam.esm.service.impl.CertificateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
public class CertificateServiceImplTest {
    @Mock
    private CertificateDaoImpl certificateDao;
    @InjectMocks
    private CertificateServiceImpl giftCertificateService;

    private CertificateTagDto certificateTagDto1;
    private CertificateTagDto certificateTagDto2;
    private CertificateTag certificateTag1;
    private CertificateTag certificateTag2;
    private CertificateDto certificateDto1;
    private Certificate certificate1;
    private CertificateDto certificateDto2;
    private Certificate certificate2;
    private SearchDataDto searchDataDto;

    @BeforeEach
    public void init() {
        certificateDto1 = new CertificateDto(
                1L,
                "certificate name 1",
                "certificate description 1",
                BigDecimal.valueOf(1),
                1,
                LocalDateTime.parse("2021-01-01T09:10:12.100"),
                LocalDateTime.parse("2021-01-01T09:10:12.200")
        );
        certificate1 = new Certificate(
                1L,
                "certificate name 1",
                "certificate description 1",
                BigDecimal.valueOf(1),
                1,
                LocalDateTime.parse("2021-01-01T09:10:12.100"),
                LocalDateTime.parse("2021-01-01T09:10:12.200")
        );
        certificateDto2 = new CertificateDto(
                2L,
                "certificate name 2",
                "certificate description 2",
                BigDecimal.valueOf(2),
                2,
                LocalDateTime.parse("2021-01-01T09:10:12.200"),
                LocalDateTime.parse("2021-01-01T09:10:12.300")
        );
        certificate2 = new Certificate(
                2L,
                "certificate name 2",
                "certificate description 2",
                BigDecimal.valueOf(2),
                2,
                LocalDateTime.parse("2021-01-01T09:10:12.200"),
                LocalDateTime.parse("2021-01-01T09:10:12.300")
        );
        TagDto tagDto1 = new TagDto(1L, "tag name 1");
        TagDto tagDto2 = new TagDto(2L, "tag name 2");
        TagDto tagDto3 = new TagDto(3L, "tag name 3");
        Tag tag1 = new Tag(1L, "tag name 1");
        Tag tag2 = new Tag(2L, "tag name 2");
        Tag tag3 = new Tag(3L, "tag name 3");
        certificateTagDto1 = new CertificateTagDto(certificateDto1, new ArrayList<>());
        certificateTagDto1.getTags().add(tagDto1);
        certificateTagDto1.getTags().add(tagDto3);
        certificateTagDto2 = new CertificateTagDto(certificateDto2, new ArrayList<>());
        certificateTagDto2.getTags().add(tagDto2);
        certificateTag1 = new CertificateTag(certificate1, new ArrayList<>());
        certificateTag1.getTags().add(tag1);
        certificateTag1.getTags().add(tag3);
        certificateTag2 = new CertificateTag(certificate2, new ArrayList<>());
        certificateTag2.getTags().add(tag2);
        searchDataDto = new SearchDataDto(
                "tag name 1",
                "1",
                "1",
                new ArrayList<>()
        );
        searchDataDto.getSortData().add(new SearchDataDto.SortDto("cte_ac.c_name", "ASC"));
        SearchData searchData = new SearchData(
                "tag name 1",
                "1",
                "1",
                new ArrayList<>()
        );
        searchData.getSortData().add(new SearchData.Sort("cte_ac.c_name", "ASC"));
    }

    @Test
    public void createTest() {
        CreateCertificateDto createGiftCertificateDto = new CreateCertificateDto(
                "certificate name 1", "certificate description 1", BigDecimal.valueOf(1), 1,
                LocalDateTime.parse("2021-01-01T09:10:12.100"), LocalDateTime.parse("2021-01-01T09:10:12.200")
        );
        Long expected = 1L;
        when(certificateDao.create(any(Certificate.class))).thenReturn(expected);
        Long actual = giftCertificateService.create(createGiftCertificateDto);
        assertEquals(expected, actual);
    }

    @Test
    public void readTest() {
        List<CertificateDto> expected = new ArrayList<>();
        expected.add(certificateDto1);
        expected.add(certificateDto2);
        List<Certificate> returnFromDao = new ArrayList<>();
        returnFromDao.add(certificate1);
        returnFromDao.add(certificate2);
        when((certificateDao.read())).thenReturn(returnFromDao);
        List<CertificateDto> actual = giftCertificateService.read();
        assertEquals(expected, actual);
    }

    @Test
    public void readByEmptySearchDataTest() {
        List<CertificateTagDto> expected = new ArrayList<>();
        expected.add(certificateTagDto1);
        expected.add(certificateTagDto2);
        List<CertificateTag> returnFromDao = new ArrayList<>();
        returnFromDao.add(certificateTag1);
        returnFromDao.add(certificateTag2);
        when(certificateDao.read(any(SearchData.class))).thenReturn(returnFromDao);
        List<CertificateTagDto> actual = giftCertificateService.read(new SearchDataDto());
        assertEquals(expected, actual);
    }

    @Test
    public void readBySearchDataTest() {
        List<CertificateTagDto> expected = new ArrayList<>();
        expected.add(certificateTagDto1);
        List<CertificateTag> returnFromDao = new ArrayList<>();
        returnFromDao.add(certificateTag1);
        when(certificateDao.read(any(SearchData.class))).thenReturn(returnFromDao);
        List<CertificateTagDto> actual = giftCertificateService.read(searchDataDto);
        assertEquals(expected, actual);
    }

    @Test
    public void updateTest() {
        UpdateCertificateDto updateCertificateDto = new UpdateCertificateDto(
                "certificate name 1", "certificate description 1", BigDecimal.valueOf(1), 1,
                LocalDateTime.parse("2021-01-01T09:10:12.100"), LocalDateTime.parse("2021-01-01T09:10:12.200")
        );
        certificateDto1.setId(null);
        when(certificateDao.update(eq(1L), any(Certificate.class))).thenReturn(true);
        boolean actual = giftCertificateService.update(1L, updateCertificateDto);
        assertTrue(actual);
    }

    @Test
    public void deleteTest() {
        when(certificateDao.delete(eq(1L))).thenReturn(true);
        boolean actual = giftCertificateService.delete(new DeleteCertificateByIdDto(1L));
        assertTrue(actual);
    }


}
