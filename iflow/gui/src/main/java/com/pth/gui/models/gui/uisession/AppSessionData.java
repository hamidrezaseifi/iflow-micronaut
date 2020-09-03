package com.pth.gui.models.gui.uisession;

import com.pth.gui.models.gui.UiMenuItem;

import java.util.ArrayList;
import java.util.List;

public class AppSessionData {

    private List<UiMenuItem> menus;

    private DashboardSessionData dashboard;

    public AppSessionData() {
        menus = new ArrayList<>();
        dashboard = new DashboardSessionData();
    }

    public AppSessionData(List<UiMenuItem> menus, DashboardSessionData dashboard) {
        this.menus = menus;
        this.dashboard = dashboard;
    }

    public List<UiMenuItem> getMenus() {
        return menus;
    }

    public void setMenus(List<UiMenuItem> menus) {
        this.menus = menus;
    }

    public DashboardSessionData getDashboard() {
        return dashboard;
    }

    public void setDashboard(DashboardSessionData dashboard) {
        this.dashboard = dashboard;
    }
}
