package com.pth.gui.services.impl;

import com.pth.clients.clients.profile.IUserClient;
import com.pth.common.edo.*;
import com.pth.common.edo.enums.EApplication;
import com.pth.gui.mapper.IUserDashboardMenuMapper;
import com.pth.gui.mapper.IUserMapper;
import com.pth.gui.models.User;
import com.pth.gui.models.UserDashboardMenu;
import com.pth.gui.services.IUserHandler;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class UserHandler implements IUserHandler {

    private final IUserClient userClient;
    private final IUserMapper userMapper;
    private final IUserDashboardMenuMapper userDashboardMenuMapper;

    public UserHandler(IUserClient userClient,
                       IUserMapper userMapper,
                       IUserDashboardMenuMapper userDashboardMenuMapper) {

        this.userClient = userClient;
        this.userMapper = userMapper;
        this.userDashboardMenuMapper = userDashboardMenuMapper;

    }

    @Override
    public Optional<User> saveUser(String authorization, final User user) {

        final Optional<UserEdo> savedUserOptional = this.userClient.saveUser(authorization, userMapper.toEdo(user));
        if(savedUserOptional.isPresent()){
            return Optional.of(userMapper.fromEdo(savedUserOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public List<User> getCompanyUserList(String authorization, final UUID companyId){

        Optional<UserListEdo> userListEdoOptional = this.userClient.readCompanyUsers(authorization, companyId);
        if(userListEdoOptional.isPresent()){
            return userMapper.fromEdoList(userListEdoOptional.get().getUsers());
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteUser(String authorization, final User user) {

        this.userClient.deleteUser(authorization, userMapper.toEdo(user));

    }

    @Override
    public String saveUserPassword(String authorization,
                                           UUID userId,
                                           String password ,
                                           boolean resetPassword) {

        if (resetPassword) {
            Optional<UserEdo> edoOptional = this.userClient.readUserById(authorization, userId);
            if(edoOptional.isPresent()){
                password = this.getResetedPassword(edoOptional.get());
            }
        }

        UserPasswordResetRequestEdo userPasswordResetRequestEdo = new UserPasswordResetRequestEdo();
        userPasswordResetRequestEdo.setAppId(EApplication.IFLOW.getIdentity());
        userPasswordResetRequestEdo.setUserId(userId);
        userPasswordResetRequestEdo.setPassword(password);
        this.userClient.resetPassword(authorization, userPasswordResetRequestEdo, userId);

        return password;
    }

    private String getStringFirstUpper(final String substring) {

        String res = substring.toLowerCase();
        res = res.length() > 3 ? res.substring(0, 3) : res;

        final String preparedText = res.substring(0, 1).toUpperCase() + res.substring(1);
        return preparedText;
    }

    private String getResetedPassword(UserEdo userEdo){
        return this.getStringFirstUpper(userEdo.getFirstName()) +
               this.getStringFirstUpper(userEdo.getLastName()) +
               "12345!#";
    }

    @Override
    public List<UserDashboardMenu> saveUserDashboardMenus(String authorization,
                                                          final List<UserDashboardMenu> userDashboardMenuList,
                                                          final UUID userId){

        for (final UserDashboardMenu item : userDashboardMenuList) {
            item.setAppId(EApplication.IFLOW.getIdentity());
            item.setUserId(userId);

        }

        List<UserDashboardMenuEdo> userDashboardMenuEdoList = userDashboardMenuMapper.toEdoList(userDashboardMenuList);

        UserDashboardMenuListEdo userDashboardMenuListEdo = new UserDashboardMenuListEdo(userDashboardMenuEdoList);

        final Optional<UserDashboardMenuListEdo> resultEdoListOptional =
                this.userClient.saveUserDashboardMenuByIdentity(authorization,
                                                                userDashboardMenuListEdo,
                                                                EApplication.IFLOW.getIdentity(),
                                                                userId);

        if(resultEdoListOptional.isPresent()){
            List<UserDashboardMenu> resultUserDashboardMenuList =
                    userDashboardMenuMapper.fromEdoList(resultEdoListOptional.get().getUserDashboardMenus());

            return resultUserDashboardMenuList;
        }

        return new ArrayList<>();
    }
}
