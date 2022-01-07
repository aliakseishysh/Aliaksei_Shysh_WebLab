package com.epam.esm.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateTag {
    private Certificate certificate;
    private List<Tag> tags;
}
