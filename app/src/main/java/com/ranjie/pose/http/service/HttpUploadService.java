package com.ranjie.pose.http.service;

import com.ranjie.http.api.BaseResultEntity;
import com.ranjie.pose.http.result.UploadResult;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by quzhiyong on 2018/12/10
 */
public interface HttpUploadService {

    /*上传文件*/
    @Multipart
    @POST("AppYuFaKu/uploadHeadImg")
    Observable<BaseResultEntity<UploadResult>> uploadImage(@Part("uid") RequestBody uid, @Part("auth_key") RequestBody auth_key,
                                                           @Part MultipartBody.Part file);

}
