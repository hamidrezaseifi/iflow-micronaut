package com.pth.gui.models.gui.uisession;

import com.pth.gui.models.Company;
import com.pth.gui.models.Department;
import com.pth.gui.models.User;

import java.util.List;

public class CompanySessionData {
    private Company company;
    private List<Department> departments;
    private User users;

    public CompanySessionData() {
    }

    public CompanySessionData(Company company,
                              List<Department> departments,
                              User users) {
        this.company = company;
        this.departments = departments;
        this.users = users;
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

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
