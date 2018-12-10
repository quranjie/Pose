package com.ranjie.pose.http.entity;

import com.ranjie.pose.http.result.SubjectResult;

import java.util.List;

/**
 * Created by quzhiyong on 2018/12/10
 */
public class RetrofitEntity {

    private int ret;
    private String msg;
    private List<SubjectResult> data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<SubjectResult> getData() {
        return data;
    }

    public void setData(List<SubjectResult> data) {
        this.data = data;
    }

}
