package com.pth.gui.services.impl;

import com.pth.clients.clients.profile.IUserClient;
import com.pth.common.edo.*;
import com.pth.common.edo.enums.EApplication;
import com.pth.gui.mapper.IUserDashboardMenuMapper;
import com.pth.gui.mapper.IUserMapper;
import com.pth.gui.models.User;
import com.pth.gui.models.UserDashboardMenu;
import com.pth.gui.services.IUserHandler;
import org.apache.commons.lang3.RandomStringUtils;

import javax.inject.Singleton;
import java.util.*;

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
    public Optional<User> createUser(String authorization, final User user) {

        initialUserData(user);

        final Optional<UserEdo> savedUserOptional = this.userClient.createUser(authorization, userMapper.toEdo(user));
        if(savedUserOptional.isPresent()){
            return Optional.of(userMapper.fromEdo(savedUserOptional.get()));
        }
        return Optional.empty();
    }

    private void initialUserData(User user) {
        user.setUsername(user.getEmail());

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
                                   User user,
                                   String password ,
                                   boolean resetPassword) {

        if (resetPassword) {
            password = this.getResetedPassword(user);
        }

        UserPasswordResetRequestEdo userPasswordResetRequestEdo = new UserPasswordResetRequestEdo();
        userPasswordResetRequestEdo.setAppId(EApplication.IFLOW.getIdentity());
        userPasswordResetRequestEdo.setUserId(user.getId());
        userPasswordResetRequestEdo.setPassword(password);
        this.userClient.resetPassword(authorization, userPasswordResetRequestEdo, user.getId());

        return password;
    }

    private String getResetedPassword(User user){

        String generatedString1 = RandomStringUtils.randomAlphanumeric(4);
        String generatedString2 = RandomStringUtils.randomAlphanumeric(4);
        return generatedString1.toUpperCase() + "!"+ generatedString2.toLowerCase() + "#";
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
