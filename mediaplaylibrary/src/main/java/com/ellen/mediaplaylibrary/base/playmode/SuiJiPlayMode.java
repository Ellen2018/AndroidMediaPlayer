package com.ellen.mediaplaylibrary.base.playmode;

public class SuiJiPlayMode extends BasePlayMode {

    public SuiJiPlayMode() {
        super(2);
    }

    @Override
    public int getNextPosition(int position, int size) {
        int currentPosition = (int) ((Math.random() * size));
        while (currentPosition == position){
            currentPosition = (int) ((Math.random() * size));
        }
        return currentPosition;
    }

    @Override
    public int getNextFromUserPosition(int position, int size) {
        int currentPosition = (int) ((Math.random() * size));
        while (currentPosition == position){
            currentPosition = (int) ((Math.random() * size));
        }
        return currentPosition;
    }

    @Override
    public int getPrePosition(int position, int size) {
        int currentPosition = (int) ((Math.random() * size));
        while (currentPosition == position){
            currentPosition = (int) ((Math.random() * size));
        }
        return currentPosition;
    }

    @Override
    public int getPreFromUserPosition(int position, int size) {
        int currentPosition = (int) ((Math.random() * size));
        while (currentPosition == position){
            currentPosition = (int) ((Math.random() * size));
        }
        return currentPosition;
    }
}
