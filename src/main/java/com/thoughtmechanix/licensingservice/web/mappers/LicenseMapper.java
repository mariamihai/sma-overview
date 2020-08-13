package com.thoughtmechanix.licensingservice.web.mappers;

import com.thoughtmechanix.licensingservice.domain.License;
import com.thoughtmechanix.licensingservice.web.model.LicenseDto;
import org.mapstruct.Mapper;

@Mapper
public interface LicenseMapper {

    License dtoToLicense(LicenseDto dto);
    LicenseDto licenseToDto(License license);
}
