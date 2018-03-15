package com.wechat.corp.cgi;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.WXCorpConstants;
import com.wechat.corp.connect.WechatClient;
import net.sf.json.JSONObject;

import java.text.MessageFormat;

/**
 * @Author: liulh
 * @Description: 标签管理接口封装
 * @Date: 2018/3/14 15:55
 * @Modified By:
 */
public abstract class Tag {

	/**
	 * @Author: liulh
	 * @Description: 创建标签
	 * @Params: [msgJson, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:55
	 */
	public static JSONObject create(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String createUrl = MessageFormat.format(WXCorpConstants.URL_TAG_CREATE, accessToken);
		String msg = msgJson.toString();
		return CgiUtils.toRequest(JSONObject.fromObject(wc.post(createUrl, msg)), wc, msg, WXCorpConstants.URL_TAG_CREATE);
	}

	/**
	 * @Author: liulh
	 * @Description: 修改标签
	 * @Params: [msgJson, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:55
	 */
	public static JSONObject update(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String updateUrl = MessageFormat.format(WXCorpConstants.URL_TAG_UPDATE, accessToken);
		String msg = msgJson.toString();
		return CgiUtils.toRequest(JSONObject.fromObject(wc.post(updateUrl, msg)), wc, msg, WXCorpConstants.URL_TAG_UPDATE);
	}

	/**
	 * @Author: liulh
	 * @Description: 删除标签
	 * @Params: [tagid, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:56
	 */
	public static JSONObject delete(String tagid, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String deleteUrl = MessageFormat.format(WXCorpConstants.URL_TAG_DELETE, accessToken, tagid);
		return CgiUtils.toRequest(JSONObject.fromObject(wc.get(deleteUrl)), wc, null, WXCorpConstants.URL_TAG_DELETE, tagid);
	}

	/**
	 * @Author: liulh
	 * @Description: 获取标签成员
	 * @Params: [tagid, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:56
	 */
	public static JSONObject get(String tagid, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String getUrl = MessageFormat.format(WXCorpConstants.URL_TAG_GET, accessToken, tagid);
		return CgiUtils.toRequest(JSONObject.fromObject(wc.get(getUrl)), wc, null, WXCorpConstants.URL_TAG_GET, tagid);
	}

	/**
	 * @Author: liulh
	 * @Description: 增加标签成员
	 * @Params: [msgJson, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:56
	 */
	public static JSONObject addtagusers(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String addtagusersUrl = MessageFormat.format(WXCorpConstants.URL_TAG_ADDTAGUSERS, accessToken);
		String msg = msgJson.toString();
		return CgiUtils.toRequest(JSONObject.fromObject(wc.post(addtagusersUrl, msg)), wc, msg, WXCorpConstants.URL_TAG_ADDTAGUSERS);
	}

	/**
	 * @Author: liulh
	 * @Description: 删除标签成员
	 * @Params: [msgJson, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:56
	 */
	public static JSONObject deltagusers(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String deltagusersUrl = MessageFormat.format(WXCorpConstants.URL_TAG_DELTAGUSERS, accessToken);
		String msg = msgJson.toString();
		return CgiUtils.toRequest(JSONObject.fromObject(wc.post(deltagusersUrl, msg)), wc, msg, WXCorpConstants.URL_TAG_DELTAGUSERS);
	}

	/**
	 * @Author: liulh
	 * @Description: 获取标签列表
	 * @Params: [wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:57
	 */
	public static JSONObject list(WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String listUrl = MessageFormat.format(WXCorpConstants.URL_TAG_LIST, accessToken);
		return CgiUtils.toRequest(JSONObject.fromObject(wc.get(listUrl)), wc, null, WXCorpConstants.URL_TAG_LIST);
	}

}
