package com.pth.gui.services;

import com.pth.gui.models.gui.UiMenuItem;

import java.util.List;

public interface IGuiMenuService {
    List<UiMenuItem> getAllMenus();

    List<UiMenuItem> getMenus(List<String> idList);

    void setMenus(List<UiMenuItem> menus);

    UiMenuItem getMenuItemByUrl(String url);
}
