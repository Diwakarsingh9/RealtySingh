package com.Apporio.realtysingh.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 11/3/2015.
 */
public class pagesnamessettergetter {

    @SerializedName("result")
    public String result;

    @SerializedName("msg")
    public List<Innerpage> innerpage = new ArrayList<Innerpage>();
}
