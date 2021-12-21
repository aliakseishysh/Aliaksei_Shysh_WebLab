package com.epam.esm.controller;

import com.epam.esm.service.CertificateService;
import com.epam.esm.service.CertificateTagService;
import com.epam.esm.service.dto.CertificateTagDto;
import com.epam.esm.service.dto.SearchDataDto;
import com.epam.esm.service.dto.certificate.CertificateDto;
import com.epam.esm.service.dto.certificate.CreateCertificateTagDto;
import com.epam.esm.service.dto.certificate.DeleteCertificateByIdDto;
import com.epam.esm.service.dto.certificate.UpdateCertificateTagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @param createCertificateTagDto dto object for {@code CertificateTag} entity
     * @return {@long} id of created object
     */
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createCertificate(@RequestBody @Valid CreateCertificateTagDto createCertificateTagDto) {
        Long result = certificateTagService.create(createCertificateTagDto);
        return ResponseEntity.ok(result);
    }

    /**
     * Reads all certificates from the com.epam.esm.database
     *
     * @return {@code ResponseEntity<List<CertificateDto>>} with {@code CertificateDto} objects
     */
    @GetMapping
    public ResponseEntity<List<CertificateDto>> findCertificates() {
        List<CertificateDto> certificates = certificateService.read();
        return !certificates.isEmpty() ? ResponseEntity.ok(certificates) : ResponseEntity.noContent().build();
    }

    /**
     * Reads certificates from the com.epam.esm.database with specified parameters
     *
     * @param searchDataDto parameters for search
     * @return {@code ResponseEntity<List<CertificateDto>>} with {@code CertificateDto} objects
     */
    @PostMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CertificateTagDto>> findCertificatesWithSearchData(@RequestBody @Valid SearchDataDto searchDataDto) {
        List<CertificateTagDto> certificates = certificateService.read(searchDataDto);
        return ResponseEntity.ok(certificates);
    }

    /**
     * Updates certificate in the com.epam.esm.database with specified parameters
     *
     * @param updateCertificateTagDto certificate update info
     * @return {@code ResponseEntity<Boolean>} result of the certificate update
     */
    @PostMapping(path = "/update/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updateCertificate(@RequestBody @Valid UpdateCertificateTagDto updateCertificateTagDto) {
        Boolean result = certificateTagService.update(updateCertificateTagDto);
        return ResponseEntity.ok(result);
    }

    /**
     * Deletes certificate with specified parameters (id)
     *
     * @param deleteCertificateByIdDto dto object for {@code GiftCertificate} entity with {@id} of the certificate to delete
     * @return {@code ResponseEntity<Boolean>} result of the certificate update
     */
    @DeleteMapping(path = "/delete")
    public ResponseEntity<Boolean> deleteCertificate(@RequestBody @Valid DeleteCertificateByIdDto deleteCertificateByIdDto) {
        Boolean result = certificateService.delete(deleteCertificateByIdDto);
        return ResponseEntity.ok(result);
    }

}
