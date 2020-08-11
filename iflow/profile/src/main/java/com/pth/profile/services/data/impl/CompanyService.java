package com.pth.profile.services.data.impl;

import com.pth.common.edo.CompanyEdo;
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo;
import com.pth.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.profile.entities.CompanyEntity;
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity;
import com.pth.profile.repositories.ICompanyRepository;
import com.pth.profile.services.data.ICompanyService;

import java.util.List;

public class CompanyService implements ICompanyService {

    private final ICompanyRepository companyRepository;

    public CompanyService(ICompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyEntity save(CompanyEntity model) {
        return null;
    }

    @Override
    public CompanyEntity getByIdentity(String identifyId) {
        return null;
    }

    @Override
    public List<CompanyWorkflowTypeOcrSettingPresetEntity> readCompanyWorkflowtypeItemOcrSettings(Long id) {
        return null;
    }

    @Override
    public List<CompanyWorkflowTypeOcrSettingPresetEntity> readCompanyWorkflowtypeItemOcrSettingsByCompanyIdentity(String identity) {
        return null;
    }

    @Override
    public CompanyWorkflowTypeOcrSettingPresetEntity saveCompanyWorkflowtypeItemOcrSetting(CompanyWorkflowTypeOcrSettingPresetEntity preset) {
        return null;
    }

    @Override
    public void deleteCompanyWorkflowtypeItemOcrSetting(CompanyWorkflowTypeOcrSettingPresetEntity preset) {

    }

    @Override
    public CompanyWorkflowTypeOcrSettingPresetEntity fromCompanyWorkflowtypeItemOcrSettingPresetEdo(CompanyWorkflowtypeItemOcrSettingPresetEdo edo) {
        return null;
    }

    @Override
    public List<CompanyWorkflowTypeOcrSettingPresetEntity> fromCompanyWorkflowtypeItemOcrSettingPresetEdoList(List<CompanyWorkflowtypeItemOcrSettingPresetEdo> edoList) {
        return null;
    }

    @Override
    public List<CompanyWorkflowtypeItemOcrSettingPresetEdo> toCompanyWorkflowtypeItemOcrSettingPresetEdoList(List<CompanyWorkflowTypeOcrSettingPresetEntity> modelList) {
        return null;
    }

    @Override
    public CompanyWorkflowtypeItemOcrSettingPresetEdo toCompanyWorkflowtypeItemOcrSettingPresetEdo(CompanyWorkflowTypeOcrSettingPresetEntity modelSaved) {
        return null;
    }

    @Override
    public CompanyEntity fromEdo(CompanyEdo edo) throws IFlowMessageConversionFailureException {
        return null;
    }

    @Override
    public CompanyEdo toEdo(CompanyEntity model) {
        return null;
    }

    @Override
    public List<CompanyEdo> toEdoList(List<CompanyEntity> modelList) {
        return null;
    }

    @Override
    public List<CompanyEntity> fromEdoList(List<CompanyEdo> edoList) throws
                                                                     IFlowMessageConversionFailureException {
        return null;
    }
}
