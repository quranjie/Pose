package com.ranjie.http.http.cookie;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;

/**
 * Created by quzhiyong on 2018/12/10
 */
@Entity
public class CookieResult {

    @Id
    private Long id;
    /*url*/
    private String url;
    /*返回结果*/
    private String result;
    /*时间*/
    private long time;

    @Keep
    public CookieResult(String url, String result, long time) {
        this.url = url;
        this.result = result;
        this.time = time;
    }

    @Keep
    public CookieResult(Long id, String url, String result, long time) {
        this.id = id;
        this.url = url;
        this.result = result;
        this.time = time;
    }

    @Keep
    public CookieResult() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }


}
