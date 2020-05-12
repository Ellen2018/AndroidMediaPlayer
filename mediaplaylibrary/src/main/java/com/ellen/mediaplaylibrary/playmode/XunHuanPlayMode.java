package com.ellen.mediaplaylibrary.playmode;

public class XunHuanPlayMode extends BasePlayMode {

    public XunHuanPlayMode() {
        super(1);
    }

    @Override
    public int getNextPosition(int position, int size) {
        position = position + 1;
        if(position == size){
            position = 0;
        }
        return position;
    }

    @Override
    public int getNextFromUserPosition(int position, int size) {
        position = position + 1;
        if(position == size){
            position = 0;
        }
        return position;
    }

    @Override
    public int getPrePosition(int position, int size) {
        position = position - 1;
        if(position == -1){
            position = size - 1;
        }
        return position;
    }

    @Override
    public int getPreFromUserPosition(int position, int size) {
        position = position - 1;
        if(position == -1){
            position = size - 1;
        }
        return position;
    }

}
