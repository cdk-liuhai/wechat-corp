package com.wechat.corp.cgi;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.WXCorpConstants;
import com.wechat.corp.connect.WechatClient;
import net.sf.json.JSONObject;

import java.text.MessageFormat;

/**
 * @Author: liulh
 * @Description: 企业应用管理封装
 * @Date: 2018/3/14 12:05
 * @Modified By:
 */
public abstract class Agent {

	/**
	 * @Author: liulh
	 * @Description: 获取企业应用
	 * @Params: [agentid, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 12:05
	 */
	public static JSONObject get(String agentid, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String getUrl = MessageFormat.format(WXCorpConstants.URL_AGENT_GET, accessToken, agentid);
		return CgiUtils.toRequest(JSONObject.fromObject(wc.get(getUrl)), wc, null, WXCorpConstants.URL_AGENT_GET, agentid);
	}

	/**
	 * @Author: liulh
	 * @Description: 设置企业应用
	 * @Params: [msgJson, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 12:05
	 */
	public static JSONObject set(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String setUrl = MessageFormat.format(WXCorpConstants.URL_AGENT_SET, accessToken);
		String msg = msgJson.toString();
		return CgiUtils.toRequest(JSONObject.fromObject(wc.post(setUrl, msg)), wc, msg, WXCorpConstants.URL_AGENT_SET);
	}

	/**
	 * @Author: liulh
	 * @Description: 获取应用概况列表
	 * @Params: [wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 12:06
	 */
	public static JSONObject list(WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String listUrl = MessageFormat.format(WXCorpConstants.URL_AGENT_LIST, accessToken);
		return CgiUtils.toRequest(JSONObject.fromObject(wc.get(listUrl)), wc, null, WXCorpConstants.URL_AGENT_LIST);
	}

}
