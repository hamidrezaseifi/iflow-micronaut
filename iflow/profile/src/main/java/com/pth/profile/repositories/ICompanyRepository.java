package com.pth.profile.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.profile.entities.CompanyEntity;
import com.pth.profile.entities.UserEntity;

import java.util.Optional;

public interface ICompanyRepository extends IEntityRepository<CompanyEntity> {

    CompanyEntity getByIdentity(String identity);
}
