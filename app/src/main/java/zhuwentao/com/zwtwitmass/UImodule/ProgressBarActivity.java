package zhuwentao.com.zwtwitmass.UImodule;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import zhuwentao.com.zwtwitmass.R;
import zhuwentao.com.zwtwitmass.UImodule.custom.DotProgressBar;

public class ProgressBarActivity extends AppCompatActivity {


    private ProgressBar mProgressBar;
    private Button mStartBtn;

    private DotProgressBar mDotProgressBar;



    private int progressStatus = 0;

    Handler mhondler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    mDotProgressBar.setProgress(progressStatus);
                    mProgressBar.setProgress(progressStatus);
                    break;

                default:
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        mProgressBar = (ProgressBar) findViewById(R.id.pg_h_bar);
        mDotProgressBar = (DotProgressBar) findViewById(R.id.dpb_h_bar);

        mStartBtn = (Button) findViewById(R.id.btn_start);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setProgress(0);
                mDotProgressBar.setProgress(0);
                progressStatus = 0;
                startDownLoad();
            }
        });
    }


    /**
     * 模拟下载任务
     */
    private void startDownLoad(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (progressStatus < 100){
                    Message msg = Message.obtain();
                    msg.what = 0;
                    progressStatus++;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mhondler.sendMessage(msg);
                }
            }
        }).start();
    }
}
