package com.pth.profile.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.profile.entities.CompanyEntity;

import java.util.Optional;

public interface ICompanyRepository extends IEntityRepository<CompanyEntity> {

    Optional<CompanyEntity> getByIdentity(String identity);
}
