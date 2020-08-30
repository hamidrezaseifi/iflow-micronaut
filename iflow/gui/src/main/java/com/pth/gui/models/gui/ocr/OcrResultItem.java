package com.pth.gui.models.gui.ocr;

public class OcrResultItem {

  protected String id;
  protected String title;
  protected String className;
  protected OcrResultBox box;

  public OcrResultItem() {

  }

  public String getId() {

    return this.id;
  }

  public void setId(final String id) {

    this.id = id;
  }

  public String getTitle() {

    return this.title;
  }

  public void setTitle(final String title) {

    this.title = title;
    this.box = new OcrResultBox(title);
  }

  public String getClassName() {

    return this.className;
  }

  public void setClassName(final String className) {

    this.className = className;
  }

  public OcrResultBox getBox() {

    return this.box;
  }

  public void setBox(final OcrResultBox box) {

    this.box = box;
  }

  public boolean isLeftOf(final OcrResultItem item) {

    return this.getBox().isLeftOf(item.getBox());
  }

  public boolean isRightOf(final OcrResultItem item) {

    return this.getBox().isRightOf(item.getBox());
  }

  public boolean isTopOf(final OcrResultItem item) {

    return this.getBox().isTopOf(item.getBox());
  }

  public boolean isBottomOf(final OcrResultItem item) {

    return this.getBox().isBottomOf(item.getBox());
  }

}
