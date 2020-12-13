package com.pth.gui.services.impl;

import com.pth.clients.clients.profile.ICompanyClient;
import com.pth.clients.declaratives.company.ICompanyV001DeclarativeClient;
import com.pth.common.edo.CompanyEdo;
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo;
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetListEdo;
import com.pth.common.edo.enums.EInvoiceWorkflowTypeItems;
import com.pth.common.edo.enums.EOcrType;
import com.pth.common.edo.enums.EWorkflowType;
import com.pth.gui.mapper.ICompanyMapper;
import com.pth.gui.mapper.ICompanyWorkflowTypeOcrSettingPresetItemMapper;
import com.pth.gui.mapper.ICompanyWorkflowTypeOcrSettingPresetMapper;
import com.pth.gui.models.Company;
import com.pth.gui.models.CompanyWorkflowtypeItemOcrSettingPreset;
import com.pth.gui.models.CompanyWorkflowtypeItemOcrSettingPresetItem;
import com.pth.gui.services.ICompanyHandler;

import javax.inject.Singleton;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class CompanyHandler implements ICompanyHandler {

    private final ICompanyClient companyClient;
    private final ICompanyMapper companyMapper;
    private final ICompanyWorkflowTypeOcrSettingPresetMapper companyWorkflowTypeOcrSettingPresetMapper;
    private final ICompanyWorkflowTypeOcrSettingPresetItemMapper companyWorkflowTypeOcrSettingPresetItemMapper;


    public CompanyHandler(ICompanyClient companyClient,
                          ICompanyMapper companyMapper,
                          ICompanyWorkflowTypeOcrSettingPresetMapper companyWorkflowTypeOcrSettingPresetMapper,
                          ICompanyWorkflowTypeOcrSettingPresetItemMapper companyWorkflowTypeOcrSettingPresetItemMapper) {
        this.companyClient = companyClient;
        this.companyMapper = companyMapper;
        this.companyWorkflowTypeOcrSettingPresetMapper = companyWorkflowTypeOcrSettingPresetMapper;
        this.companyWorkflowTypeOcrSettingPresetItemMapper = companyWorkflowTypeOcrSettingPresetItemMapper;
    }

    @Override
    public Optional<Company> read(final UUID companyId, final String token){

        Optional<CompanyEdo> edoOptional = this.companyClient.read(token, companyId);

        if(edoOptional.isPresent()){
            return Optional.of(companyMapper.fromEdo(edoOptional.get()));
        }

        return Optional.empty();
    }

    @Override
    public Optional<Company> save(final Company company, final String token) {

        Optional<CompanyEdo> edoOptional = this.companyClient.save(token, companyMapper.toEdo(company));
        if(edoOptional.isPresent()){
            return Optional.of(companyMapper.fromEdo(edoOptional.get()));
        }

        return Optional.empty();
    }

    @Override
    public List<CompanyWorkflowtypeItemOcrSettingPreset> readCompanyWorkflowtypeItemOcrSettings(final UUID companyId,
                                                                                                final String token){

        final Optional<CompanyWorkflowtypeItemOcrSettingPresetListEdo> listEdoOptional =
                this.companyClient.readCompanyWorkflowtypeItemOcrSettings(token, companyId);

        if(listEdoOptional.isPresent()){
            List<CompanyWorkflowtypeItemOcrSettingPresetEdo> edoList =
                    listEdoOptional.get().getCompanyWorkflowtypeItemOcrSettings();
            return companyWorkflowTypeOcrSettingPresetMapper.fromEdoList(edoList);
        }
//CompanyWorkflowtypeItemOcrSettingPresetEdo
//CompanyWorkflowtypeItemOcrSettingPresetEdo
        return new ArrayList<>();
    }

    @Override
    public Optional<CompanyWorkflowtypeItemOcrSettingPreset>
        saveCompanyWorkflowtypeItemOcrSetting(final CompanyWorkflowtypeItemOcrSettingPreset preset,
                                              final String token) {

        preset.prepareItems();

        CompanyWorkflowtypeItemOcrSettingPresetEdo presetEdo = companyWorkflowTypeOcrSettingPresetMapper.toEdo(preset);

        final Optional<CompanyWorkflowtypeItemOcrSettingPresetEdo> resultOptional =
                this.companyClient.saveCompanyWorkflowtypeItemOcrSettings(token, presetEdo);

        if(resultOptional.isPresent()){

            CompanyWorkflowtypeItemOcrSettingPresetEdo result = resultOptional.get();
            //this.sessionUserInfo.getCompanyProfile().setOcrPreset(result);
            //this.sessionUserInfo.resetWorkflowtypeItemOcrSettings();

            return Optional.of(companyWorkflowTypeOcrSettingPresetMapper.fromEdo(result));
        }

        return Optional.empty();
    }

    @Override
    public void deleteCompanyWorkflowtypeItemOcrSetting(final CompanyWorkflowtypeItemOcrSettingPreset preset,
                                                        final String token) {

        this.companyClient.deleteCompanyWorkflowtypeItemOcrSettings(token,
                                                                    companyWorkflowTypeOcrSettingPresetMapper.toEdo(preset));

        //this.sessionUserInfo.getCompanyProfile().deleteOcrPreset(preset);
        //this.sessionUserInfo.resetWorkflowtypeItemOcrSettings();

    }

    @Override
    public List<String> readWorkflowtypeItems(final EWorkflowType workflowType) {

        final List<String> items = new ArrayList<>();

        if (EWorkflowType.INVOICE_WORKFLOW_TYPE == workflowType) {
            items.addAll(EInvoiceWorkflowTypeItems.toIdentityList());

        }

        return items;
    }

    @Override
    public Map<String, CompanyWorkflowtypeItemOcrSettingPresetItem> readPresetAllItems(final String presetName,
                                                                                       final UUID CompanyId,
                                                                                       EWorkflowType workflowType,
                                                                                       final String token) {

        final List<CompanyWorkflowtypeItemOcrSettingPreset> presetList =
                this.readCompanyWorkflowtypeItemOcrSettings(CompanyId, token);
        final Optional<CompanyWorkflowtypeItemOcrSettingPreset> optionalPreset = presetList
                .stream()
                .filter(p -> p.getPresetName().equals(presetName))
                .findAny();

        if (optionalPreset.isPresent() == false) {
            return null;
        }

        return this.extractMappedCompanyWorkflowtypeItemsFromOcrPreset(optionalPreset.get(), workflowType);
    }

    @Override
    public Map<String, CompanyWorkflowtypeItemOcrSettingPresetItem>
        readPresetAllItemsFromSession(final String presetName,
                                      List<CompanyWorkflowtypeItemOcrSettingPreset> presetList,
                                      EWorkflowType workflowType) {

        final Optional<CompanyWorkflowtypeItemOcrSettingPreset> optionalPreset = presetList
                .stream()
                .filter(p -> p.getPresetName().equals(presetName))
                .findAny();

        if (optionalPreset.isPresent() == false) {
            return null;
        }

        return this.extractMappedCompanyWorkflowtypeItemsFromOcrPreset(optionalPreset.get(), workflowType);
    }

    @Override
    public Map<String, CompanyWorkflowtypeItemOcrSettingPresetItem>
        extractMappedCompanyWorkflowtypeItemsFromOcrPreset(final CompanyWorkflowtypeItemOcrSettingPreset preset,
                                                           EWorkflowType workflowType) {

        final Map<String,
                CompanyWorkflowtypeItemOcrSettingPresetItem> map = preset
                .getItems()
                .stream()
                .collect(Collectors.toMap(i -> i.getPropertyName(), i -> i));

        final List<String> items = this.readWorkflowtypeItems(workflowType);

        for (final String itemName : items) {
            if (map.containsKey(itemName) == false) {
                final CompanyWorkflowtypeItemOcrSettingPresetItem prop =
                        new CompanyWorkflowtypeItemOcrSettingPresetItem();
                prop.setOcrType(EOcrType.SEARCH_WORD.getValue());
                prop.setPropertyName(itemName);
                prop.setValue("");
                prop.setStatus(1);
                prop.setVersion(1);

                map.put(itemName, prop);
            }
        }

        return map;
    }
    
}
