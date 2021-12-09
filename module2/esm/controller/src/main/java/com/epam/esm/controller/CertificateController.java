package com.epam.esm.controller;

import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.database.entity.GiftCertificate;
import com.epam.esm.database.entity.SearchData;
import com.epam.esm.service.CertificateTagService;
import com.epam.esm.service.GiftCertificateService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/certificates", produces = MediaType.APPLICATION_JSON_VALUE)
public class CertificateController {
    private final GiftCertificateService certificateService;
    private final CertificateTagService certificateTagService;
    @Autowired
    public CertificateController(GiftCertificateService certificateService, CertificateTagService certificateTagService) {
        this.certificateService = certificateService;
        this.certificateTagService = certificateTagService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> createCertificate(@RequestBody CertificateTag certificateTag) {
        Long result = certificateTagService.create(certificateTag);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<GiftCertificate>> findCertificates() {
        List<GiftCertificate> certificates = certificateService.read();
        return !certificates.isEmpty() ? ResponseEntity.ok(certificates) : ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CertificateTag>> findCertificatesWithSearchData(@RequestBody SearchData searchData) {
        List<CertificateTag> certificates = certificateService.read(searchData);
        return ResponseEntity.ok(certificates);
    }

//    @GetMapping(path = "/tags/{tagName}")
//    public ResponseEntity<List<CertificateTag>> findCertificatesByTag(@PathVariable String tagName) {
//        List<CertificateTag> certificates = certificateService.read(tagName);
//        return ResponseEntity.ok(certificates);
//    }

//    @GetMapping(path = "/pname/{partialName}")
//    public ResponseEntity<List<CertificateTag>> findCertificatesWithTagsByPartialName(@PathVariable String partialName) {
//        List<CertificateTag> certificates = certificateService.readByPartialName(partialName);
//        return ResponseEntity.ok(certificates);
//    }
//
//    @GetMapping(path = "/pdescription/{partialDescription}")
//    public ResponseEntity<List<CertificateTag>> findCertificatesWithTagsByPartialDescription(@PathVariable String partialDescription) {
//        List<CertificateTag> certificates = certificateService.readByPartialDescription(partialDescription);
//        return ResponseEntity.ok(certificates);
//    }
//
//    @GetMapping(path = "/sort_order", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<CertificateTag>> findCertificatesWithTagsSortedAndOrdered(@RequestBody List<SearchData> searchData) {
//        List<CertificateTag> certificates = certificateService.readSortedOrdered(searchData);
//        return ResponseEntity.ok(certificates);
//    }


    @PostMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updateCertificate(@PathVariable long id, @RequestBody CertificateTag certificateTag) {
        Boolean result = certificateService.update(id, certificateTag.getCertificate());
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Boolean> deleteCertificate(@PathVariable long id) {
        Boolean result = certificateService.delete(id);
        return ResponseEntity.ok(result);
    }

}
