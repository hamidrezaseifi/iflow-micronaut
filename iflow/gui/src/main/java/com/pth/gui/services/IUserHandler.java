package com.pth.gui.services;

import com.pth.gui.models.User;
import com.pth.gui.models.UserDashboardMenu;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface IUserHandler {

  public Optional<User> saveUser(String authorization, final User user);

  public List<User> getCompanyUserList(String authorization, final UUID companyId);

  public void deleteUser(String authorization, User user);

  public String saveUserPassword(String authorization, UUID userId, String password , boolean resetPassword);

  public List<UserDashboardMenu> saveUserDashboardMenus(String authorization,
                                                        List<UserDashboardMenu> userDashboardMenuList,
                                                        String userIdentity);

}
