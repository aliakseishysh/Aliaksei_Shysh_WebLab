package com.epam.esm.controller;

import com.epam.esm.controller.exception.EntityIsNotValidControllerException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.GiftCertificateTagService;
import com.epam.esm.service.dto.CertificateTagDto;
import com.epam.esm.service.dto.GiftCertificateDto;
import com.epam.esm.service.dto.SearchDataDto;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * Creates certificate in the database with specified parameters
     *
     * @param certificateTagDto dto object for {@code CertificateTag} entity
     * @return {@long} id of created object
     * @throws EntityIsNotValidControllerException if {@certificateTagDto} object is not valid
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createCertificate(@RequestBody CertificateTagDto certificateTagDto) throws EntityIsNotValidControllerException {
        try {
            Long result = giftCertificateTagService.create(certificateTagDto);
            return ResponseEntity.ok(result);
        } catch (EntityIsNotValidServiceException exception) {
            throw new EntityIsNotValidControllerException(exception);
        }
    }

    /**
     * Reads all certificates from the database
     *
     * @return {@code ResponseEntity<List<GiftCertificateDto>>} with {@code GiftCertificateDto} objects
     */
    @GetMapping
    public ResponseEntity<List<GiftCertificateDto>> findCertificates() {
        List<GiftCertificateDto> certificates = certificateService.read();
        return !certificates.isEmpty() ? ResponseEntity.ok(certificates) : ResponseEntity.noContent().build();
    }

    /**
     * Reads certificates from the database with specified parameters
     *
     * @param searchDataDto parameters for search
     * @return {@code ResponseEntity<List<GiftCertificateDto>>} with {@code GiftCertificateDto} objects
     */
    @PostMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CertificateTagDto>> findCertificatesWithSearchData(@RequestBody SearchDataDto searchDataDto) throws EntityIsNotValidControllerException {
        try {
            List<CertificateTagDto> certificates = certificateService.read(searchDataDto);
            return ResponseEntity.ok(certificates);
        } catch (EntityIsNotValidServiceException exception) {
            throw new EntityIsNotValidControllerException(exception);
        }

    }

    /**
     * Updates certificate in the database with specified parameters
     *
     * @param id                id of certificate to update
     * @param certificateTagDto certificate update info
     * @return {@code ResponseEntity<Boolean>} result of the certificate update
     * @throws EntityIsNotValidControllerException if entity is not valid
     */
    @PostMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updateCertificate(@PathVariable long id, @RequestBody CertificateTagDto certificateTagDto) throws EntityIsNotValidControllerException {
        try {
            Boolean result = certificateService.update(id, certificateTagDto.getCertificate());
            return ResponseEntity.ok(result);
        } catch (EntityIsNotValidServiceException exception) {
            throw new EntityIsNotValidControllerException(exception);
        }

    }

    /**
     * Deletes certificate with specified parameters (id)
     *
     * @param giftCertificateDto dto object for {@code GiftCertificate} entity with {@id} of the certificate to delete
     * @return {@code ResponseEntity<Boolean>} result of the certificate update
     * @throws EntityIsNotValidControllerException if entity is not valid
     */
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
