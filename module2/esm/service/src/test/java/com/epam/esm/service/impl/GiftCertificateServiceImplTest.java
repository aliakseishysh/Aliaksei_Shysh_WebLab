package com.epam.esm.service.impl;

import com.epam.esm.database.dao.impl.CertificateDaoImpl;
import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.database.entity.GiftCertificate;
import com.epam.esm.database.entity.SearchData;
import com.epam.esm.database.entity.Tag;
import com.epam.esm.service.dto.CertificateTagDto;
import com.epam.esm.service.dto.GiftCertificateDto;
import com.epam.esm.service.dto.SearchDataDto;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;
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

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GiftCertificateServiceImplTest {
    @Mock
    private CertificateDaoImpl certificateDao;
    @InjectMocks
    private GiftCertificateServiceImpl giftCertificateService;

    private CertificateTagDto certificateTagDto1;
    private CertificateTagDto certificateTagDto2;
    private CertificateTag certificateTag1;
    private CertificateTag certificateTag2;
    private GiftCertificateDto giftCertificateDto1;
    private GiftCertificate giftCertificate1;
    private GiftCertificateDto giftCertificateDto2;
    private GiftCertificate giftCertificate2;
    private SearchDataDto searchDataDto;

    @BeforeEach
    public void init() {
        giftCertificateDto1 = new GiftCertificateDto(
                1L,
                "certificate name 1",
                "certificate description 1",
                BigDecimal.valueOf(1),
                1,
                LocalDateTime.parse("2021-01-01T09:10:12.100"),
                LocalDateTime.parse("2021-01-01T09:10:12.200")
        );
        giftCertificate1 = new GiftCertificate(
                1L,
                "certificate name 1",
                "certificate description 1",
                BigDecimal.valueOf(1),
                1,
                LocalDateTime.parse("2021-01-01T09:10:12.100"),
                LocalDateTime.parse("2021-01-01T09:10:12.200")
        );
        giftCertificateDto2 = new GiftCertificateDto(
                2L,
                "certificate name 2",
                "certificate description 2",
                BigDecimal.valueOf(2),
                2,
                LocalDateTime.parse("2021-01-01T09:10:12.200"),
                LocalDateTime.parse("2021-01-01T09:10:12.300")
        );
        giftCertificate2 = new GiftCertificate(
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
        certificateTagDto1 = new CertificateTagDto(giftCertificateDto1, new ArrayList<>());
        certificateTagDto1.getTags().add(tagDto1);
        certificateTagDto1.getTags().add(tagDto3);
        certificateTagDto2 = new CertificateTagDto(giftCertificateDto2, new ArrayList<>());
        certificateTagDto2.getTags().add(tagDto2);
        certificateTag1 = new CertificateTag(giftCertificate1, new ArrayList<>());
        certificateTag1.getTags().add(tag1);
        certificateTag1.getTags().add(tag3);
        certificateTag2 = new CertificateTag(giftCertificate2, new ArrayList<>());
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
    public void createTest() throws EntityIsNotValidServiceException {
        Long expected = 1L;
        when(certificateDao.create(any(GiftCertificate.class))).thenReturn(expected);
        Long actual = giftCertificateService.create(giftCertificateDto1);
        assertEquals(expected, actual);
    }

    @Test
    public void readTest() {
        List<GiftCertificateDto> expected = new ArrayList<>();
        expected.add(giftCertificateDto1);
        expected.add(giftCertificateDto2);
        List<GiftCertificate> returnFromDao = new ArrayList<>();
        returnFromDao.add(giftCertificate1);
        returnFromDao.add(giftCertificate2);
        when((certificateDao.read())).thenReturn(returnFromDao);
        List<GiftCertificateDto> actual = giftCertificateService.read();
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
    public void updateTest() throws EntityIsNotValidServiceException {
        boolean expected = true;
        giftCertificateDto1.setId(null);
        when(certificateDao.update(eq(1L), any(GiftCertificate.class))).thenReturn(true);
        boolean actual = giftCertificateService.update(1L, giftCertificateDto1);
        assertEquals(true, actual);
    }

    @Test
    public void deleteTest() throws EntityIsNotValidServiceException {
        boolean expected = true;
        when(certificateDao.delete(eq(1L))).thenReturn(true);
        boolean actual = giftCertificateService.delete(new GiftCertificateDto(1L, null, null, null, null, null, null));
        assertEquals(true, actual);
    }


}
