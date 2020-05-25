package com.gracepad.hh.object;

import java.io.Serializable;

public class NeedsObject implements Serializable {

    private int id;
    private boolean selected;
    private String title;

    public NeedsObject(int id, boolean selected, String title) {
        this.id = id;
        this.selected = selected;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsSelected() {
        return selected;
    }

    public void setIsSelected(boolean selected) {
        this.selected = selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
