package zhuwentao.com.zwtwitmass.network;

/**
 * 进度返回回调
 * Created by zhuwentao on 2017-04-25.
 */
public interface ProgressListener {

    void onProgress(long progress, long total, boolean done);
}
