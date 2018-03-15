package com.wechat.corp.cgi;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.WXCorpConstants;
import com.wechat.corp.connect.WechatClient;
import net.sf.json.JSONObject;

import java.text.MessageFormat;

/**
 * @Author: liulh
 * @Description: 部分成员及部门管理封装
 * @Date: 2018/3/14 12:06
 * @Modified By:
 */
public abstract class Batch {

	/**
	 * @Author: liulh
	 * @Description: 邀请成员关注
	 * @Params: [msgJson, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 12:07
	 */
	public static JSONObject inviteUser(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String inviteUserUrl = MessageFormat.format(WXCorpConstants.URL_BATCH_INVITEUSER, accessToken);
		String msg = msgJson.toString();
		return CgiUtils.toRequest(JSONObject.fromObject(wc.post(inviteUserUrl, msg)), wc, msg, WXCorpConstants.URL_BATCH_INVITEUSER);
	}

	/**
	 * @Author: liulh
	 * @Description: 增量更新成员
	 * @Params: [msgJson, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 12:07
	 */
	public static JSONObject syncUser(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String syncUserUrl = MessageFormat.format(WXCorpConstants.URL_BATCH_SYNCUSER, accessToken);
		String msg = msgJson.toString();
		return CgiUtils.toRequest(JSONObject.fromObject(wc.post(syncUserUrl, msg)), wc, msg, WXCorpConstants.URL_BATCH_SYNCUSER);
	}

	/**
	 * @Author: liulh
	 * @Description: 全量覆盖成员
	 * @Params: [msgJson, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 12:08
	 */
	public static JSONObject replaceUser(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String replaceUserUrl = MessageFormat.format(WXCorpConstants.URL_BATCH_REPLACEUSER, accessToken);
		String msg = msgJson.toString();
		return CgiUtils.toRequest(JSONObject.fromObject(wc.post(replaceUserUrl, msg)), wc, msg, WXCorpConstants.URL_BATCH_REPLACEUSER);
	}

	/**
	 * @Author: liulh
	 * @Description: 全量覆盖部门
	 * @Params: [msgJson, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 12:08
	 */
	public static JSONObject replaceParty(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String replacePartyUrl = MessageFormat.format(WXCorpConstants.URL_BATCH_REPLACEPARTY, accessToken);
		String msg = msgJson.toString();
		return CgiUtils.toRequest(JSONObject.fromObject(wc.post(replacePartyUrl, msg)), wc, msg, WXCorpConstants.URL_BATCH_REPLACEPARTY);
	}

	/**
	 * @Author: liulh
	 * @Description: 获取异步任务结果
	 * @Params: [jobId, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 12:08
	 */
	public static JSONObject getResult(String jobId, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String getResultUrl = MessageFormat.format(WXCorpConstants.URL_BATCH_GETRESULT, accessToken, jobId);
		return CgiUtils.toRequest(JSONObject.fromObject(wc.get(getResultUrl)), wc, null, WXCorpConstants.URL_BATCH_GETRESULT);
	}

}
