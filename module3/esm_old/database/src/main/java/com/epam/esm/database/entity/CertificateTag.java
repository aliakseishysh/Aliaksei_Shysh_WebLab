package com.epam.esm.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateTag {
    private GiftCertificate certificate;
    private List<Tag> tags;
}
