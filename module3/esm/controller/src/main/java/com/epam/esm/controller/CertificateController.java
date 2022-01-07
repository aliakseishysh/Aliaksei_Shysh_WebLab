package com.epam.esm.controller;

import com.epam.esm.service.CertificateService;
import com.epam.esm.service.CertificateTagService;
import com.epam.esm.service.dto.CertificateTagDto;
import com.epam.esm.service.dto.SearchDataDto;
import com.epam.esm.service.dto.certificate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/certificates", produces = MediaType.APPLICATION_JSON_VALUE)
public class CertificateController {
    private final CertificateService certificateService;
    private final CertificateTagService certificateTagService;

    @Autowired
    public CertificateController(CertificateService certificateService, CertificateTagService certificateTagService) {
        this.certificateService = certificateService;
        this.certificateTagService = certificateTagService;
    }

    /**
     * Creates certificate in the com.epam.esm.database with specified parameters
     *
     * @param createCertificateDto dto object for {@code CertificateTag} entity
     * @return {@long} id of created object
     */
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createCertificate(@RequestBody @Valid CreateCertificateDto createCertificateDto) {
        Long result = certificateTagService.create(createCertificateDto);
        return ResponseEntity.ok(result);
    }

    /**
     * Reads all certificates from the com.epam.esm.database
     *
     * @return {@code ResponseEntity<List<CertificateDto>>} with {@code CertificateDto} objects
     */
    @GetMapping
    public ResponseEntity<List<CertificateDto>> findCertificates() {
        List<CertificateDto> certificates = certificateService.findAll();
        return !certificates.isEmpty() ? ResponseEntity.ok(certificates) : ResponseEntity.noContent().build();
    }

    /**
     * Reads certificates from the com.epam.esm.database with specified parameters
     *
     * @param searchDataDto parameters for search
     * @return {@code ResponseEntity<List<CertificateDto>>} with {@code CertificateDto} objects
     */
    @PostMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CertificateDto>> searchCertificates(@RequestBody @Valid SearchDataDto searchDataDto) {
        List<CertificateDto> certificates = certificateService.read(searchDataDto);
        return ResponseEntity.ok(certificates);
    }

    /**
     * Updates certificate in the com.epam.esm.database with specified parameters
     *
     * @param updateCertificateDto certificate update info
     * @return {@code ResponseEntity<Boolean>} result of the certificate update
     */
    @PostMapping(path = "/update/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updateCertificate(@RequestBody @Valid UpdateCertificateDto updateCertificateDto) {
        Boolean result = certificateTagService.update(updateCertificateDto);
        return ResponseEntity.ok(result);
    }

    /**
     * Deletes certificate with specified parameters (id)
     *
     * @param deleteCertificateByIdDto dto object for {@code GiftCertificate} entity with {@id} of the certificate to delete
     * @return {@code ResponseEntity<Boolean>} result of the certificate update
     */
    //TODO return value
    @DeleteMapping(path = "/delete")
    public ResponseEntity<Boolean> deleteCertificate(@RequestBody @Valid DeleteCertificateByIdDto deleteCertificateByIdDto) {
        certificateService.delete(deleteCertificateByIdDto);
        return ResponseEntity.ok(true);
    }

}
