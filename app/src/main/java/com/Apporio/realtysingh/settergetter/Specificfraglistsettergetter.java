package com.Apporio.realtysingh.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 11/4/2015.
 */
public class Specificfraglistsettergetter {

    @SerializedName("result")
    public String result;

    @SerializedName("msg")
    public List<Innerpagefraglist> innerpagefraglist = new ArrayList<Innerpagefraglist>();
}
