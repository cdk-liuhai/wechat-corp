package com.wechat.corp.cgi;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.Constants;
import com.wechat.corp.connect.WechatClient;
import net.sf.json.JSONObject;

import java.text.MessageFormat;

/**
 * @Author: liulh
 * @Description: 成员管理接口封装
 * @Date: 2018/3/14 15:57
 * @Modified By:
 */
public abstract class User {

	/**
	 * 企业在开启二次验证时，必须填写企业二次验证页面的url，此url的域名必须设置为企业小助手的可信域名。当成员绑定通讯录中的帐号后，
	 * 会收到一条图文消息，引导成员到企业的验证页面验证身份。在跳转到企业的验证页面时，会带上如下参数：code=CODE&state=STATE，
	 * 企业可以调用oauth2接口，根据code获取成员的userid。 企业在成员验证成功后，调用如下接口即可让成员关注成功。
	 */
	public static JSONObject authsucc(String userid, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String authsuccUrl = MessageFormat.format(Constants.URL_USER_AUTHSUCC, accessToken, userid);
		return CgiUtils.toRequest(JSONObject.fromObject(wc.get(authsuccUrl)), wc, null, Constants.URL_USER_AUTHSUCC, userid);
	}

	/**
	 * @Author: liulh
	 * @Description: 创建成员
	 * @Params: [msgJson, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:57
	 */
	public static JSONObject create(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String createUrl = MessageFormat.format(Constants.URL_USER_CREATE, accessToken);
		String msg = msgJson.toString();
		return CgiUtils.toRequest(JSONObject.fromObject(wc.post(createUrl, msg)), wc, msg, Constants.URL_USER_CREATE);
	}

	/**
	 * @Author: liulh
	 * @Description: 修改成员
	 * @Params: [msgJson, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:57
	 */
	public static JSONObject update(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String updateUrl = MessageFormat.format(Constants.URL_USER_UPDATE, accessToken);
		String msg = msgJson.toString();
		return CgiUtils.toRequest(JSONObject.fromObject(wc.post(updateUrl, msg)), wc, msg, Constants.URL_USER_UPDATE);
	}

	/**
	 * @Author: liulh
	 * @Description: 删除成员
	 * @Params: [id, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:58
	 */
	public static JSONObject delete(String id, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String deleteUrl = MessageFormat.format(Constants.URL_USER_DELETE, accessToken, id);
		return CgiUtils.toRequest(JSONObject.fromObject(wc.get(deleteUrl)), wc, null, Constants.URL_USER_DELETE);
	}

	/**
	 * @Author: liulh
	 * @Description: 批量删除成员
	 * @Params: [msgJson, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:58
	 */
	public static JSONObject batchDelete(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String batchDeleteUrl = MessageFormat.format(Constants.URL_USER_BATCHDELETE, accessToken);
		String msg = msgJson.toString();
		return CgiUtils.toRequest(JSONObject.fromObject(wc.post(batchDeleteUrl, msg)), wc, msg, Constants.URL_USER_BATCHDELETE);
	}

	/**
	 * @Author: liulh
	 * @Description: 获取成员信息
	 * @Params: [id, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:58
	 */
	public static JSONObject get(String id, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String getUrl = MessageFormat.format(Constants.URL_USER_GET, accessToken, id);
		String respJson = wc.get(getUrl);
		return CgiUtils.toRequest(JSONObject.fromObject(wc.get(getUrl)), wc, null, Constants.URL_USER_GET, id);
	}

	/**
	 * @Author: liulh
	 * @Description: 获取部门成员
	 * @Params: [departmentId, fetchChild, status, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:58
	 */
	public static JSONObject simpleList(String departmentId, String fetchChild, WechatClient wc)
			throws WechatException {
		String accessToken = wc.getAccessToken();
		String simpleListUrl = MessageFormat.format(Constants.URL_USER_SIMPLELIST, accessToken, departmentId, fetchChild);
		return CgiUtils.toRequest(JSONObject.fromObject(wc.get(simpleListUrl)), wc, null, Constants.URL_USER_SIMPLELIST, departmentId,fetchChild);
	}

	/**
	 * @Author: liulh
	 * @Description: 获取部门成员详情
	 * @Params: [departmentId, fetchChild, status, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:58
	 */
	public static JSONObject list(String departmentId, String fetchChild, WechatClient wc)
			throws WechatException {
		String accessToken = wc.getAccessToken();
		String listUrl = MessageFormat.format(Constants.URL_USER_LIST, accessToken, departmentId, fetchChild);
		return CgiUtils.toRequest(JSONObject.fromObject(wc.get(listUrl)), wc, null, Constants.URL_USER_LIST, departmentId, fetchChild);
	}

	/**
	 * @Author: liulh
	 * @Description: 根据code获取成员信息
	 * @Params: [code, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:59
	 */
	public static JSONObject getuserinfo(String code, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String getuserinfoUrl = MessageFormat.format(Constants.URL_USER_GETUSERINFO, accessToken, code);
		return CgiUtils.toRequest(JSONObject.fromObject(wc.get(getuserinfoUrl)), wc, null, Constants.URL_USER_GETUSERINFO, code);
	}

	/**
	 * @Author: liulh
	 * @Description: 根据user_ticket获取成员详细信息
	 * @Params: [msgJson, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:59
	 */
	public static JSONObject getUserDetail(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String sendUrl = MessageFormat.format(Constants.URL_USER_DETAIL, accessToken);
		return CgiUtils.toRequest(JSONObject.fromObject(wc.post(sendUrl, msgJson.toString())), wc, msgJson.toString(), Constants.URL_USER_DETAIL);
	}

	/**
	 * @Author: liulh
	 * @Description: userid转换成openid接口
	 * @Params: [msgJson, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:59
	 */
	public static JSONObject convertToOpenid(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String convertToOpenidUrl = MessageFormat.format(Constants.URL_USER_CONVERTTOOPENID, accessToken);
		String msg = msgJson.toString();
		return CgiUtils.toRequest(JSONObject.fromObject(wc.post(convertToOpenidUrl, msg)), wc, msg, Constants.URL_USER_CONVERTTOOPENID);
	}

	/**
	 * @Author: liulh
	 * @Description: openid转换成userid接口
	 * @Params: [msgJson, wc]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:59
	 */
	public static JSONObject convertToUserid(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String convertToUseridUrl = MessageFormat.format(Constants.URL_USER_CONVERTTOUSERID, accessToken);
		String msg = msgJson.toString();
		return CgiUtils.toRequest(JSONObject.fromObject(wc.post(convertToUseridUrl, msg)), wc, msg, Constants.URL_USER_CONVERTTOUSERID);
	}

}
