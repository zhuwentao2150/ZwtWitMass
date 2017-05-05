package zhuwentao.com.zwtwitmass.network.entity;

import java.util.List;

/**
 * 业务请求基类
 * @author zwt Create by 2017-4-20
 */
public class BaseEntity<T extends Entity> extends Entity{

	/**
	 * 接口状态码
	 */
	public String stateCode;

	/**
	 * 接口状态描述
	 */
	public String stateDesc;

	/**
	 * 响应的实体数据列表
	 */
	public List<T> datas;

}
