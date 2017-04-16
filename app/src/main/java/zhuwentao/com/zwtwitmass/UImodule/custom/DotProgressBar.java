package zhuwentao.com.zwtwitmass.uimodule.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import zhuwentao.com.zwtwitmass.R;

/**
 * 带有小圆点的ProgressBar控件
 * Created by zhuwentao on 2017-03-16.
 */
public class DotProgressBar extends RelativeLayout {

    /**
     * 进度条的最大值
     */
    private static final float PROGRESS_MAX = 100.0f;


    private ProgressBar mProgressBar = null;

    /**
     * 圆点图片
     */
    private ImageView mDotIv = null;

    public DotProgressBar(Context context) {
        super(context);
        initDotViews(context);
    }

    public DotProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDotViews(context);
    }

    public DotProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDotViews(context);
    }

    private void initDotViews(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.progress_bar_dot_layout, null);
        mProgressBar = (ProgressBar) view.findViewById(R.id.pb_progressbar);
        mDotIv = (ImageView) view.findViewById(R.id.iv_dot_download);

        addView(view);
    }

    /**
     * 更新进度条
     * @param progress
     */
    public void setProgress(int progress) {
        if (progress < PROGRESS_MAX) {
            // 开始下载，显示小圆点
            mDotIv.setVisibility(View.VISIBLE);
            LayoutParams arrowParams = (LayoutParams) mDotIv.getLayoutParams();
            float leftPosition = ((mProgressBar.getWidth() / PROGRESS_MAX) * progress) + mProgressBar.getLeft();
            arrowParams.leftMargin = (int) Math.ceil(leftPosition);

            mDotIv.setLayoutParams(arrowParams);
        } else {
            // 下载完毕，隐藏小圆点
            mDotIv.setVisibility(ImageView.GONE);
        }
        mProgressBar.setProgress(progress);
    }
}
