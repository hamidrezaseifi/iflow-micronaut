package com.pth.gui.models.gui.uisession;

import com.pth.gui.models.UserDashboardMenu;
import com.pth.gui.models.gui.UiMenuItem;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DashboardSessionData {

    public static final int DEFAULT_TOTAL_COLUMNS = 10;
    public static final int DEFAULT_TOTAL_ROWS = 6;
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


    public static List<List<UserDashboardMenu>>
        getPreparedUserDashboardMenus(List<UserDashboardMenu> dashboardMenus, final List<UiMenuItem> menuList) {

        final List<List<UserDashboardMenu>> dashboardMenuList = new ArrayList<>();

        for (int r = 1; r <= DEFAULT_TOTAL_ROWS; r++) {
            final List<UserDashboardMenu> row = new ArrayList<>();

            for (int c = 1; c <= DEFAULT_TOTAL_COLUMNS; c++) {

                UserDashboardMenu item = findUserDashboardMenu(dashboardMenus, menuList, r, c);

                if (item == null) {
                    item = initializeUserDashboardMenu(r, c);
                }

                row.add(item);
            }

            dashboardMenuList.add(row);
        }

        return dashboardMenuList;
    }

    private static UserDashboardMenu findUserDashboardMenu(List<UserDashboardMenu> dashboardMenus,
                                                           final List<UiMenuItem> menuList,
                                                           final int r,
                                                           final int c) {

        UserDashboardMenu item = null;
        for (final UserDashboardMenu searchItem : dashboardMenus) {
            if (searchItem.getRowIndex() == r && searchItem.getColumnIndex() == c) {
                item = searchItem;

                UiMenuItem menuItem = findMenuById(item.getMenuId(), menuList);
                item.setMenu(menuItem);

                break;
            }
        }
        return item;
    }

    private static UserDashboardMenu initializeUserDashboardMenu(final int r, final int c) {

        UserDashboardMenu item;
        item = new UserDashboardMenu();
        item.setMenu(null);

        item.setRowIndex(r);
        item.setColumnIndex(c);
        return item;
    }

    private static UiMenuItem findMenuById(String menuId, final List<UiMenuItem> menuList){
        for(UiMenuItem menuItem: menuList){
            if (menuItem.getId().equals(menuId)) {
                return menuItem;
            }

            UiMenuItem subMenuItem = findMenuById(menuId, menuItem.getChildren());
            if(subMenuItem != null){
                return subMenuItem;
            }
        }

        return null;
    }
}
