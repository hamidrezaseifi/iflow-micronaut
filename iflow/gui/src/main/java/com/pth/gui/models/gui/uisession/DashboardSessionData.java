package com.pth.gui.models.gui.uisession;

import com.pth.gui.models.UserDashboardMenu;

import java.util.ArrayList;
import java.util.List;

public class DashboardSessionData {

    public static final int DEFAULT_TOTAL_COLUMNS = 10;
    public static final int DEFAULT_TOTAL_ROWS = 10;
    private int totalColumns;
    private int totalRows;
    private List<List<UserDashboardMenu>> dashboardMenus;

    public DashboardSessionData() {
        this(DEFAULT_TOTAL_COLUMNS, DEFAULT_TOTAL_ROWS, new ArrayList<>());
    }

    public DashboardSessionData(List<List<UserDashboardMenu>> dashboardMenus) {
        this(DEFAULT_TOTAL_COLUMNS, DEFAULT_TOTAL_ROWS, dashboardMenus);
    }

    public DashboardSessionData(int totalColumns, int totalRows, List<List<UserDashboardMenu>> dashboardMenus) {

        this.totalColumns = totalColumns;
        this.totalRows = totalRows;
        this.dashboardMenus = dashboardMenus;
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
