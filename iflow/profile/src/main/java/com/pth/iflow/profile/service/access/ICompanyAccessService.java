package com.pth.iflow.profile.service.access;

import java.net.MalformedURLException;
import java.util.List;

import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.profile.exceptions.ProfileCustomizedException;
import com.pth.iflow.profile.model.Company;
import com.pth.iflow.profile.model.CompanyWorkflowtypeItemOcrSettingPreset;

public interface ICompanyAccessService {

  Company getByIdentity(final String comapnyIdentity)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  Company saveCompany(Company company) throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  List<CompanyWorkflowtypeItemOcrSettingPreset> readCompanyWorkflowtypeItemOcrSettingsByCompanyIdentity(String companyidentity)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  CompanyWorkflowtypeItemOcrSettingPreset
      saveCompanyWorkflowtypeItemOcrSettings(CompanyWorkflowtypeItemOcrSettingPreset modelInput)
          throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  void deleteCompanyWorkflowtypeItemOcrSettings(CompanyWorkflowtypeItemOcrSettingPreset modelInput)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;;

}
