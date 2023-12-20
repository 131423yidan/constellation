package com.swun.constellation.starfrag;

public class StarAnalysisBean {
    private String title;
    private String content;
    private int color;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public StarAnalysisBean(String title, String content, int color) {
        this.title = title;
        this.content = content;
        this.color = color;
    }
}
