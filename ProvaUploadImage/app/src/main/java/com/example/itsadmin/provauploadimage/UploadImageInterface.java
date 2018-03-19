package com.example.itsadmin.provauploadimage;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by itsadmin on 19/03/2018.
 */

public interface UploadImageInterface {
    @Multipart
    @POST("/imagefolder/index.php")
    Call<MainActivity.UploadObject> uploadFile(@Part MultipartBody.Part file, @Part("name") RequestBody name);
}