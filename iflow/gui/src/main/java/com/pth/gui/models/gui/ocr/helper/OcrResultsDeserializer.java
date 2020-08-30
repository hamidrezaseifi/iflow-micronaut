package com.pth.gui.models.gui.ocr.helper;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.pth.gui.models.gui.ocr.*;


public class OcrResultsDeserializer extends StdDeserializer<OcrResults> {

  private static final long serialVersionUID = 1490421967633952428L;

  public OcrResultsDeserializer() {

    this(null);
  }

  public OcrResultsDeserializer(final Class<?> vc) {

    super(vc);
  }

  @Override
  public OcrResults deserialize(final JsonParser jp, final DeserializationContext ctxt)
      throws IOException, JsonProcessingException {

    final OcrResults ocrResults = new OcrResults();

    final JsonNode rootNode = jp.getCodec().readTree(jp);

    final JsonNode bodyNode = rootNode.get("body");

    final JsonNode pageRootNode = bodyNode.get("div");

    if (pageRootNode.isArray()) {
      final ArrayNode pageNodes = (ArrayNode) pageRootNode;

      for (int i = 0; i < pageNodes.size(); i++) {
        final JsonNode pageNode = pageNodes.get(i);

        final OcrResultPage ocrPage = this.extractOcrResultPage(pageNode);

        ocrResults.addPage(ocrPage);
      }
    }
    else {
      final OcrResultPage ocrPage = this.extractOcrResultPage(pageRootNode);
      ocrResults.addPage(ocrPage);
    }

    return ocrResults;
  }

  private OcrResultPage extractOcrResultPage(final JsonNode pageNode) {

    final OcrResultPage ocrPage = new OcrResultPage();

    final Iterator<Entry<String, JsonNode>> fields = pageNode.fields();

    while (fields.hasNext()) {
      final Entry<String, JsonNode> jsonField = fields.next();

      if (this.setIfIsGeneralInfo(jsonField, ocrPage)) {

        continue;
      }

      if (jsonField.getKey().toLowerCase().equals("div")) {

        final JsonNode parRootNode = jsonField.getValue();

        if (parRootNode.isArray()) {
          final ArrayNode nodes = (ArrayNode) parRootNode;

          for (int i = 0; i < nodes.size(); i++) {
            final JsonNode node = nodes.get(i);

            final OcrResultArea ocrArea = this.extractOcrResultArea(node, ocrPage);

            ocrPage.addArea(ocrArea);
          }
        }
        else {
          final OcrResultArea ocrArea = this.extractOcrResultArea(parRootNode, ocrPage);
          ocrPage.addArea(ocrArea);
        }

        continue;

      }
    }

    return ocrPage;
  }

  private OcrResultArea extractOcrResultArea(final JsonNode rootNode, final OcrResultPage ocrPage) {

    final OcrResultArea ocrArea = new OcrResultArea(ocrPage);

    final Iterator<Entry<String, JsonNode>> fields = rootNode.fields();

    while (fields.hasNext()) {
      final Entry<String, JsonNode> jsonField = fields.next();

      if (this.setIfIsGeneralInfo(jsonField, ocrArea)) {

        continue;
      }

      if (jsonField.getKey().toLowerCase().equals("p")) {

        final JsonNode parRootNode = jsonField.getValue();

        if (parRootNode.isArray()) {
          final ArrayNode nodes = (ArrayNode) parRootNode;

          for (int i = 0; i < nodes.size(); i++) {
            final JsonNode node = nodes.get(i);

            final OcrResultPar ocrPar = this.extractOcrResultPar(node, ocrArea);

            ocrArea.addPar(ocrPar);
          }
        }
        else {
          final OcrResultPar ocrPar = this.extractOcrResultPar(parRootNode, ocrArea);
          ocrArea.addPar(ocrPar);
        }

        continue;

      }
    }

    return ocrArea;
  }

  private OcrResultPar extractOcrResultPar(final JsonNode rootNode, final OcrResultArea ocrArea) {

    final OcrResultPar ocrPar = new OcrResultPar(ocrArea);

    final Iterator<Entry<String, JsonNode>> fields = rootNode.fields();

    while (fields.hasNext()) {
      final Entry<String, JsonNode> jsonField = fields.next();

      if (this.setIfIsGeneralInfo(jsonField, ocrPar)) {
        continue;
      }

      if (jsonField.getKey().toLowerCase().equals("span")) {

        final JsonNode parRootNode = jsonField.getValue();

        if (parRootNode.isArray()) {
          final ArrayNode nodes = (ArrayNode) parRootNode;

          for (int i = 0; i < nodes.size(); i++) {
            final JsonNode node = nodes.get(i);

            final OcrResultLine ocrLine = this.extractOcrResultLine(node, ocrPar);

            ocrPar.addLine(ocrLine);
          }
        }
        else {
          final OcrResultLine ocrLine = this.extractOcrResultLine(parRootNode, ocrPar);
          ocrPar.addLine(ocrLine);
        }

        continue;

      }
    }

    return ocrPar;
  }

  private OcrResultLine extractOcrResultLine(final JsonNode rootNode, final OcrResultPar ocrPar) {

    final OcrResultLine ocrLine = new OcrResultLine(ocrPar);

    final Iterator<Entry<String, JsonNode>> fields = rootNode.fields();

    while (fields.hasNext()) {
      final Entry<String, JsonNode> jsonField = fields.next();

      if (this.setIfIsGeneralInfo(jsonField, ocrLine)) {
        continue;
      }

      if (jsonField.getKey().toLowerCase().equals("span")) {

        final JsonNode parRootNode = jsonField.getValue();

        if (parRootNode.isArray()) {
          final ArrayNode nodes = (ArrayNode) parRootNode;

          for (int i = 0; i < nodes.size(); i++) {
            final JsonNode node = nodes.get(i);

            final OcrResultWord ocrWord = this.extractOcrResultWord(node, ocrLine);

            ocrLine.addWord(ocrWord);
          }
        }
        else {
          final OcrResultWord ocrWord = this.extractOcrResultWord(parRootNode, ocrLine);
          ocrLine.addWord(ocrWord);
        }

        continue;

      }
    }

    return ocrLine;
  }

  private OcrResultWord extractOcrResultWord(final JsonNode rootNode, final OcrResultLine ocrLine) {

    final OcrResultWord ocrWord = new OcrResultWord(ocrLine);
    ocrWord.setStrong(false);

    final Iterator<Entry<String, JsonNode>> fields = rootNode.fields();

    while (fields.hasNext()) {
      final Entry<String, JsonNode> jsonField = fields.next();

      if (this.setIfIsGeneralInfo(jsonField, ocrWord)) {
        continue;
      }

      if (jsonField.getKey().toLowerCase().equals("strong")) {

        ocrWord.setStrong(true);

        ocrWord.setText(jsonField.getValue().asText());

        continue;

      }

      if (jsonField.getValue().isTextual()) {

        ocrWord.setText(jsonField.getValue().asText());

        continue;

      }
    }

    return ocrWord;
  }

  private boolean setIfIsGeneralInfo(final Entry<String, JsonNode> jsonField, final OcrResultItem item) {

    if (jsonField.getKey().toLowerCase().equals("id")) {
      item.setId(jsonField.getValue().asText());
      return true;
    }
    if (jsonField.getKey().toLowerCase().equals("title")) {
      item.setTitle(jsonField.getValue().asText());
      return true;
    }
    if (jsonField.getKey().toLowerCase().equals("class")) {
      item.setClassName(jsonField.getValue().asText());
      return true;
    }
    return false;
  }

}
