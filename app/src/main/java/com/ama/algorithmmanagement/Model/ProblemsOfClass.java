package com.ama.algorithmmanagement.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProblemsOfClass {
    @SerializedName("class")
    @Expose
    private Integer _class;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("essentials")
    @Expose
    private Integer essentials;

    public Integer getClass_() {
        return _class;
    }

    public void setClass_(Integer _class) {
        this._class = _class;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getEssentials() {
        return essentials;
    }

    public void setEssentials(Integer essentials) {
        this.essentials = essentials;
    }

}
