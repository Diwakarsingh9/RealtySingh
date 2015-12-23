package com.Apporio.realtysingh.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 11/18/2015.
 */
public class recentsettergetter {
    @SerializedName("result")
    public String result;

    @SerializedName("msg")
    public List<innerrecent> innerrecents = new ArrayList<innerrecent>();
}
