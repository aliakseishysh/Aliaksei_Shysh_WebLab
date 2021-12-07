package com.epam.esm.service;


import com.epam.esm.database.entity.CertificateTag;

public interface CertificateTagService {
    long create(CertificateTag certificateTag);
    boolean update(long id, CertificateTag certificateTag);
}
