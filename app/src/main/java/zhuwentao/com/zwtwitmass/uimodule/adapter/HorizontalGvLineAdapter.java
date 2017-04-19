package zhuwentao.com.zwtwitmass.uimodule.adapter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zhuwentao.com.zwtwitmass.R;
import zhuwentao.com.zwtwitmass.uimodule.CommonAdapter;

/**
 * Created by zhuwentao on 2017-04-19.
 */

public class HorizontalGvLineAdapter extends CommonAdapter<ResolveInfo> {

    private PackageManager pm;

    /**
     * 构造方法
     *
     * @param ctx      上下文
     * @param datas
     */
    public HorizontalGvLineAdapter(Context ctx, List<ResolveInfo> datas) {
        super(ctx, R.layout.item_horizontal_gv, datas);
        pm = ctx.getPackageManager();
    }

    @Override
    public void convert(ViewHolder holder, ResolveInfo item, int position) {
        ImageView appIconIv = (ImageView) holder.getView(R.id.ivAppIcon);
        TextView appNameTv = (TextView) holder.getView(R.id.tvAppName);

        appIconIv.setImageDrawable(item.loadIcon(pm));
        appNameTv.setText(item.loadLabel(pm));
    }
}
