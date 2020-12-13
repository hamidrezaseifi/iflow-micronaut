package com.pth.gui.models;

import java.util.UUID;

public class GuiBaseModel {

    protected UUID id;
    protected Integer version;

    public GuiBaseModel() {
        this.id = UUID.randomUUID();
        this.version = 1;
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

    public Integer getVersion() {

        return this.version;
    }

    /**
     * @param version the version to set
     */

    public void setVersion(final Integer version) {

        this.version = version;
    }

}
