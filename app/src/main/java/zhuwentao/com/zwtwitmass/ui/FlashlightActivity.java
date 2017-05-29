package zhuwentao.com.zwtwitmass.ui;

import android.graphics.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * 手电筒
 * Created by zhuwentao on 2017-05-29.
 */
public class FlashlightActivity extends AppCompatActivity{

    boolean isFlash;	// 控制器，为true时开，false关
    Camera camera;
    Button mButtonOpenOrClose;	// 控制开关

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
