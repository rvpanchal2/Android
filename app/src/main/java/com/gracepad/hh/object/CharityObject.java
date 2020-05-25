package com.gracepad.hh.object;

import java.io.Serializable;
import java.util.ArrayList;

public class CharityObject implements Serializable {

    private ArrayList<CharityRequestObject> arrCharityRequest = new ArrayList<>();

    public ArrayList<CharityRequestObject> getArrCharityRequest() {
        return arrCharityRequest;
    }

    public void setArrCharityRequest(ArrayList<CharityRequestObject> arrCharityRequest) {
        this.arrCharityRequest = arrCharityRequest;
    }
}
