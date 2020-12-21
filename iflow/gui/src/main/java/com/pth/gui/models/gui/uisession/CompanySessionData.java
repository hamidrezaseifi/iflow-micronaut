package com.pth.gui.models.gui.uisession;

import com.pth.gui.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class CompanySessionData {
    private Company company;
    private List<Department> departments;
    private List<UserGroup> userGroups;
    private List<User> users;
    private List<CompanyWorkflowTypeController> workflowTypeControllers;
    private List<CompanyWorkflowtypeItemOcrSettingPreset> ocrPresets;


    public CompanySessionData() {
        this.company = new Company();
        this.departments = new ArrayList<>();
        this.userGroups = new ArrayList<>();
        this.users = new ArrayList<>();
        this.workflowTypeControllers = new ArrayList<>();
        this.ocrPresets = new ArrayList<>();
    }

    public CompanySessionData(Company company,
                              List<Department> departments,
                              List<UserGroup> userGroups,
                              List<User> users,
                              List<CompanyWorkflowTypeController> workflowTypeControllers,
                              List<CompanyWorkflowtypeItemOcrSettingPreset> ocrPresets) {
        this.company = company;
        this.departments = departments;
        this.userGroups = userGroups;
        this.users = users;
        this.workflowTypeControllers = workflowTypeControllers;
        this.ocrPresets = ocrPresets;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<CompanyWorkflowTypeController> getWorkflowTypeControllers() {
        return workflowTypeControllers;
    }

    public void setWorkflowTypeControllers(List<CompanyWorkflowTypeController> workflowTypeControllers) {
        this.workflowTypeControllers = workflowTypeControllers;
    }

    public List<CompanyWorkflowtypeItemOcrSettingPreset> getOcrPresets() {
        return ocrPresets;
    }

    public void setOcrPresets(List<CompanyWorkflowtypeItemOcrSettingPreset> ocrPresets) {
        this.ocrPresets = ocrPresets;
    }

    public Optional<CompanyWorkflowtypeItemOcrSettingPreset> findOcrPresetByName(String presetName){
        return this.ocrPresets.stream().filter( p -> p.hasPresetName(presetName)).findFirst();
    }

    public List<CompanyWorkflowTypeController> getControllerForWorkflowType(UUID workflowTypeId){

        return this.workflowTypeControllers.
                                           stream().
                                           filter(c -> c.getWorkflowTypeId().equals(workflowTypeId)).
                                           collect(Collectors.toList());
    }
}
