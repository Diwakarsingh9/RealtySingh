package com.Apporio.realtysingh.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 12/1/2015.
 */
public class photossettergetteroutter {
    @SerializedName("result")
    public String result;

    @SerializedName("msg")
    public List<Innerphoto> innerphoto = new ArrayList<Innerphoto>();
}
