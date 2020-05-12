package com.ellen.mediaplaylibrary.base;

import java.util.ArrayList;
import java.util.List;

public class RecordData<T> {

    private List<Integer> recordList;
    private int recordPosition = -1;

    public RecordData(){
        recordList = new ArrayList<>();
    }

    public List<Integer> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Integer> recordList) {
        this.recordList = recordList;
    }

    public int getRecordPosition() {
        return recordPosition;
    }

    public void setRecordPosition(int recordPosition) {
        this.recordPosition = recordPosition;
    }
}
