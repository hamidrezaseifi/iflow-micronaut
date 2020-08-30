package com.pth.gui.models.gui.ocr;

import org.apache.commons.lang3.StringUtils;

public class OcrResultBox {

  private int left, right, top, bottom;

  public OcrResultBox() {

  }

  public OcrResultBox(final int left, final int right, final int top, final int bottom) {

    this.left = left;
    this.right = right;
    this.top = top;
    this.bottom = bottom;
  }

  public OcrResultBox(final String title) {

    if (StringUtils.isEmpty(title) == false && title.contains("bbox")) {

      String[] parts = title.split(";");

      String boxPart = "";
      for (final String s : parts) {
        if (s.contains("bbox")) {
          boxPart = s;
          break;
        }
      }

      if (StringUtils.isEmpty(boxPart) == false) {
        boxPart = boxPart.replace("bbox", "").trim();
        parts = boxPart.split(" ");

        if (parts.length >= 4) {
          this.left = Integer.parseInt(parts[0]);
          this.right = Integer.parseInt(parts[2]);
          this.top = Integer.parseInt(parts[1]);
          this.bottom = Integer.parseInt(parts[3]);
        }

      }
    }

  }

  public int getLeft() {

    return this.left;
  }

  public void setLeft(final int left) {

    this.left = left;
  }

  public int getRight() {

    return this.right;
  }

  public void setRight(final int right) {

    this.right = right;
  }

  public int getTop() {

    return this.top;
  }

  public void setTop(final int top) {

    this.top = top;
  }

  public int getBottom() {

    return this.bottom;
  }

  public void setBottom(final int bottom) {

    this.bottom = bottom;
  }

  public int getWidth() {

    return this.right - this.left;
  }

  public int getHeight() {

    return this.bottom - this.top;
  }

  public boolean isLeftOf(final OcrResultBox box) {

    return box.getLeft() > this.left;
  }

  public boolean isRightOf(final OcrResultBox box) {

    return box.getRight() < this.left;
  }

  public boolean isTopOf(final OcrResultBox box) {

    return box.getTop() > this.bottom;
  }

  public boolean isBottomOf(final OcrResultBox box) {

    return box.getBottom() < this.top;
  }

}
