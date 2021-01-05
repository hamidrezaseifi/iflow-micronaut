package com.pth.gui.models.gui.ocr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.pth.gui.helpers.MergeDuplicateFieldsJsonNodeDeserializer;
import com.pth.gui.models.gui.ocr.helper.OcrResultsDeserializer;

@JsonDeserialize(using = OcrResultsDeserializer.class)
public class OcrResults {

  private List<OcrResultPage> pages = new ArrayList<>();

  public OcrResults() {

  }

  public List<OcrResultPage> getPages() {

    return this.pages;
  }

  public void setPages(final List<OcrResultPage> pages) {

    this.pages = pages;
  }

  public void addPage(final OcrResultPage page) {

    this.pages.add(page);
  }

  public Set<OcrResultWord> findWord(final String searchWord, final boolean exact, final boolean caseSensitive,
      final OcrResultValueType valueType) {

    final Set<OcrResultWord> words = new HashSet<>();

    for (final OcrResultPage page : this.pages) {
      words.addAll(page.findWord(searchWord, exact, caseSensitive, valueType));
    }

    return words;
  }

  public Set<OcrResultWord> findWords(final String[] searchWords, final boolean exact, final boolean caseSensitive,
      final OcrResultValueType valueType) {

    final Set<OcrResultWord> words = new HashSet<>();

    if (searchWords.length > 0) {
      for (final String searchWord : searchWords) {

        if (searchWord.trim().isEmpty() == false) {
          final Set<OcrResultWord> foundWords = this.findWord(searchWord.trim(), exact, caseSensitive, valueType);
          words.addAll(foundWords);

        }

      }
    }

    return words;
  }

}
