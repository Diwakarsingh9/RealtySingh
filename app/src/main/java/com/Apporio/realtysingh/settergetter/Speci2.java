package com.Apporio.realtysingh.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 11/5/2015.
 */
public class Speci2 {
    @SerializedName("result")
    public String result;

    @SerializedName("msg")
    public List<innerspeci2> innerspeci2 = new ArrayList<innerspeci2>();
}
