package com.pth.gui.helpers;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("iflow.gui.tesseract")
public class GuiTesseractProperties
{
    private String tessDataPath;

    public GuiTesseractProperties() {
    }


    public String getTessDataPath() {
        return tessDataPath;
    }

    public void setTessDataPath(String tessDataPath) {
        this.tessDataPath = tessDataPath;
    }
}
