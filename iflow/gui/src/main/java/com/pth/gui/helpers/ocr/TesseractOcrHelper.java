package com.pth.gui.helpers.ocr;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.pth.gui.helpers.GuiTesseractProperties;
import com.pth.gui.helpers.MergeDuplicateFieldsJsonNodeDeserializer;
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
    private final XmlMapper xmlMapper;


    public TesseractOcrHelper(GuiTesseractProperties tesseractProperties) {
        this.tesseractProperties = tesseractProperties;

        final SimpleModule mergeDuplicatesModule = new SimpleModule("Merge duplicated fields in array");
        mergeDuplicatesModule.addDeserializer(JsonNode.class, new MergeDuplicateFieldsJsonNodeDeserializer());

        this.xmlMapper = new XmlMapper();
        this.xmlMapper.registerModule(mergeDuplicatesModule);

        this.tesseract = new Tesseract();
        this.tesseract.setDatapath(this.tesseractProperties.getTessDataPath());
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
    public OcrResults generateResult(String hocrResult) throws TesseractException, IOException {

        final OcrResults ocrResults = loadFromHocrText(hocrResult);
        return ocrResults;
    }

    private OcrResults loadFromHocrText(final String hocrText) throws IOException {

        final OcrResults results = this.xmlMapper.readValue(hocrText, OcrResults.class);
        return results;
    }

}
