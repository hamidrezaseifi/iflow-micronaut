package com.pth.gui.models.gui.ocr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class OcrResultPage extends OcrResultItem {

  private List<OcrResultArea> areas = new ArrayList<>();

  private String image;

  private int pageNumber;

  public OcrResultPage() {

  }

  public List<OcrResultArea> getAreas() {

    return this.areas;
  }

  public void setAreas(final List<OcrResultArea> areas) {

    this.areas = areas;
  }

  public void addArea(final OcrResultArea area) {

    this.areas.add(area);
  }

  public String getImage() {

    return this.image;
  }

  public void setImage(final String image) {

    this.image = image;
  }

  public int getPageNumber() {

    return this.pageNumber;
  }

  public void setPageNumber(final int pageNumber) {

    this.pageNumber = pageNumber;
  }

  @Override
  public void setTitle(final String title) {

    super.setTitle(title);

    if (StringUtils.isEmpty(title) == false) {

      final String[] titleParts = title.split(";");

      for (final String s : titleParts) {

        if (s.contains("image")) {
          this.image = s.replace("image", "").trim();
          continue;
        }
        if (s.contains("ppageno")) {
          final String str = s.replace("ppageno", "").trim();
          this.pageNumber = Integer.parseInt(str);
          continue;
        }
      }

    }

  }

  public Collection<? extends OcrResultWord> findWord(final String searchWord, final boolean exact, final boolean caseSensitive,
      final OcrResultValueType valueType) {

    final List<OcrResultWord> words = new ArrayList<>();

    for (final OcrResultArea area : this.areas) {
      words.addAll(area.findWord(searchWord, exact, caseSensitive, valueType));
    }

    return words;
  }
}
