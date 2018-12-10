package com.ranjie.pose.http.api;

import com.ranjie.http.api.BaseApi;
import com.ranjie.http.listener.HttpOnNextListener;
import com.ranjie.pose.http.service.HttpUploadService;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by quzhiyong on 2018/12/10
 */
public class UploadApi extends BaseApi {

    /*需要上传的文件*/
    private MultipartBody.Part part;

    public UploadApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setShowProgress(true);
        setMethod("AppFiftyToneGraph/videoLink");
    }

    public MultipartBody.Part getPart() {
        return part;
    }

    public void setPart(MultipartBody.Part part) {
        this.part = part;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpUploadService service = retrofit.create(HttpUploadService.class);
        RequestBody uid= RequestBody.create(MediaType.parse("text/plain"), "4811420");
        RequestBody key = RequestBody.create(MediaType.parse("text/plain"), "cfed6cc8caad0d79ea56d917376dc4df");
        return service.uploadImage(uid,key,getPart());
    }

}
