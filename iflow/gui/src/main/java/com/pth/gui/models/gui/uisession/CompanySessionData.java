package com.pth.gui.models.gui.uisession;

import com.pth.gui.models.Company;
import com.pth.gui.models.Department;
import com.pth.gui.models.User;
import com.pth.gui.models.UserGroup;

import java.util.List;

public class CompanySessionData {
    private Company company;
    private List<Department> departments;
    private List<UserGroup> userGroups;

    public CompanySessionData() {
    }

    public CompanySessionData(Company company,
                              List<Department> departments,
                              List<UserGroup> userGroups) {
        this.company = company;
        this.departments = departments;
        this.userGroups = userGroups;
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
}
