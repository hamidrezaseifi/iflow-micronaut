package com.pth.gui.helpers.ocr;

import com.pth.gui.models.gui.ocr.OcrResults;
import net.sourceforge.tess4j.TesseractException;

import java.io.IOException;

public interface IOcrHelper {

    String doOcr(String filePath) throws TesseractException;

    OcrResults generateResult(String hocrResult) throws TesseractException, IOException;
}
