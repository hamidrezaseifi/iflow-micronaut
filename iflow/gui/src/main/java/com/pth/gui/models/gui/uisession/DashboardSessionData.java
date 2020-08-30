package com.pth.gui.models.gui.uisession;

import com.pth.gui.models.UserDashboardMenu;

import java.util.ArrayList;
import java.util.List;

public class DashboardSessionData {

    private int totalColumns;
    private int totalRows;
    private List<List<UserDashboardMenu>> dashboardMenus;

    public DashboardSessionData() {
        totalColumns = 0;
        totalRows = 0;
        dashboardMenus = new ArrayList<>();
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public List<List<UserDashboardMenu>> getDashboardMenus() {
        return dashboardMenus;
    }

    public void setDashboardMenus(List<List<UserDashboardMenu>> dashboardMenus) {
        this.dashboardMenus = dashboardMenus;
    }
}
