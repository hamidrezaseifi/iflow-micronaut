package com.pth.gui.models.gui.ocr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OcrResultArea extends OcrResultItem {

  private List<OcrResultPar> pars = new ArrayList<>();
  private OcrResultPage parent = null;

  public OcrResultArea(final OcrResultPage parent) {

    this.parent = parent;
  }

  public List<OcrResultPar> getPars() {

    return this.pars;
  }

  public void setPars(final List<OcrResultPar> pars) {

    this.pars = pars;
  }

  public void addPar(final OcrResultPar par) {

    this.pars.add(par);
  }

  public Collection<? extends OcrResultWord> findWord(final String searchWord, final boolean exact, final boolean caseSensitive,
      final OcrResultValueType valueType) {

    final List<OcrResultWord> words = new ArrayList<>();

    for (final OcrResultPar par : this.pars) {
      words.addAll(par.findWord(searchWord, exact, caseSensitive, valueType));
    }

    return words;
  }

  public OcrResultPage getParent() {

    return this.parent;
  }

}
