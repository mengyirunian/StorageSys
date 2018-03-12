package com.mengyirunian.client.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiControl {
  
    //@Query注解的作用理解为查询条件，这里表示需要查询的字段为ip  
    //ResponseBody是Retrofit自带的返回类，  
    @GET("http://ip.taobao.com/service/getIpInfo.php")
    Call<ResponseBody> getIpInfo(@Query("ip") String ip);
}