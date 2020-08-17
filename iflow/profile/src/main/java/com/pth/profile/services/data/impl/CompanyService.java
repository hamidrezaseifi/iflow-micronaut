package com.pth.profile.services.data.impl;

import com.pth.profile.entities.CompanyEntity;
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity;
import com.pth.profile.repositories.ICompanyRepository;
import com.pth.profile.repositories.ICompanyWorkflowTypeOcrSettingPresetRepository;
import com.pth.profile.services.data.ICompanyService;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class CompanyService implements ICompanyService {

    private final ICompanyRepository companyRepository;
    private final ICompanyWorkflowTypeOcrSettingPresetRepository workflowTypeOcrSettingPresetRepository;

    public CompanyService(ICompanyRepository companyRepository,
                          ICompanyWorkflowTypeOcrSettingPresetRepository workflowTypeOcrSettingPresetRepository) {
        this.companyRepository = companyRepository;
        this.workflowTypeOcrSettingPresetRepository = workflowTypeOcrSettingPresetRepository;
    }

    @Override
    public Optional<CompanyEntity> save(CompanyEntity model) {
        companyRepository.save(model);
        return companyRepository.getById(model.getId());
    }

    @Override
    public Optional<CompanyEntity> getByIdentity(String identity) {
        return companyRepository.getByIdentity(identity);
    }

    @Override
    public List<CompanyWorkflowTypeOcrSettingPresetEntity> getCompanyWorkflowtypeItemOcrSettingList(UUID companyId) {
        return this.workflowTypeOcrSettingPresetRepository.getByCompanyId(companyId);
    }

    @Override
    public List<CompanyWorkflowTypeOcrSettingPresetEntity> getCompanyWorkflowtypeItemOcrSettingListByCompanyIdentity(String identity) {

        Optional<CompanyEntity> companyEntityOptional = getByIdentity(identity);
        if(companyEntityOptional.isPresent()){
            CompanyEntity companyEntity = companyEntityOptional.get();
            return getCompanyWorkflowtypeItemOcrSettingList(companyEntity.getId());
        }
        return null;
    }

    @Override
    public Optional<CompanyWorkflowTypeOcrSettingPresetEntity> saveCompanyWorkflowtypeItemOcrSetting(CompanyWorkflowTypeOcrSettingPresetEntity preset) {
        this.workflowTypeOcrSettingPresetRepository.save(preset);
        return this.workflowTypeOcrSettingPresetRepository.getById(preset.getId());
    }

    @Override
    public void deleteCompanyWorkflowtypeItemOcrSetting(CompanyWorkflowTypeOcrSettingPresetEntity preset) {
        this.workflowTypeOcrSettingPresetRepository.delete(preset);
    }

}
