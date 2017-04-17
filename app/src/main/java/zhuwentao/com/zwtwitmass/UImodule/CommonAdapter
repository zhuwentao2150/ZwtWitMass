package zhuwentao.com.zwtwitmass.uimodule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 通用Adapter基类
 * 
 * @author zwt Create by 2017-3-3
 */
public abstract class CommonAdapter<E> extends BaseAdapter {

	/**
	 * 数据
	 */
	private List<E> datas;

	/**
	 * item条目的布局id
	 */
	private int resource;

	private Context ctx;

	private final Object mLock = new Object();

	/**
	 * 构造方法
	 * 
	 * @param ctx
	 *            上下文
	 * @param resource
	 *            item布局id
	 * @param datas
	 *            数据
	 */
	public CommonAdapter(Context ctx, int resource, List<E> datas) {
		this.ctx = ctx;
		this.resource = resource;
		setData(datas);
	}

	/**
	 * 设置Adapter中的数据
	 * 
	 * @param data
	 *            需要设置的列表数据
	 */
	public void setData(List<E> data) {
		synchronized (mLock) {
			if (data == null) {
				data = new ArrayList<E>();
			}
		}
		this.datas = data;
		notifyDataSetChanged();
	}

	/**
	 * 向List添加单个对象
	 * 
	 * @param object
	 */
	public void add(E object) {
		synchronized (mLock) {
			if (datas != null) {
				datas.add(object);
			}
		}
		notifyDataSetChanged();
	}

	/**
	 * 向List前追加列表数据
	 * 
	 * @param collection
	 *            添加的集合
	 */
	public void addAllToHead(Collection<? extends E> collection) {
		synchronized (mLock) {
			if (datas != null) {
				datas.addAll(0, collection);
			}
		}
		notifyDataSetChanged();
	}

	/**
	 * 向List末尾追加列表数据
	 * 
	 * @param data
	 *            添加的集合
	 */
	public void addAll(Collection<? extends E> collection) {
		synchronized (mLock) {
			if (datas != null) {
				datas.addAll(collection);
			}
		}
		notifyDataSetChanged();
	}

	/**
	 * 从List中删除指定的一个对象
	 * 
	 * @param object
	 */
	public void remove(E object) {
		synchronized (mLock) {
			if (datas != null) {
				datas.remove(object);
			}
		}
		notifyDataSetChanged();
	}

	/**
	 * 清空List所有数据
	 */
	public void clear() {
		synchronized (mLock) {
			if (datas != null) {
				datas.clear();
			}
		}
		notifyDataSetChanged();
	}

	public Context getContext() {
		return ctx;
	}

	public List<E> getData() {
		return datas;
	}

	/**
	 * 返回item在List中的位置
	 * 
	 * @param item
	 * @return 指定的项的位置
	 */
	public int getPosition(E item) {
		return datas.indexOf(item);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.datas.size();
	}

	@Override
	public E getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = ViewHolder.get(ctx, resource, convertView, parent);
		convert(holder, getItem(position), position);
		return holder.getConvertView();
	}

	/**
	 * 子类需实现此方法，完成item界面设置
	 */
	public abstract void convert(ViewHolder holder, E item, int position);

	public static class ViewHolder {

		private View convertView;

		private SparseArray<View> views;

		private ViewHolder(Context ctx, int resource, ViewGroup parent) {
			convertView = LayoutInflater.from(ctx).inflate(resource, parent,
					false);
			convertView.setTag(this);
			views = new SparseArray<View>();
		}

		/**
		 * 获取ViewHoler
		 * 
		 * @param ctx
		 *            上下文
		 * @param resource
		 *            资源
		 * @param convertView
		 *            View
		 * @return
		 */
		public static ViewHolder get(Context ctx, int resource,
				View convertView, ViewGroup parent) {
			ViewHolder vh = null;
			if (convertView == null) {
				vh = new ViewHolder(ctx, resource, parent);
			} else {
				vh = (ViewHolder) convertView.getTag();
			}
			return vh;
		}

		public View getConvertView() {
			return this.convertView;
		}

		/**
		 * 从ViewHoler中获取已经保持的View
		 * 
		 * @param resId
		 *            布局资源id
		 * @return View item 对应的view
		 */
		public View getView(int widgetId) {
			View view = views.get(widgetId);
			if (view == null) {
				view = convertView.findViewById(widgetId);
				views.append(widgetId, view);
			}
			return view;
		}
	}

}
