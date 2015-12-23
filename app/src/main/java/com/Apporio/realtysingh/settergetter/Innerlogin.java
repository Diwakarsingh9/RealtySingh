package com.Apporio.realtysingh.settergetter;

import com.google.gson.annotations.SerializedName;

/**
 * Created by saifi45 on 11/3/2015.
 */
public class Innerlogin {
    @SerializedName("user_id")
    public String user_id;

    @SerializedName("name")
    public String name;

    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;

    @SerializedName("mobile_number")
    public String mobile_number;

    @SerializedName("status")
    public String status;
}
