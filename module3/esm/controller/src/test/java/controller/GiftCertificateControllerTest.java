package controller;

import com.epam.esm.controller.GiftCertificateController;
import com.epam.esm.controller.exception.EntityIsNotValidControllerException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.GiftCertificateTagService;
import com.epam.esm.service.dto.CertificateTagDto;
import com.epam.esm.service.dto.CreateGiftCertificateDto;
import com.epam.esm.service.dto.GiftCertificateDto;
import com.epam.esm.service.dto.SearchDataDto;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.dto.certificate.CreateUpdateCertificateTagDto;
import com.epam.esm.service.dto.tag.CreateTagDto;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;
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
public class GiftCertificateControllerTest {
    @Mock
    private GiftCertificateTagService giftCertificateTagService;
    @Mock
    private GiftCertificateService giftCertificateService;
    @InjectMocks
    private GiftCertificateController giftCertificateController;

    @Test
    public void createCertificateTest() throws EntityIsNotValidServiceException, EntityIsNotValidControllerException {
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
        doReturn(expected).when(giftCertificateTagService).create(createUpdateCertificateTagDto);
        ResponseEntity<Long> responseEntity = giftCertificateController.createCertificate(createUpdateCertificateTagDto);
        assertEquals(expected, responseEntity.getBody());
    }

    @Test
    public void findCertificatesTest() {
        List<GiftCertificateDto> expected = new ArrayList<>();
        expected.add(
                new GiftCertificateDto(
                        1L, "certificate name 1", "certificate description 1", BigDecimal.valueOf(1), 1,
                        LocalDateTime.parse("2021-01-01T09:10:12.100"), LocalDateTime.parse("2021-01-01T09:10:12.200")
                )
        );
        expected.add(
                new GiftCertificateDto(
                        2L, "certificate name 2", "certificate description 2", BigDecimal.valueOf(2), 2,
                        LocalDateTime.parse("2021-01-01T09:10:12.200"), LocalDateTime.parse("2021-01-01T09:10:12.300")
                )
        );
        doReturn(expected).when(giftCertificateService).read();
        ResponseEntity<List<GiftCertificateDto>> responseEntity = giftCertificateController.findCertificates();
        assertEquals(expected, responseEntity.getBody());
    }

    @Test
    public void findCertificatesWithSearchDataTest() throws EntityIsNotValidServiceException, EntityIsNotValidControllerException {
        List<CertificateTagDto> expected = new ArrayList<>();
        CertificateTagDto certificateTagDto = new CertificateTagDto();
        certificateTagDto.setCertificate(new GiftCertificateDto(
                1L, "certificate name 1", "certificate description 1", BigDecimal.valueOf(1), 1,
                LocalDateTime.parse("2021-01-01T09:10:12.100"), LocalDateTime.parse("2021-01-01T09:10:12.200")
        ));
        certificateTagDto.setTags(new ArrayList<>());
        certificateTagDto.getTags().add(new TagDto(1L, "tag name 1"));
        SearchDataDto searchDataDto = new SearchDataDto(
                "tag name 1", "1", "1", new ArrayList<>()
        );
        searchDataDto.getSortData().add(new SearchDataDto.SortDto("cte_ac.c_name", "ASC"));
        doReturn(expected).when(giftCertificateService).read(searchDataDto);
        ResponseEntity<List<CertificateTagDto>> responseEntity = giftCertificateController.findCertificatesWithSearchData(searchDataDto);
        assertEquals(expected, responseEntity.getBody());
    }

    @Test
    public void updateCertificateTest() throws EntityIsNotValidServiceException, EntityIsNotValidControllerException {
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto(
                null, "certificate name 1", "certificate description 1", BigDecimal.valueOf(1), 1,
                LocalDateTime.parse("2021-01-01T09:10:12.100"), LocalDateTime.parse("2021-01-01T09:10:12.200")
        );
        List<TagDto> tagsDto = new ArrayList<>();
        tagsDto.add(new TagDto(1L, "tag name 1"));
        CertificateTagDto certificateTagDto = new CertificateTagDto(giftCertificateDto, tagsDto);
        Boolean expected = true;
        doReturn(true).when(giftCertificateService).update(1L, certificateTagDto.getCertificate());
        ResponseEntity<Boolean> responseEntity = giftCertificateController.updateCertificate(1L, certificateTagDto);
        assertEquals(true, responseEntity.getBody());
    }

    @Test
    public void deleteCertificateTest() throws EntityIsNotValidServiceException, EntityIsNotValidControllerException {
        GiftCertificateDto giCertificateTagDto = new GiftCertificateDto(
                1L, null, null, null, null, null, null
        );
        Boolean expected = true;
        doReturn(true).when(giftCertificateService).delete(giCertificateTagDto);
        ResponseEntity<Boolean> responseEntity = giftCertificateController.deleteCertificate(giCertificateTagDto);
        assertEquals(true, responseEntity.getBody());
    }


}
