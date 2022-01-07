package com.epam.esm.database.dao;

import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.database.entity.Certificate;
import com.epam.esm.database.entity.SearchData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateDao extends JpaRepository<Certificate, Long> {
    Certificate update(Certificate certificate);
}
