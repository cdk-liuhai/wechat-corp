package com.wechat.corp.cgi;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.Constants;
import com.wechat.corp.connect.WechatClient;
import net.sf.json.JSONObject;

import java.text.MessageFormat;

/**
 * @Author: liulh
 * @Description: 消息发送接口封装
 * @Date: 2018/3/14 15:54
 * @Modified By:
 */
public abstract class Message {

	/**
	 * @Author: liulh
	 * @Description: 发送消息
	 * @Params: [msgJson, wc, accessToken]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:54
	 */
	public static JSONObject send(JSONObject msgJson, WechatClient wc, String accessToken) throws WechatException {
		String sendUrl = MessageFormat.format(Constants.URL_MESSAGE_SEND, accessToken);
		String msg = msgJson.toString();
		return CgiUtils.toRequest(JSONObject.fromObject(wc.post(sendUrl, msg)), wc, msg, Constants.URL_MESSAGE_SEND);
	}

}
