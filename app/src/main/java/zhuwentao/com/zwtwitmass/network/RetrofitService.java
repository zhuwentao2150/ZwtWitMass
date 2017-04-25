package zhuwentao.com.zwtwitmass.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

/**
 *
 * Created by zhuwentao on 2017-04-25.
 */
public interface RetrofitService {

    @Streaming
    @GET("http://gdown.baidu.com/data/wisegame/df65a597122796a4/weixin_821.apk")
    Call<ResponseBody> download();
}
