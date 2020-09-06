package com.pth.gui.models;

import java.util.UUID;

public class GuiBaseModel {

    protected UUID id;

    public GuiBaseModel() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public boolean hasId(UUID id) {
        return this.id == id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
