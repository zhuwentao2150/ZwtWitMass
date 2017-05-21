package zhuwentao.com.zwtwitmass.network.download;

/**
 * 下载管理
 * Created by zhuwentao on 2017-05-21.
 */
public class RetrofitDownLoadMange {

    private static RetrofitDownLoadMange mRetrofitDownLoadMange = new RetrofitDownLoadMange();

    public static RetrofitDownLoadMange getMange(){
        return mRetrofitDownLoadMange;
    }


    /***
     * 下载方法
     * @param url
     */
    public void download(String url){

    }

    /**
     * 关闭
     * @param url
     */
    public void close(String url){

    }

}
