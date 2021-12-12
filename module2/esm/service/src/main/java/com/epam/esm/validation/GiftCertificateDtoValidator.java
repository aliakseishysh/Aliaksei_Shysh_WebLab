package com.epam.esm.validation;

import com.epam.esm.service.dto.GiftCertificateDto;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class GiftCertificateDtoValidator {

    private static boolean validateIdInternal(Long id) {
        return id != null;
    }

    private static boolean validateNameInternal(String name) {
        return name != null;
    }

    private static boolean validateDescriptionInternal(String description) {
        return description != null;
    }

    private static boolean validatePriceInternal(BigDecimal price) {
        return price != null && price.compareTo(BigDecimal.ZERO) >= 0;
    }

    private static boolean validateDurationInternal(Integer duration) {
        return duration != null && duration >= 0;
    }

    private static boolean validateDateInternal(LocalDateTime localDateTime) {
        return localDateTime != null;
    }

    public static void validate(GiftCertificateDto certificateDto) throws EntityIsNotValidServiceException {
        if (!validateNameInternal(certificateDto.getName())
                && !validateDescriptionInternal(certificateDto.getDescription())
                && !validatePriceInternal(certificateDto.getPrice())
                && !validateDurationInternal(certificateDto.getDuration())
                && !validateDateInternal(certificateDto.getCreateDate())
                && !validateDateInternal(certificateDto.getLastUpdateDate())
        ) {
            throw new EntityIsNotValidServiceException("Entity " + certificateDto + " is not valid.");

        }
    }

    public static void validateWithId(GiftCertificateDto certificateDto) throws EntityIsNotValidServiceException {
        if (!validateIdInternal(certificateDto.getId())
                && !validateNameInternal(certificateDto.getName())
                && !validateDescriptionInternal(certificateDto.getDescription())
                && !validatePriceInternal(certificateDto.getPrice())
                && !validateDurationInternal(certificateDto.getDuration())
                && !validateDateInternal(certificateDto.getCreateDate())
                && !validateDateInternal(certificateDto.getLastUpdateDate())) {
            throw new EntityIsNotValidServiceException("Entity " + certificateDto + " is not valid.");
        }
    }

    public static void validateId(Long id) throws EntityIsNotValidServiceException {
        if (!validateIdInternal(id)) {
            throw new EntityIsNotValidServiceException("Entity with id=" + id + " is not valid.");
        }
    }

    public static void validatePartial(GiftCertificateDto certificateDto) throws EntityIsNotValidServiceException {
        if (certificateDto.getId() != null && !validateIdInternal(certificateDto.getId())) {
            throw new EntityIsNotValidServiceException("Entity " + certificateDto + " is not valid.");
        }
        if (certificateDto.getName() != null && !validateNameInternal(certificateDto.getName())) {
            throw new EntityIsNotValidServiceException("Entity " + certificateDto + " is not valid.");
        }
        if (certificateDto.getDescription() != null && !validateDescriptionInternal(certificateDto.getDescription())) {
            throw new EntityIsNotValidServiceException("Entity " + certificateDto + " is not valid.");
        }
        if (certificateDto.getPrice() != null && !validatePriceInternal(certificateDto.getPrice())) {
            throw new EntityIsNotValidServiceException("Entity " + certificateDto + " is not valid.");
        }
        if (certificateDto.getDuration() != null && !validateDurationInternal(certificateDto.getDuration())) {
            throw new EntityIsNotValidServiceException("Entity " + certificateDto + " is not valid.");
        }
        if (certificateDto.getCreateDate() != null && !validateDateInternal(certificateDto.getCreateDate())) {
            throw new EntityIsNotValidServiceException("Entity " + certificateDto + " is not valid.");
        }
        if (certificateDto.getLastUpdateDate() != null && !validateDateInternal(certificateDto.getLastUpdateDate())) {
            throw new EntityIsNotValidServiceException("Entity " + certificateDto + " is not valid.");
        }
    }

}
