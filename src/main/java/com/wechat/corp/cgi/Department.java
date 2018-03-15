package com.wechat.corp.cgi;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.Constants;
import com.wechat.corp.connect.WechatClient;
import net.sf.json.JSONObject;

import java.text.MessageFormat;

/**
 * @Author: liulh
 * @Description: 部门管理请求封装
 * @Date: 2018/3/14 15:45
 * @Modified By:
 */
public abstract class Department {

	/**
	 * @Author: liulh
	 * @Description: 创建部门
	 * @Params: [msgJson, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:45
	 */
	public static JSONObject create(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String createUrl = MessageFormat.format(Constants.URL_DEPARTMENT_CREATE, accessToken);
		String msg = msgJson.toString();
		return CgiUtils.toRequest(JSONObject.fromObject(wc.post(createUrl, msg)), wc, msg, Constants.URL_DEPARTMENT_CREATE);
	}

	/**
	 * @Author: liulh
	 * @Description: 修改部门
	 * @Params: [msgJson, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:46
	 */
	public static JSONObject update(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String updateUrl = MessageFormat.format(Constants.URL_DEPARTMENT_UPDATE, accessToken);
		String msg = msgJson.toString();
		return CgiUtils.toRequest(JSONObject.fromObject(wc.post(updateUrl, msg)), wc, msg, Constants.URL_DEPARTMENT_UPDATE);
	}

	/**
	 * @Author: liulh
	 * @Description: 删除部门
	 * @Params: [id, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:46
	 */
	public static JSONObject delete(String id, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String deleteUrl = MessageFormat.format(Constants.URL_DEPARTMENT_DELETE, accessToken, id);
		return CgiUtils.toRequest(JSONObject.fromObject(wc.get(deleteUrl)), wc, null, Constants.URL_DEPARTMENT_DELETE, id);
	}

	/**
	 * @Author: liulh
	 * @Description: 获取部门
	 * @Params: [id, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:46
	 */
	public static JSONObject list(String id, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String listUrl = MessageFormat.format(Constants.URL_DEPARTMENT_LIST, accessToken, id);
		String respJson = wc.get(listUrl);
		return CgiUtils.toRequest(JSONObject.fromObject(wc.get(listUrl)), wc, null, Constants.URL_DEPARTMENT_LIST, id);
	}

}
