package com.pth.gui.models.gui.ocr;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "parentPage", "parentLine" })
public class OcrResultWord extends OcrResultItem {

  private boolean isStrong;
  private String text;
  private OcrResultWord value;
  private final OcrResultLine parent;

  public OcrResultWord(final OcrResultLine parent) {

    this.parent = parent;
  }

  public boolean isStrong() {

    return this.isStrong;
  }

  public void setStrong(final boolean isStrong) {

    this.isStrong = isStrong;
  }

  public String getText() {

    return this.text;
  }

  public void setText(final String text) {

    this.text = text;
  }

  public OcrResultWord getValue() {

    return this.value;
  }

  public void setValue(final OcrResultWord value) {

    this.value = value;
  }

  public OcrResultLine getParentLine() {

    return this.parent;
  }

  public OcrResultPage getParentPage() {

    return this.parent.getParent().getParent().getParent();
  }

  public int getPageIndex() {

    return this.getParentPage().getPageNumber();
  }

  public int getPageWidth() {

    return this.getParentPage().getBox().getWidth();
  }

  public int getPageHeight() {

    return this.getParentPage().getBox().getHeight();
  }

  @Override
  public OcrResultWord clone() {

    final OcrResultWord word = new OcrResultWord(this.parent);
    word.setBox(this.box);
    word.setClassName(this.className);
    word.setId(this.id);
    word.setStrong(this.isStrong);
    word.setText(this.text);
    word.setTitle(this.title);

    return word;
  }

  public boolean IsValueTypeCorrect(final OcrResultValueType valueType) {

    if (StringUtils.isEmpty(this.text)) {
      return false;
    }

    if (valueType == OcrResultValueType.TEXT) {
      return this.text.isEmpty() == false;

    }

    if (valueType == OcrResultValueType.INTEGER) {
      return this.text.matches("-?(\\d+[.]){1,10}\\d+");

    }

    if (valueType == OcrResultValueType.FLOAT) {
      return this.text.matches("-?(\\d+[.]\\d+){1,10}[,]\\d+");

    }

    if (valueType == OcrResultValueType.DATE) {
      return this.text.matches("^[0-9]{2}.[0-9]{2}.[0-9]{4}");

    }

    return false;
  }
}
