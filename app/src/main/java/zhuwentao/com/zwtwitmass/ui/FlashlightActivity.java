package zhuwentao.com.zwtwitmass.ui;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import zhuwentao.com.zwtwitmass.R;

/**
 * 手电筒
 *
 * Created by zhuwentao on 2017-05-29.
 */
public class FlashlightActivity extends AppCompatActivity{

    boolean isFlash;	// 控制器，为true时开，false关
    Camera camera;
    Button mButtonOpenOrClose;	// 控制开关

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_flashlight);

        mButtonOpenOrClose = (Button) findViewById(R.id.btn_open_flashlight);
        camera = Camera.open();
        isFlash = true;
    }
    /**
     * 操作手电筒
     * @param v
     */
    public void openOrClose(View v){
        if(isFlash){
            Camera.Parameters mParameters = camera.getParameters();
            // 更改手电筒的状态
            mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(mParameters);
            mButtonOpenOrClose.setText("关闭手电筒");
            isFlash = false;
        }else{
            Camera.Parameters mParameterss = camera.getParameters();
            mParameterss.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(mParameterss);
            mButtonOpenOrClose.setText("打开手电筒");
            isFlash = true;
        }
    }

    // 应用退出就关闭
    @Override
    protected void onDestroy() {
        super.onDestroy();
        camera.release();	// 释放手电筒
    }
}
