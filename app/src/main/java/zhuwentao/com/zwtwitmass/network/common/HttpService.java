package zhuwentao.com.zwtwitmass.network.common;

/**
 * Retrofit2.2网络请求通用接口
 * Created by zhuwentao on 2017-05-05.
 */

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 请求接口
 * @author zwt Create by 2017-4-20
 */
public interface HttpService {

    /**
     * GET通用请求基类
     * @param map
     * @return
     */
    @GET(".")
    Call<ResponseBody> get(@QueryMap Map<String, String> map);

    /**
     * POST通用请求基类
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST(".")
    Call<ResponseBody> post(@FieldMap Map<String, String> map);

    /**
     * 下载文件
     * @param url
     * @return
     */
    @Streaming
    @GET()
    Call<ResponseBody> downloadFile(@Url String url);

    /**
     * 下载文件，带断点
     * @param url
     * @return
     */
    @Streaming
    @GET()
    Call<ResponseBody> downloadFile(@Url String url, @Header("RANGE") String range);

}
