package zhuwentao.com.zwtwitmass.UImodule.adapter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zhuwentao.com.zwtwitmass.R;
import zhuwentao.com.zwtwitmass.UImodule.HorizontalGridViewAct;

/**
 * Created by zhuwentao on 2017-03-09.
 */

public class HorizontalGvAdapter extends BaseAdapter {

    private Context mContext;

    /**
     * 所有应用数据
     */
    private List<ResolveInfo> mAppDatas;

    private PackageManager pm;

    public HorizontalGvAdapter(Context context, List<ResolveInfo> list, int page) {
        this.mContext = context;
        this.pm = context.getPackageManager();

        mAppDatas = new ArrayList<ResolveInfo>();

        // 开始加载的位置
        int pageStart = page * HorizontalGridViewAct.APP_SIZE;
        // 结束加载的位置
        int pageEnd = pageStart + HorizontalGridViewAct.APP_SIZE;

        while ((pageStart < list.size()) && (pageStart < pageEnd)) {
            mAppDatas.add(list.get(pageStart));
            pageStart++;
        }
    }
    public int getCount() {
        return mAppDatas.size();
    }

    public Object getItem(int position) {
        return mAppDatas.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_horizontal_gv, parent,false);
        }
        ResolveInfo appInfo = mAppDatas.get(position);
        ImageView appicon = (ImageView)convertView.findViewById(R.id.ivAppIcon);
        TextView appname = (TextView)convertView.findViewById(R.id.tvAppName);
        appicon.setImageDrawable(appInfo.loadIcon(pm));
        appname.setText(appInfo.loadLabel(pm));
        return convertView;
    }
}
