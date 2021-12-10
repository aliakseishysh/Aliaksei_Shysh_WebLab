package com.epam.esm.controller;

import com.epam.esm.controller.exception.EntityIsNotValidControllerException;
import com.epam.esm.service.GiftCertificateTagService;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.dto.CertificateTagDto;
import com.epam.esm.service.dto.GiftCertificateDto;
import com.epam.esm.service.dto.SearchDataDto;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/certificates", produces = MediaType.APPLICATION_JSON_VALUE)
public class GiftCertificateController {
    private final GiftCertificateService certificateService;
    private final GiftCertificateTagService giftCertificateTagService;
    @Autowired
    public GiftCertificateController(GiftCertificateService certificateService, GiftCertificateTagService giftCertificateTagService) {
        this.certificateService = certificateService;
        this.giftCertificateTagService = giftCertificateTagService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createCertificate(@RequestBody CertificateTagDto certificateTagDto) throws EntityIsNotValidControllerException {
        try {
            Long result = giftCertificateTagService.create(certificateTagDto);
            return ResponseEntity.ok(result);
        } catch (EntityIsNotValidServiceException exception) {
            throw new EntityIsNotValidControllerException(exception);
        }
    }

    @GetMapping
    public ResponseEntity<List<GiftCertificateDto>> findCertificates() {
        List<GiftCertificateDto> certificates = certificateService.read();
        return !certificates.isEmpty() ? ResponseEntity.ok(certificates) : ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CertificateTagDto>> findCertificatesWithSearchData(@RequestBody SearchDataDto searchDataDto) {
        List<CertificateTagDto> certificates = certificateService.read(searchDataDto);
        return ResponseEntity.ok(certificates);
    }

    @PostMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updateCertificate(@PathVariable long id, @RequestBody CertificateTagDto certificateTagDto) throws EntityIsNotValidControllerException {
        try {
            Boolean result = certificateService.update(id, certificateTagDto.getCertificate());
            return ResponseEntity.ok(result);
        } catch (EntityIsNotValidServiceException exception) {
            throw new EntityIsNotValidControllerException(exception);
        }

    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteCertificate(@RequestBody GiftCertificateDto giftCertificateDto) throws EntityIsNotValidControllerException {
        try {
            Boolean result = certificateService.delete(giftCertificateDto);
            return ResponseEntity.ok(result);
        } catch (EntityIsNotValidServiceException exception) {
            throw new EntityIsNotValidControllerException(exception);
        }

    }

}
