package com.pth.iflow.profile.service.handler;

import java.net.MalformedURLException;
import java.util.List;

import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.profile.exceptions.ProfileCustomizedException;
import com.pth.iflow.profile.model.Company;
import com.pth.iflow.profile.model.CompanyWorkflowtypeItemOcrSettingPreset;

public interface ICompaniesHandlerService {

  Company getCompanyByIdentity(final String identity)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  Company saveCompany(final Company company)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  List<CompanyWorkflowtypeItemOcrSettingPreset> readCompanyWorkflowtypeItemOcrSettingsByCompanyIdentity(String companyidentity)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  CompanyWorkflowtypeItemOcrSettingPreset saveCompanyWorkflowtypeItemOcrSettings(CompanyWorkflowtypeItemOcrSettingPreset modelInput)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  void deleteCompanyWorkflowtypeItemOcrSettings(CompanyWorkflowtypeItemOcrSettingPreset modelInput)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

}
