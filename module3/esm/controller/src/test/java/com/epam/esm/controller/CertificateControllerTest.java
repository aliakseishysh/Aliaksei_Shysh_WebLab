package com.epam.esm.controller;

import com.epam.esm.controller.CertificateController;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.CertificateTagService;
import com.epam.esm.service.dto.CertificateTagDto;
import com.epam.esm.service.dto.certificate.CertificateDto;
import com.epam.esm.service.dto.SearchDataDto;
import com.epam.esm.service.dto.tag.TagDto;
import com.epam.esm.service.dto.certificate.CreateCertificateDto;
import com.epam.esm.service.dto.certificate.CreateCertificateTagDto;
import com.epam.esm.service.dto.certificate.DeleteCertificateByIdDto;
import com.epam.esm.service.dto.certificate.UpdateCertificateDto;
import com.epam.esm.service.dto.certificate.UpdateCertificateTagDto;
import com.epam.esm.service.dto.tag.CreateTagDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class CertificateControllerTest {
    @Mock
    private CertificateTagService certificateTagService;
    @Mock
    private CertificateService certificateService;
    @InjectMocks
    private CertificateController certificateController;

    @Test
    public void createCertificateTest() {
        List<CreateTagDto> tagsDto = new ArrayList<>();
        tagsDto.add(new CreateTagDto("tag name 1"));
        CreateCertificateTagDto createUpdateCertificateTagDto = new CreateCertificateTagDto(
                new CreateCertificateDto(
                        "certificate name 1", "certificate description 1", BigDecimal.valueOf(1), 1,
                        LocalDateTime.parse("2021-01-01T09:10:12.100"), LocalDateTime.parse("2021-01-01T09:10:12.200")
                ),
                tagsDto
        );
        Long expected = 1L;
        doReturn(expected).when(certificateTagService).create(createUpdateCertificateTagDto);
        ResponseEntity<Long> responseEntity = certificateController.createCertificate(createUpdateCertificateTagDto);
        assertEquals(expected, responseEntity.getBody());
    }

    @Test
    public void findCertificatesTest() {
        List<CertificateDto> expected = new ArrayList<>();
        expected.add(
                new CertificateDto(
                        1L, "certificate name 1", "certificate description 1", BigDecimal.valueOf(1), 1,
                        LocalDateTime.parse("2021-01-01T09:10:12.100"), LocalDateTime.parse("2021-01-01T09:10:12.200")
                )
        );
        expected.add(
                new CertificateDto(
                        2L, "certificate name 2", "certificate description 2", BigDecimal.valueOf(2), 2,
                        LocalDateTime.parse("2021-01-01T09:10:12.200"), LocalDateTime.parse("2021-01-01T09:10:12.300")
                )
        );
        doReturn(expected).when(certificateService).read();
        ResponseEntity<List<CertificateDto>> responseEntity = certificateController.findCertificates();
        assertEquals(expected, responseEntity.getBody());
    }

    @Test
    public void findCertificatesWithSearchDataTest() {
        List<CertificateTagDto> expected = new ArrayList<>();
        CertificateTagDto certificateTagDto = new CertificateTagDto();
        certificateTagDto.setCertificate(new CertificateDto(
                1L, "certificate name 1", "certificate description 1", BigDecimal.valueOf(1), 1,
                LocalDateTime.parse("2021-01-01T09:10:12.100"), LocalDateTime.parse("2021-01-01T09:10:12.200")
        ));
        certificateTagDto.setTags(new ArrayList<>());
        certificateTagDto.getTags().add(new TagDto(1L, "tag name 1"));
        SearchDataDto searchDataDto = new SearchDataDto(
                "tag name 1", "1", "1", new ArrayList<>()
        );
        searchDataDto.getSortData().add(new SearchDataDto.SortDto("cte_ac.c_name", "ASC"));
        doReturn(expected).when(certificateService).read(searchDataDto);
        ResponseEntity<List<CertificateTagDto>> responseEntity = certificateController.findCertificatesWithSearchData(searchDataDto);
        assertEquals(expected, responseEntity.getBody());
    }

    @Test
    public void updateCertificateTest() {
        UpdateCertificateDto giftCertificateDto = new UpdateCertificateDto(
                "certificate name 1", "certificate description 1", BigDecimal.valueOf(1), 1,
                LocalDateTime.parse("2021-01-01T09:10:12.100"), LocalDateTime.parse("2021-01-01T09:10:12.200")
        );
        List<CreateTagDto> tagsDto = new ArrayList<>();
        tagsDto.add(new CreateTagDto("tag name 1"));
        UpdateCertificateTagDto certificateTagDto = new UpdateCertificateTagDto(1L, giftCertificateDto, tagsDto);
        doReturn(true).when(certificateTagService).update(certificateTagDto);
        ResponseEntity<Boolean> responseEntity = certificateController.updateCertificate(certificateTagDto);
        assertEquals(true, responseEntity.getBody());
    }

    @Test
    public void deleteCertificateTest() {
        DeleteCertificateByIdDto deleteCertificateByIdDto = new DeleteCertificateByIdDto(1L);
        doReturn(true).when(certificateService).delete(deleteCertificateByIdDto);
        ResponseEntity<Boolean> responseEntity = certificateController.deleteCertificate(deleteCertificateByIdDto);
        assertEquals(true, responseEntity.getBody());
    }


}
