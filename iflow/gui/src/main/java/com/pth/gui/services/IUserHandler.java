package com.pth.gui.services;

import com.pth.gui.models.User;
import com.pth.gui.models.UserDashboardMenu;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface IUserHandler {

  Optional<User> createUser(String authorization, final User user);

  Optional<User> saveUser(String authorization, final User user);

  List<User> getCompanyUserList(String authorization, final UUID companyId);

  void deleteUser(String authorization, User user);

  String saveUserPassword(String authorization, User user, String password , boolean resetPassword);

  List<UserDashboardMenu> saveUserDashboardMenus(String authorization,
                                                        List<UserDashboardMenu> userDashboardMenuList,
                                                        UUID userId);

}
