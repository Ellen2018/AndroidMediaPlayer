package com.ellen.mediaplaylibrary.base;

public class AddMusicData<T> {
    private T t;
    private int position;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
