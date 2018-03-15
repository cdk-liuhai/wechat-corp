package com.wechat.corp.cgi;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.WXCorpConstants;
import com.wechat.corp.connect.WechatClient;
import net.sf.json.JSONObject;

import java.text.MessageFormat;


public abstract class General {

	/**
	 * @Author: liulh
	 * @Description: 邀请成员关注
	 * @Params: [msgJson, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:47
	 */
	public static JSONObject inviteSend(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String inviteUrl = MessageFormat.format(WXCorpConstants.URL_INVITE_SEND, accessToken);
		String msg = msgJson.toString();
		return CgiUtils.toRequest(JSONObject.fromObject(wc.post(inviteUrl, msg)), wc, msg, WXCorpConstants.URL_DEPARTMENT_DELETE);
	}

	/**
	 * @Author: liulh
	 * @Description: 获取微信服务器的ip段
	 * @Params: [wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:47
	 */
	public static JSONObject getCallbackIp(WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String getCallbackIpUrl = MessageFormat.format(WXCorpConstants.URL_GETCALLBACKIP, accessToken);
		String respJson = wc.get(getCallbackIpUrl);
		return CgiUtils.toRequest(JSONObject.fromObject(wc.get(getCallbackIpUrl)), wc, null, WXCorpConstants.URL_GETCALLBACKIP);
	}

}
