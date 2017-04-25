package zhuwentao.com.zwtwitmass.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 *
 * Created by zhuwentao on 2017-04-25.
 */
public interface RetrofitService {

    @Streaming
    @GET()
    Call<ResponseBody> download(@Url String path);
}
