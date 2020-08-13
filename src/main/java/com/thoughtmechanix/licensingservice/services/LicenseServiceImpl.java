package com.thoughtmechanix.licensingservice.services;

import com.thoughtmechanix.licensingservice.config.ServiceConfig;
import com.thoughtmechanix.licensingservice.domain.License;
import com.thoughtmechanix.licensingservice.repositories.LicenseRepository;
import com.thoughtmechanix.licensingservice.web.mappers.LicenseMapper;
import com.thoughtmechanix.licensingservice.web.model.LicenseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LicenseServiceImpl implements LicenseService {

    private final LicenseRepository licenseRepository;
    private final LicenseMapper licenseMapper;

    private final ServiceConfig serviceConfig;

    @Override
    public LicenseDto getLicense(UUID organizationId, UUID licenseId) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        license.setComment(serviceConfig.getExampleProperty());

        return licenseMapper.licenseToDto(license);
    }

    @Override
    public List<LicenseDto> getLicensesByOrg(UUID organizationId) {
        List<License> licenses = licenseRepository.findByOrganizationId(organizationId);

        return licenses.stream()
                .map(licenseMapper::licenseToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveLicense(LicenseDto dto) {
        License license = licenseMapper.dtoToLicense(dto);
        license.setLicenseId(null);

        licenseRepository.save(license);
    }
}
