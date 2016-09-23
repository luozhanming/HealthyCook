package com.zhanming.healthycook.beans;

/**
 * Created by zhanming on 2016/9/23.
 */
public class Catalogue {
    private int id;
    private int page;
    private String name;

    public Catalogue(int id, int page, String name) {
        this.id = id;
        this.page = page;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
