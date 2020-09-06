package com.pth.gui.services.impl;

import com.pth.clients.workflow.IWorkflowMessageClient;
import com.pth.common.edo.WorkflowMessageListEdo;
import com.pth.gui.mapper.IWorkflowMessageMapper;
import com.pth.gui.models.gui.cach.CompanyCachData;
import com.pth.gui.models.gui.cach.UserCachData;
import com.pth.gui.models.workflow.WorkflowMessage;
import com.pth.gui.services.ICompanyCachDataManager;
import com.pth.gui.services.ISocketUserMessageManager;

import javax.inject.Singleton;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class CompanyDataManager implements ICompanyCachDataManager {

    private final IWorkflowMessageClient workflowMessageClient;
    private final ISocketUserMessageManager socketUserManager;
    private final IWorkflowMessageMapper workflowMessageMapper;

    private final Map<UUID, CompanyCachData> companiesCachData = new HashMap<>();

    public CompanyDataManager(IWorkflowMessageClient workflowMessageClient,
                              ISocketUserMessageManager socketUserManager,
                              IWorkflowMessageMapper workflowMessageMapper) {

        this.workflowMessageClient = workflowMessageClient;
        this.socketUserManager = socketUserManager;
        this.workflowMessageMapper = workflowMessageMapper;
    }

    public Map<UUID, CompanyCachData> getCompaniesCachData() {

        return this.companiesCachData;
    }

    public void setCompaniesCachData(final List<CompanyCachData> companiesCachData) {

        this.companiesCachData.clear();
        if (companiesCachData != null) {
            this.companiesCachData.putAll(companiesCachData.stream().collect(
                    Collectors.toMap(cd -> cd.getCompanyId(), cd -> cd)));
        }

    }

    @Override
    public void setUserWorkflowMessages(final UUID companyId, final UUID userId, final List<WorkflowMessage> workflowMessageList) {

        this.getUserCachData(companyId, userId).setWorkflowMessageList(workflowMessageList);

    }

    public List<UUID> setWorkflowWorkflowMessages(final UUID companyId,
                                                    final UUID workflowId,
                                                    final List<WorkflowMessage> workflowMessageList) {

        CompanyCachData companyCachData = this.getCompanyCachData(companyId, true);

        return companyCachData.setWorkflowWorkflowMessages(workflowId,
                                                           workflowMessageList,
                                                           this);

    }

    @Override
    public List<WorkflowMessage> getUserWorkflowMessages(final UUID companyId, final UUID userId) {

        return this.getUserCachData(companyId, userId).getWorkflowMessagesList();
    }

    @Override
    public void resetUserData(final UUID companyId,
                              final UUID userId,
                              final String authorization,
                              final boolean fromController){

        final Optional<WorkflowMessageListEdo> messageListEdoOptional =
                this.workflowMessageClient.readUserWorkflowMessageList(authorization, userId);

        if(messageListEdoOptional.isPresent()){
            final List<WorkflowMessage> messageList =
                    this.workflowMessageMapper.fromEdoList(messageListEdoOptional.get().getWorkflowMessages());
            this.removeUserMessages(companyId, userId);
            this.setUserWorkflowMessages(companyId, userId, messageList);

            if (fromController) {
                this.sendResetMessageToSocket(userId);
            }
        }


    }

    @Override
    public void resetWorkflowStepData(final UUID companyId, final UUID workflowId, final String authorization){

        final Optional<WorkflowMessageListEdo> messageListEdoOptional =
                this.workflowMessageClient.readWorkflowWorkflowMessageList(authorization, workflowId);

        if(messageListEdoOptional.isPresent()) {
            final List<WorkflowMessage> messageList =
                    this.workflowMessageMapper.fromEdoList(messageListEdoOptional.get().getWorkflowMessages());

            final List<UUID> userIdentityList = this.removeWorkflowMessages(companyId, workflowId);

            final List<UUID> resetUserIdentityList =
                    this.setWorkflowWorkflowMessages(companyId, workflowId, messageList);

            userIdentityList.removeAll(resetUserIdentityList);

            for (final UUID userIdentity : userIdentityList) {
                this.sendResetMessageToSocket(userIdentity);
            }
        }
    }

    @Override
    public void resetUserListData(final UUID companyId,
                                  final Collection<UUID> userIdList,
                                  final String authorization) {

        for (final UUID userId : userIdList) {
            final Optional<WorkflowMessageListEdo> messageListEdoOptional =
                    this.workflowMessageClient.readUserWorkflowMessageList(authorization, userId);

            if(messageListEdoOptional.isPresent()) {
                final List<WorkflowMessage> messageList =
                        this.workflowMessageMapper.fromEdoList(messageListEdoOptional.get().getWorkflowMessages());

                this.setUserWorkflowMessages(companyId, userId, messageList);
                this.sendResetMessageToSocket(userId);
            }
        }
    }

    public void addCompanyCachData(final CompanyCachData companyCachData) {

        this.companiesCachData.put(companyCachData.getCompanyId(), companyCachData);
    }

    @Override
    public void sendResetMessageToSocket(final UUID userId) {

        final Map<String, Object> map = new HashMap<>();
        map.put("command", "message-reload");
        map.put("status", "done");

        socketUserManager.sendMessage(userId, map);
    }

    private CompanyCachData getCompanyCachData(final UUID companyId, final boolean initialCompanyCachData) {

        if (this.companiesCachData.containsKey(companyId) == false && initialCompanyCachData) {
            this.initialCompanyCachData(companyId);
        }
        if (this.companiesCachData.containsKey(companyId)) {
            return this.companiesCachData.get(companyId);
        }
        return null;

    }

    private void removeUserMessages(final UUID companyId, final UUID userId) {

        final CompanyCachData companyCachData = this.getCompanyCachData(companyId, true);

        companyCachData.removeUserCachData(userId);

    }

    private List<UUID> removeWorkflowMessages(final UUID companyId, final UUID workflowId) {

        final CompanyCachData companyCachData = this.getCompanyCachData(companyId, true);

        return companyCachData.removeWorkflowCachData(workflowId);

    }

    private UserCachData getUserCachData(final UUID companyId, final UUID userId) {

        final CompanyCachData companyCachData = this.getCompanyCachData(companyId, true);

        return companyCachData.getUserCachData(userId, true);
    }

    private boolean hasCompanyCachData(final UUID companyId) {

        return this.companiesCachData.containsKey(companyId);
    }

    private void initialCompanyCachData(final UUID companyId) {

        if (this.hasCompanyCachData(companyId) == false) {
            final CompanyCachData companyCachData = new CompanyCachData(companyId);
            this.addCompanyCachData(companyCachData);
        }
    }

}
