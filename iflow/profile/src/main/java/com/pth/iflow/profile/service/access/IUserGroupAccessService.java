package com.pth.iflow.profile.service.access;

import java.net.MalformedURLException;
import java.util.List;

import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.profile.exceptions.ProfileCustomizedException;
import com.pth.iflow.profile.model.UserGroup;

public interface IUserGroupAccessService {

  UserGroup getById(final String id) throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  List<UserGroup> getListByCompanyIdentity(final String companyId)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;
}
