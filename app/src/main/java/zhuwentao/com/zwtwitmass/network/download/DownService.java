package zhuwentao.com.zwtwitmass.network.download;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * 下载服务
 * Created by zhuwentao on 2017-05-27.
 */
public class DownService extends Service{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    public class MyBinder extends Binder {
        /**
         * 得到一个Binder对象
         *
         * @return
         */
        public DownService getService() {
            return DownService.this;
        }
    }


}
