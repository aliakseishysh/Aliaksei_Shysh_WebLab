package com.epam.esm.database.dao;

import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.database.entity.Certificate;
import com.epam.esm.database.entity.SearchData;

import java.util.List;

public interface CertificateDao {

    long create(Certificate certificate);

    List<Certificate> read();

    List<CertificateTag> read(SearchData searchData);

    boolean update(long id, Certificate certificate);

    boolean delete(long id);

}
