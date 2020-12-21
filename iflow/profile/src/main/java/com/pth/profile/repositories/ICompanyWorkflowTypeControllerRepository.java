package com.pth.profile.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.profile.entities.CompanyWorkflowTypeControllerEntity;
import com.pth.profile.entities.UserDashboardMenuEntity;

import java.util.List;
import java.util.UUID;

public interface ICompanyWorkflowTypeControllerRepository extends IEntityRepository<CompanyWorkflowTypeControllerEntity> {
    List<CompanyWorkflowTypeControllerEntity> getByCompanyId(UUID companyId);
}
