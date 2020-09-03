package com.pth.gui.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pth.gui.models.gui.UiMenuItem;
import com.pth.gui.services.IGuiMenuService;
import io.micronaut.core.io.ResourceResolver;
import io.micronaut.core.io.scan.ClassPathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class GuiMenuService implements IGuiMenuService {

    private final Logger logger = LoggerFactory.getLogger(GuiMenuService.class);

    private List<UiMenuItem> menus;

    public GuiMenuService() {

        this.menus = new ArrayList<>();
    }

    @PostConstruct
    public void reload() throws IOException {

        this.logger.debug("Reading menu access.");

        final List<UiMenuItem> menulist;

        ClassPathResourceLoader loader = new ResourceResolver().getLoader(ClassPathResourceLoader.class).get();
        Optional<InputStream> resourceOptional = loader.getResourceAsStream("classpath:config/menulist.json");

        ObjectMapper objectMapper = new ObjectMapper();

        if(resourceOptional.isPresent()){
            InputStream resource = resourceOptional.get();

            menulist = objectMapper.readValue(resource, new TypeReference<List<UiMenuItem>>() {
            });

            for (final UiMenuItem item : menulist) {
                // item.setLabelsFromMessage(this.messages);
            }

            this.menus.clear();
            this.menus.addAll(menulist);

            this.logger.debug("Found {} menu item(s).", this.menus.size());


        }


    }

    @Override
    public List<UiMenuItem> getAllMenus() {

        return this.menus;
    }

    @Override
    public List<UiMenuItem> getMenus(final List<String> idList) {

        final List<UiMenuItem> list = new ArrayList<>();
        for (final UiMenuItem menu : this.menus) {
            if (idList.contains(menu.getId())) {
                list.add(menu);
            }
        }
        return list;
    }

    @Override
    public void setMenus(final List<UiMenuItem> menus) {

        this.menus = menus;
    }

    @Override
    public UiMenuItem getMenuItemByUrl(final String url) {

        for (final UiMenuItem item : this.menus) {
            final UiMenuItem f = item.getMenuItemByUrl(url);
            if (f != null) {
                return f;
            }
        }

        return null;
    }
}
