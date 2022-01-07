package com.epam.esm.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateIdTagId {
    private Long tag_id;
    private Long certificate_id;
}
