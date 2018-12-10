package com.ranjie.pose.http.service;

import com.ranjie.http.api.BaseResultEntity;
import com.ranjie.pose.http.entity.RetrofitEntity;
import com.ranjie.pose.http.result.SubjectResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by quzhiyong on 2018/12/10
 */
public interface HttpPostService {

    @POST("AppFiftyToneGraph/videoLink")
    Call<RetrofitEntity> getAllVedio(@Body boolean once_no);

    @POST("AppFiftyToneGraph/videoLink")
    Observable<RetrofitEntity> getAllVedioBy(@Body boolean once_no);

    @FormUrlEncoded
    @POST("AppFiftyToneGraph/videoLink")
    Observable<BaseResultEntity<List<SubjectResult>>> getAllVedioBys(@Field("once") boolean once_no);


}
