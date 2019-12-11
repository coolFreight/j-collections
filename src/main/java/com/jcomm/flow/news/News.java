package com.jcomm.flow.news;

import java.time.LocalDate;

public class News {
    public static final int SPORTS=0;
    public static final int WORLD=1;
    public static final int ECONOMIC=2;
    public static final int SCIENCE=3;

    private int category;
    private String txt;
    private LocalDate date;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "News{" +
                "category=" + category +
                ", txt='" + txt + '\'' +
                ", date=" + date +
                '}';
    }
}
