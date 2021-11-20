package com.epam.esm.controller;

import com.epam.esm.database.entity.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/certificates", produces = MediaType.APPLICATION_JSON_VALUE)
public class CertificateController {
    private final GiftCertificateService certificateService;
    @Autowired
    public CertificateController(GiftCertificateService certificateService) {
        this.certificateService = certificateService;
    }
    @GetMapping
    public ResponseEntity<List<GiftCertificate>> findCertificates() {
        List<GiftCertificate> certificates = certificateService.findCertificates();
        return !certificates.isEmpty() ? ResponseEntity.ok(certificates) : ResponseEntity.noContent().build();
    }
}
