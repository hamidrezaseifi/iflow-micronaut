package com.pth.gui.helpers.ocr;

import com.pth.gui.helpers.GuiTesseractProperties;
import com.pth.gui.helpers.ocr.IOcrHelper;
import com.pth.gui.models.gui.ocr.OcrResults;
import io.micronaut.context.annotation.ConfigurationProperties;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;

@Singleton
@ConfigurationProperties("iflow.gui.tesseract")
public class TesseractOcrHelper implements IOcrHelper {

    private final Tesseract tesseract;
    private final GuiTesseractProperties tesseractProperties;


    public TesseractOcrHelper(GuiTesseractProperties tesseractProperties) {
        this.tesseractProperties = tesseractProperties;

        this.tesseract = new Tesseract();
        this.tesseract.setDatapath(tesseractProperties.getTessDataPath());
        this.tesseract.setLanguage("deu");
        this.tesseract.setHocr(true);
    }

    @Override
    public String doOcr(String filePath) throws TesseractException {
        final File file = new File(filePath);
        final String hocrResult = this.tesseract.doOCR(file);
        return hocrResult;
    }

    @Override
    public OcrResults doOcrGenerateResult(String filePath) throws TesseractException, IOException {

        final String hocrResult = this.doOcr(filePath);
        final OcrResults ocrResults = OcrResults.loadFromHocrText(hocrResult);
        return ocrResults;
    }

}
