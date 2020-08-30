package com.pth.gui.models.gui.ocr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class OcrResultLine extends OcrResultItem {

  private static final int WORD_VALUE_RIGHT_LEFT_DIFF = 150;
  private List<OcrResultWord> words = new ArrayList<>();
  private float baselineX, baselineY;
  private float xDescenders, xAscenders;
  private final OcrResultPar parent;

  public OcrResultLine(final OcrResultPar parent) {

    this.parent = parent;
  }

  public List<OcrResultWord> getWords() {

    return this.words;
  }

  public void setWords(final List<OcrResultWord> words) {

    this.words = words;
  }

  public void addWord(final OcrResultWord word) {

    this.words.add(word);

  }

  @Override
  public void setTitle(final String title) {

    super.setTitle(title);

    if (StringUtils.isEmpty(title) == false) {

      final String[] titleParts = title.split(";");

      for (final String s : titleParts) {
        if (s.contains("baseline")) {
          this.extractBaseLines(s);
          continue;
        }
        if (s.contains("x_descenders")) {
          final String str = s.replace("x_descenders", "").trim();
          this.xDescenders = Float.parseFloat(str);
          continue;
        }
        if (s.contains("x_ascenders")) {
          final String str = s.replace("x_ascenders", "").trim();
          this.xAscenders = Float.parseFloat(str);
          continue;
        }
      }

    }

  }

  private void extractBaseLines(final String s) {

    final String str = s.replace("baseline", "").trim();
    final String[] parts = str.split(" ");
    this.baselineX = Float.parseFloat(parts[0]);
    this.baselineY = Float.parseFloat(parts[1]);
  }

  public Collection<? extends OcrResultWord> findWord(final String searchWord, final boolean exact, final boolean caseSensitive,
      final OcrResultValueType valueType) {

    final List<OcrResultWord> foundWords = new ArrayList<>();
    String inSearchWord = searchWord.trim();
    for (final OcrResultWord word : this.words) {

      String text = word.getText().trim();
      if (caseSensitive == false) {
        text = text.toLowerCase();
        inSearchWord = inSearchWord.toLowerCase();
      }

      if (exact && inSearchWord.equals(text)) {
        foundWords.addAll(this.prepareValue(word, valueType));
      }
      else {
        if (text.contains(inSearchWord)) {
          foundWords.addAll(this.prepareValue(word, valueType));
        }
      }

    }

    return foundWords;
  }

  public float getBaselineX() {

    return this.baselineX;
  }

  public void setBaselineX(final float baselineX) {

    this.baselineX = baselineX;
  }

  public float getBaselineY() {

    return this.baselineY;
  }

  public void setBaselineY(final float baselineY) {

    this.baselineY = baselineY;
  }

  public float getxDescenders() {

    return this.xDescenders;
  }

  public void setxDescenders(final float xDescenders) {

    this.xDescenders = xDescenders;
  }

  public float getxAscenders() {

    return this.xAscenders;
  }

  public void setxAscenders(final float xAscenders) {

    this.xAscenders = xAscenders;
  }

  public OcrResultPar getParent() {

    return this.parent;
  }

  private List<OcrResultWord> prepareValue(final OcrResultWord word, final OcrResultValueType valueType) {

    final List<OcrResultWord> results = new ArrayList<>();
    final OcrResultWord clonedWord = word.clone();
    List<OcrResultWord> sortedWords = this.getSortedByX();

    for (int i = 0; i < sortedWords.size(); i++) {
      final OcrResultWord listWord = sortedWords.get(i);
      if (listWord.isRightOf(clonedWord)) {

        if (listWord.IsValueTypeCorrect(valueType)) {
          clonedWord.setValue(listWord.clone());
          results.add(clonedWord);
        }

        break;
      }

    }

    final OcrResultLine nextLine = this.parent.findBottomLine(this);
    if (nextLine != null) {
      sortedWords = nextLine.getSortedByX();
      for (int i = 0; i < sortedWords.size(); i++) {
        final OcrResultWord listWord = sortedWords.get(i);
        final int leftDiff = Math.abs(listWord.getBox().getLeft() - word.getBox().getLeft());
        final int rightDiff = Math.abs(listWord.getBox().getRight() - word.getBox().getRight());

        if (leftDiff < WORD_VALUE_RIGHT_LEFT_DIFF || rightDiff < WORD_VALUE_RIGHT_LEFT_DIFF) {
          if (listWord.IsValueTypeCorrect(valueType)) {
            clonedWord.setValue(listWord.clone());
            results.add(clonedWord);
          }

          break;
        }

      }
    }

    return results;
  }

  protected List<OcrResultWord> getSortedByX() {

    final List<OcrResultWord> sortedWords = new ArrayList<>();

    sortedWords.addAll(this.words);

    sortedWords.sort(new Comparator<OcrResultWord>() {

      @Override
      public int compare(final OcrResultWord word1, final OcrResultWord word2) {

        return word1.isLeftOf(word2) ? -1 : word1.isRightOf(word2) ? 1 : 0;
      }
    });

    return sortedWords;
  }

}
