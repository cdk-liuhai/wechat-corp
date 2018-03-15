package com.wechat.corp.cgi;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.WXCorpConstants;
import com.wechat.corp.connect.WechatClient;
import net.sf.json.JSONObject;

import java.text.MessageFormat;

/**
 * @Author: liulh
 * @Description: 菜单管理接口封装
 * @Date: 2018/3/14 15:53
 * @Modified By:
 */
public abstract class Menu {

	/**
	 * @Author: liulh
	 * @Description: 创建菜单
	 * @Params: [msgJson, agentid, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:54
	 */
	public static JSONObject create(JSONObject msgJson, String agentid, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String createUrl = MessageFormat.format(WXCorpConstants.URL_MENU_CREATE, accessToken, agentid);
		String msg = msgJson.toString();
		return CgiUtils.toRequest(JSONObject.fromObject(wc.post(createUrl, msg)), wc, msg, WXCorpConstants.URL_MENU_CREATE, agentid);
	}

	/**
	 * @Author: liulh
	 * @Description: 删除菜单
	 * @Params: [agentid, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:54
	 */
	public static JSONObject delete(String agentid, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String deleteUrl = MessageFormat.format(WXCorpConstants.URL_MENU_DELETE, accessToken, agentid);
		return CgiUtils.toRequest(JSONObject.fromObject(wc.get(deleteUrl)), wc, null, WXCorpConstants.URL_MENU_DELETE, agentid);

	}

	/**
	 * @Author: liulh
	 * @Description: 获取菜单
	 * @Params: [agentid, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:54
	 */
	public static JSONObject get(String agentid, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String getUrl = MessageFormat.format(WXCorpConstants.URL_MENU_GET, accessToken, agentid);
		return CgiUtils.toRequest(JSONObject.fromObject(wc.get(getUrl)), wc, null, WXCorpConstants.URL_MENU_GET, agentid);
	}

}
