package com.epam.esm.database.dao;

public interface TagCertificateDao {

    boolean create(long tagId, long certificateId);
    boolean read(long tagId, long certificateId);

}
