package com.pth.profile.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity;
import com.pth.profile.entities.UserDashboardMenuEntity;

import java.util.List;
import java.util.UUID;

public interface IUserDashboardMenuRepository extends IEntityRepository<UserDashboardMenuEntity> {
    List<UserDashboardMenuEntity> getByUserId(UUID userId, String appId);
    int deleteByUserId(UUID userId, String appId);
}
