package zhuwentao.com.zwtwitmass.network.download;

import java.util.HashMap;

import zhuwentao.com.zwtwitmass.utils.LogUtil;

/**
 * 下载管理
 * Created by zhuwentao on 2017-05-21.
 */
public class RetrofitDownLoadMange {

    private static RetrofitDownLoadMange mRetrofitDownLoadMange = new RetrofitDownLoadMange();

    /**
     * 获取下载实例
     *
     * @return
     */
    public static RetrofitDownLoadMange getInstance() {
        return mRetrofitDownLoadMange;
    }

    /**
     * 存储下载任务
     */
    private HashMap<String, DownLoadTask> mDownLoadTasks = new HashMap<>();

    /***
     * 下载
     *
     * @param url
     */
    public void download(String url) {
        // 启动下载服务
        for (int i = 0; i < mDownLoadTasks.size(); i++) {
            if (mDownLoadTasks.get(url) != null) {
                LogUtil.e("该url已经存在");
                if (mDownLoadTasks.get(url).isCanceled()){
                    mDownLoadTasks.get(url).start();
                }else{
                    LogUtil.e("正在下载中");
                }
            } else {
                DownLoadTask downLoadTask = new DownLoadTask(url);
                mDownLoadTasks.put(url, downLoadTask);
                LogUtil.e("该url不存在");
            }
        }
        if(mDownLoadTasks.size() == 0){
            DownLoadTask downLoadTask = new DownLoadTask(url);
            mDownLoadTasks.put(url, downLoadTask);
            downLoadTask.start();
        }

    }

    /**
     * 取消下载
     *
     * @param url
     */
    public void close(String url) {
        for (int i = 0; i < mDownLoadTasks.size(); i++) {
            if (mDownLoadTasks.get(url) != null) {
                mDownLoadTasks.get(url).stop();
                //mDownLoadTasks.remove(url);
                //LogUtil.e("已移除该url下载任务：" + url);
                return;
            }
        }
    }

    /**
     * 返回一个下载实例
     *
     * @param url
     * @return
     */
    public DownLoadTask getDownLoadTask(String url) {
        if (mDownLoadTasks.get(url) != null) {
            return mDownLoadTasks.get(url);
        } else {
            return null;
        }
    }

}
