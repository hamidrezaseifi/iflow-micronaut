package com.pth.gui.models.gui.ocr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class OcrResultPar extends OcrResultItem {

  private List<OcrResultLine> lines = new ArrayList<>();
  private OcrResultArea parent = null;

  public OcrResultPar(final OcrResultArea parent) {

    this.parent = parent;
  }

  public List<OcrResultLine> getLines() {

    return this.lines;
  }

  public void setLines(final List<OcrResultLine> lines) {

    this.lines = lines;
  }

  public void addLine(final OcrResultLine line) {

    this.lines.add(line);
  }

  public Collection<? extends OcrResultWord> findWord(final String searchWord, final boolean exact, final boolean caseSensitive,
      final OcrResultValueType valueType) {

    final List<OcrResultWord> words = new ArrayList<>();

    for (final OcrResultLine line : this.lines) {
      words.addAll(line.findWord(searchWord, exact, caseSensitive, valueType));
    }

    return words;
  }

  public OcrResultLine findBottomLine(final OcrResultLine compareLine) {

    final List<OcrResultLine> sortedLines = this.getSortedByY();

    for (int i = 0; i < sortedLines.size(); i++) {
      final OcrResultLine line = sortedLines.get(i);
      if (line.isBottomOf(compareLine)) {

        return line;
      }

    }
    return null;
  }

  private List<OcrResultLine> getSortedByY() {

    final List<OcrResultLine> sorted = new ArrayList<>();

    sorted.addAll(this.lines);

    sorted.sort(new Comparator<OcrResultLine>() {

      @Override
      public int compare(final OcrResultLine line1, final OcrResultLine line2) {

        return line1.isTopOf(line2) ? -1 : line1.isBottomOf(line2) ? 1 : 0;
      }
    });

    return sorted;
  }

  public OcrResultArea getParent() {

    return this.parent;
  }

}
