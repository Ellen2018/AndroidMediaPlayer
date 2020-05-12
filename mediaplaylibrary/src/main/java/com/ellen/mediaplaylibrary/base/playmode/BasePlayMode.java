package com.ellen.mediaplaylibrary.base.playmode;

public abstract class BasePlayMode implements PlayModeInterface{

    private int id;


    public BasePlayMode(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
