package com.Apporio.realtysingh.settergetter;

import com.google.gson.annotations.SerializedName;

/**
 * Created by saifi45 on 11/3/2015.
 */
public class logginsettergetter2 {
    @SerializedName("result")
    public String result;

    @SerializedName("msg")
    public String msg;

    @SerializedName("details")
    public Innerlogin innerlogin = new Innerlogin();
}
