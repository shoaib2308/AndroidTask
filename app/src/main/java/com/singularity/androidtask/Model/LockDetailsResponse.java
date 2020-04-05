package com.singularity.androidtask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LockDetailsResponse {
    @SerializedName("MAC")
    @Expose
    private String mAC;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public String getMAC() {
        return mAC;
    }

    public void setMAC(String mAC) {
        this.mAC = mAC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
