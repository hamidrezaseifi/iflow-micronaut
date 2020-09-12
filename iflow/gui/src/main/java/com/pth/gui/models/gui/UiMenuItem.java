package com.pth.gui.models.gui;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pth.gui.services.IMessagesHelper;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(
  {
      "parent" }
)
@Component
@JsonInclude(JsonInclude.Include.ALWAYS)
public class UiMenuItem {

  private String label;
  private String url;
  private String image;
  private List<UiMenuItem> children = new ArrayList<>();
  private String id;
  private String backColor;
  private String foreColor;
  private int status;

  private UiMenuItem parent;

  public UiMenuItem() {

    this.label = "";
    this.url = "#";
    this.image = "";
    this.id = "";
    this.backColor = "#ffffff";
    this.foreColor = "#000000";
    this.parent = null;
  }

  public String getLabel() {

    return this.label;
  }

  public void setLabel(final String label) {

    this.label = label;
  }

  public String getUrl() {

    return this.url;
  }

  public void setUrl(final String url) {

    this.url = url;
  }

  public String getImage() {

    return this.image;
  }

  public void setImage(final String image) {

    this.image = image;
  }

  public List<UiMenuItem> getChildren() {

    return this.children;
  }

  public boolean hasChildren() {

    return this.children.size() > 0;
  }

  public void setChildren(final List<UiMenuItem> children) {

    this.children = children == null ? new ArrayList<>() : children;
  }

  public void addchildren(final List<UiMenuItem> children) {

    this.children.clear();
    for (final UiMenuItem item : children) {
      item.setParent(this);
      this.children.add(item);
    }
  }

  public String getId() {

    return this.id;
  }

  public void setId(final String id) {

    this.id = id;
  }

  public boolean isEnabled() {

    return this.status == 1;
  }

  public int getStatus() {

    return this.status;
  }

  public void setStatus(final int status) {

    this.status = status;
  }

  public UiMenuItem getParent() {

    return this.parent;
  }

  public void setParent(final UiMenuItem parent) {

    this.parent = parent;
  }

  public String getBackColor() {

    return this.backColor;
  }

  public void setBackColor(final String backColor) {

    this.backColor = backColor;
  }

  public String getForeColor() {

    return this.foreColor;
  }

  public void setForeColor(final String foreColor) {

    this.foreColor = foreColor;
  }

  public String getCssClass(final String extraClass) {

    return this.image + " " + extraClass;
  }

  public boolean hasImage() {

    return (this.image != null) && (this.image.trim().length() > 3);
  }

  public UiMenuItem getMenuItemByUrl(final String url) {

    if (this.url.equals(url)) {
      return this;
    }

    for (final UiMenuItem item : this.children) {
      final UiMenuItem f = item.getMenuItemByUrl(url);
      if (f != null) {
        return f;
      }
    }
    return null;
  }

  /**
   * read menu label from messages
   *
   * @param messages
   */
  public void setLabelsFromMessage(final IMessagesHelper messages) {

    this.label = messages.get(this.id);
    for (final UiMenuItem item : this.children) {
      item.setLabelsFromMessage(messages);
    }
  }

}
