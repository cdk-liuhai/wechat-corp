package com.wechat.corp.cgi;

import com.wechat.corp.bean.MpnewsItem;
import com.wechat.corp.bean.NewsItem;
import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.Constants;
import com.wechat.corp.connect.WechatClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: liulh
 * @Description: 接口操作请求内容封装
 * @Date: 2018/3/14 12:09
 * @Modified By:
 */
public abstract class CgiUtils {

	/**
	 * @Author: liulh
	 * @Description: 是否重新获取accesstoken
	 * @Params: [json]
	 * @return: boolean
	 * @Date: 2018/3/14 12:10
	 */
	public static boolean refreshAccessToken(JSONObject json) {
		if (json.containsKey(Constants.RESP_ERRCODE)) {
			String errcode = json.getString(Constants.RESP_ERRCODE);
			if (Constants.ILLEGAL_TOKEN.equals(errcode) || Constants.OVERDUE_TOKEN.equals(errcode)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @Author: liulh
	 * @Description: 是否需要重新获取access_token并发起请求
	 * @Params: [respJson, wc, rootUrl]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/15 15:41
	 */
	public static JSONObject toRequest(JSONObject respJson,WechatClient wc, String msg, String... rootUrl) throws WechatException {
		if (CgiUtils.refreshAccessToken(respJson)) {
			//access_token无效或过期重新获取一次
			String accessToken = wc.getAccessToken(CgiUtils.refreshAccessToken(respJson));

			//拼接url
			List parameter = new ArrayList(Arrays.asList(rootUrl));
			parameter.remove(0);
			parameter.add(0, accessToken);
			String url = MessageFormat.format(rootUrl[0], parameter.toArray(new String[parameter.size()]));

			if (StringUtils.isNotBlank(msg)) {
				respJson = JSONObject.fromObject(wc.post(url, msg));
			} else {
				respJson = JSONObject.fromObject(wc.get(url));
			}
		}
		return respJson;
	}

	/**
	 * @Author: liulh
	 * @Description: 应用设置请求内容封装
	 * @Params: [agentid, reportLocationFlag, logoMediaid, name, description, redirectDomain, isreportenter, homeUrl]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 12:15
	 */
	public static JSONObject getAgentSet(String agentid, String reportLocationFlag, String logoMediaid, String name,
                                         String description, String redirectDomain, String isreportenter, String homeUrl) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(agentid)) {
			json.put("agentid", agentid);
		}
		if (StringUtils.isNotBlank(reportLocationFlag)) {
			json.put("report_location_flag", reportLocationFlag);
		}
		if (StringUtils.isNotBlank(logoMediaid)) {
			json.put("logo_mediaid", logoMediaid);
		}
		if (StringUtils.isNotBlank(name)) {
			json.put("name", name);
		}
		if (StringUtils.isNotBlank(description)) {
			json.put("description", description);
		}
		if (StringUtils.isNotBlank(redirectDomain)) {
			json.put("redirect_domain", redirectDomain);
		}
		if (StringUtils.isNotBlank(isreportenter)) {
			json.put("isreportenter", isreportenter);
		}
		if (StringUtils.isNotBlank(homeUrl)) {
			json.put("home_url", homeUrl);
		}
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 部门创建请求内容封装
	 * @Params: [name, parentid, order, id]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:16
	 */
	public static JSONObject getDepartmentCreate(String name, String parentid, String order, String id) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(name)) {
			json.put("name", name);
		}
		if (StringUtils.isNotBlank(parentid)) {
			json.put("parentid", parentid);
		}
		if (StringUtils.isNotBlank(order)) {
			json.put("order", order);
		}
		if (StringUtils.isNotBlank(id)) {
			json.put("id", id);
		}
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 部门信息更新请求内容封装
	 * @Params: [id, name, parentid, order]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:17
	 */
	public static JSONObject getDepartmentUpdate(String id, String name, String parentid, String order) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(id)) {
			json.put("id", id);
		}
		if (StringUtils.isNotBlank(name)) {
			json.put("name", name);
		}
		if (StringUtils.isNotBlank(parentid)) {
			json.put("parentid", parentid);
		}
		if (StringUtils.isNotBlank(order)) {
			json.put("order", order);
		}
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 文本消息封装
	 * @Params: [touser, toparty, totag, agentid, content, safe]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:27
	 */
	public static JSONObject getMessageText(String touser, String toparty, String totag, String agentid,
                                            String content, Integer safe) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(touser)) {
			json.put("touser", touser);
		}
		if (StringUtils.isNotBlank(toparty)) {
			json.put("toparty", toparty);
		}
		if (StringUtils.isNotBlank(totag)) {
			json.put("totag", totag);
		}
		json.put("msgtype", Constants.MSGTYPE_TEXT);
		if (StringUtils.isNotBlank(agentid)) {
			json.put("agentid", agentid);
		}
		JSONObject textJson = new JSONObject();
		textJson.put("content", content);
		json.put("text", textJson);
		if (safe != null) {
			json.put("safe", safe);
		}
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 图片消息封装
	 * @Params: [touser, toparty, totag, agentid, mediaId, safe]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:28
	 */
	public static JSONObject getMessageImage(String touser, String toparty, String totag, String agentid,
                                             String mediaId, Integer safe) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(touser)) {
			json.put("touser", touser);
		}
		if (StringUtils.isNotBlank(toparty)) {
			json.put("toparty", toparty);
		}
		if (StringUtils.isNotBlank(totag)) {
			json.put("totag", totag);
		}
		json.put("msgtype", Constants.MSGTYPE_IMAGE);
		if (StringUtils.isNotBlank(agentid)) {
			json.put("agentid", agentid);
		}
		JSONObject imageJson = new JSONObject();
		imageJson.put("media_id", mediaId);
		json.put("image", imageJson);
		if (safe != null) {
			json.put("safe", safe);
		}
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 音频消息封装
	 * @Params: [touser, toparty, totag, agentid, mediaId, safe]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:28
	 */
	public static JSONObject getMessageVoice(String touser, String toparty, String totag, String agentid,
                                             String mediaId, Integer safe) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(touser)) {
			json.put("touser", touser);
		}
		if (StringUtils.isNotBlank(toparty)) {
			json.put("toparty", toparty);
		}
		if (StringUtils.isNotBlank(totag)) {
			json.put("totag", totag);
		}
		json.put("msgtype", Constants.MSGTYPE_VOICE);
		if (StringUtils.isNotBlank(agentid)) {
			json.put("agentid", agentid);
		}
		JSONObject voiceJson = new JSONObject();
		voiceJson.put("media_id", mediaId);
		json.put("voice", voiceJson);
		if (safe != null) {
			json.put("safe", safe);
		}
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 视频消息封装
	 * @Params: [touser, toparty, totag, agentid, mediaId, title, description, safe]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:28
	 */
	public static JSONObject getMessageVideo(String touser, String toparty, String totag, String agentid,
                                             String mediaId, String title, String description, Integer safe) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(touser)) {
			json.put("touser", touser);
		}
		if (StringUtils.isNotBlank(toparty)) {
			json.put("toparty", toparty);
		}
		if (StringUtils.isNotBlank(totag)) {
			json.put("totag", totag);
		}
		json.put("msgtype", Constants.MSGTYPE_VIDEO);
		if (StringUtils.isNotBlank(agentid)) {
			json.put("agentid", agentid);
		}
		JSONObject videoJson = new JSONObject();
		videoJson.put("media_id", mediaId);
		videoJson.put("title", title);
		videoJson.put("description", description);
		json.put("video", videoJson);
		if (safe != null) {
			json.put("safe", safe);
		}
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 文件消息封装
	 * @Params: [touser, toparty, totag, agentid, mediaId, safe]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:29
	 */
	public static JSONObject getMessageFile(String touser, String toparty, String totag, String agentid,
                                            String mediaId, Integer safe) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(touser)) {
			json.put("touser", touser);
		}
		if (StringUtils.isNotBlank(toparty)) {
			json.put("toparty", toparty);
		}
		if (StringUtils.isNotBlank(totag)) {
			json.put("totag", totag);
		}
		json.put("msgtype", Constants.MSGTYPE_FILE);
		if (StringUtils.isNotBlank(agentid)) {
			json.put("agentid", agentid);
		}
		JSONObject fileJson = new JSONObject();
		fileJson.put("media_id", mediaId);
		json.put("file", fileJson);
		if (safe != null) {
			json.put("safe", safe);
		}
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 图文消息封装
	 * @Params: [touser, toparty, totag, agentid, newsItems]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:29
	 */
	public static JSONObject getMessageNews(String touser, String toparty, String totag, String agentid,
                                            List<NewsItem> newsItems) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(touser)) {
			json.put("touser", touser);
		}
		if (StringUtils.isNotBlank(toparty)) {
			json.put("toparty", toparty);
		}
		if (StringUtils.isNotBlank(totag)) {
			json.put("totag", totag);
		}
		json.put("msgtype", Constants.MSGTYPE_NEWS);
		if (StringUtils.isNotBlank(agentid)) {
			json.put("agentid", agentid);
		}
		JSONObject newsJson = new JSONObject();
		JSONArray articlesJson = new JSONArray();
		for (NewsItem newsItem : newsItems) {
			JSONObject articleJson = new JSONObject();
			articleJson.put("title", newsItem.getTitle());
			articleJson.put("description", newsItem.getDescription());
			articleJson.put("url", newsItem.getUrl());
			articleJson.put("picurl", newsItem.getPicurl());
			articlesJson.add(articleJson);
		}
		newsJson.put("articles", articlesJson);
		json.put("news", newsJson);
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: mp图文消息封装
	 * @Params: [touser, toparty, totag, agentid, mpnewsItems, safe]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:29
	 */
	public static JSONObject getMessageMpnews(String touser, String toparty, String totag, String agentid,
                                              List<MpnewsItem> mpnewsItems, Integer safe) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(touser)) {
			json.put("touser", touser);
		}
		if (StringUtils.isNotBlank(toparty)) {
			json.put("toparty", toparty);
		}
		if (StringUtils.isNotBlank(totag)) {
			json.put("totag", totag);
		}
		json.put("msgtype", Constants.MSGTYPE_MPNEWS);
		if (StringUtils.isNotBlank(agentid)) {
			json.put("agentid", agentid);
		}
		JSONObject mpnewsJson = new JSONObject();
		JSONArray articlesJson = new JSONArray();
		for (MpnewsItem mpnewsItem : mpnewsItems) {
			JSONObject articleJson = new JSONObject();
			articleJson.put("title", mpnewsItem.getTitle());
			articleJson.put("thumb_media_id", mpnewsItem.getThumbMediaId());
			articleJson.put("author", mpnewsItem.getAuthor());
			articleJson.put("content_source_url", mpnewsItem.getContentSourceUrl());
			articleJson.put("content", mpnewsItem.getContent());
			articleJson.put("digest", mpnewsItem.getDigest());
			articleJson.put("show_cover_pic", mpnewsItem.getShowCoverPic());
			articlesJson.add(articleJson);
		}
		mpnewsJson.put("articles", articlesJson);
		json.put("mpnews", mpnewsJson);
		if (safe != null) {
			json.put("safe", safe);
		}
		return json;
	}

	public static JSONObject getMessageMpnews(String touser, String toparty, String totag, String agentid,
                                              String mediaId, Integer safe) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(touser)) {
			json.put("touser", touser);
		}
		if (StringUtils.isNotBlank(toparty)) {
			json.put("toparty", toparty);
		}
		if (StringUtils.isNotBlank(totag)) {
			json.put("totag", totag);
		}
		json.put("msgtype", Constants.MSGTYPE_MPNEWS);
		if (StringUtils.isNotBlank(agentid)) {
			json.put("agentid", agentid);
		}
		JSONObject mpnewsJson = new JSONObject();
		mpnewsJson.put("media_id", mediaId);
		json.put("mpnews", mpnewsJson);
		if (safe != null) {
			json.put("safe", safe);
		}
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 创建标签请求内容封装
	 * @Params: [tagname, tagid]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:31
	 */
	public static JSONObject getTagCreate(String tagname, String tagid) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(tagname)) {
			json.put("tagname", tagname);
		}
		if (StringUtils.isNotBlank(tagid)) {
			json.put("tagid", tagid);
		}
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 更新标签请求内容封装
	 * @Params: [tagname, tagid]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:32
	 */
	public static JSONObject getTagUpdate(String tagname, String tagid) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(tagname)) {
			json.put("tagname", tagname);
		}
		if (StringUtils.isNotBlank(tagid)) {
			json.put("tagid", tagid);
		}
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 向标签添加成员请求内容封装
	 * @Params: [tagid, userlist, partylist]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:32
	 */
	public static JSONObject getTagAddtagusers(String tagid, String[] userlist, Integer[] partylist) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(tagid)) {
			json.put("tagid", tagid);
		}
		json.put("userlist", userlist);
		json.put("partylist", partylist);
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 删除标签成员请求内容封装
	 * @Params: [tagid, userlist, partylist]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:33
	 */
	public static JSONObject getTagdeltagusers(String tagid, String[] userlist, Integer[] partylist) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(tagid)) {
			json.put("tagid", tagid);
		}
		json.put("userlist", userlist);
		json.put("partylist", partylist);
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 新增成员请求内容封装
	 * @Params: [userid, name, department, position, mobile, gender, email, weixinid, avatarMediaid, extattr]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:33
	 */
	public static JSONObject getUserCreate(String userid, String name, Integer[] department, String position,
                                           String mobile, String gender, String email, String weixinid, String avatarMediaid,
                                           List<Map<String, String>> extattr) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(userid)) {
			json.put("userid", userid);
		}
		if (StringUtils.isNotBlank(name)) {
			json.put("name", name);
		}
		json.put("department", department);
		if (StringUtils.isNotBlank(position)) {
			json.put("position", position);
		}
		if (StringUtils.isNotBlank(mobile)) {
			json.put("mobile", mobile);
		}
		if (StringUtils.isNotBlank(gender)) {
			json.put("gender", gender);
		}
		if (StringUtils.isNotBlank(email)) {
			json.put("email", email);
		}
		if (StringUtils.isNotBlank(weixinid)) {
			json.put("weixinid", weixinid);
		}
		if (StringUtils.isNotBlank(avatarMediaid)) {
			json.put("avatar_mediaid", avatarMediaid);
		}
		if (extattr != null && !extattr.isEmpty()){
			JSONObject extattrJson = new JSONObject();
			JSONArray attrsJson = new JSONArray();
			for (Map<String, String> map : extattr) {
				attrsJson.add(map);
			}
			extattrJson.put("attrs", attrsJson);
			json.put("extattr", extattrJson);
		}
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 更新成员信息请求内容封装
	 * @Params: [userid, name, department, position, mobile, gender, email, weixinid, enable, avatarMediaid, extattr]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:34
	 */
	public static JSONObject getUserUpdate(String userid, String name, Integer[] department, String position,
                                           String mobile, String gender, String email, String weixinid, Integer enable, String avatarMediaid,
                                           List<Map<String, String>> extattr) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(userid)) {
			json.put("userid", userid);
		}
		if (StringUtils.isNotBlank(name)) {
			json.put("name", name);
		}
		json.put("department", department);
		if (StringUtils.isNotBlank(position)) {
			json.put("position", position);
		}
		if (StringUtils.isNotBlank(mobile)) {
			json.put("mobile", mobile);
		}
		if (StringUtils.isNotBlank(gender)) {
			json.put("gender", gender);
		}
		if (StringUtils.isNotBlank(email)) {
			json.put("email", email);
		}
		if (StringUtils.isNotBlank(weixinid)) {
			json.put("weixinid", weixinid);
		}
		if (enable != null) {
			json.put("enable", enable);
		}
		if (StringUtils.isNotBlank(avatarMediaid)) {
			json.put("avatar_mediaid", avatarMediaid);
		}
		JSONObject extattrJson = new JSONObject();
		JSONArray attrsJson = new JSONArray();
		for (Map<String, String> map : extattr) {
			attrsJson.add(map);
		}
		extattrJson.put("attrs", attrsJson);
		json.put("extattr", extattrJson);
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 批量删除成员请求内容封装
	 * @Params: [useridlist]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:34
	 */
	public static JSONObject getUserBatchDelete(String[] useridlist) {
		JSONObject json = new JSONObject();
		json.put("useridlist", useridlist);
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 成员userid换取openid请求内容封装
	 * @Params: [userid, agentid]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:35
	 */
	public static JSONObject getUserConvertToOpenid(String userid, String agentid) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(userid)) {
			json.put("userid", userid);
		}
		if (StringUtils.isNotBlank(agentid)) {
			json.put("agentid", agentid);
		}
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 成员openid换取userid请求内容封装
	 * @Params: [openid]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:35
	 */
	public static JSONObject getUserConvertToUserid(String openid) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(openid)) {
			json.put("openid", openid);
		}
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 邀请关注请求内容封装
	 * @Params: [touser, toparty, totag, url, token, encodingaeskey]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:39
	 */
	public static JSONObject getBatchInviteUser(String touser, String toparty, String totag, String url, String token,
                                                String encodingaeskey) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(touser)) {
			json.put("touser", touser);
		}
		if (StringUtils.isNotBlank(toparty)) {
			json.put("toparty", toparty);
		}
		if (StringUtils.isNotBlank(totag)) {
			json.put("totag", totag);
		}
		JSONObject callbackJson = new JSONObject();
		if (StringUtils.isNotBlank(url)) {
			callbackJson.put("url", url);
		}
		if (StringUtils.isNotBlank(token)) {
			callbackJson.put("token", token);
		}
		if (StringUtils.isNotBlank(encodingaeskey)) {
			callbackJson.put("encodingaeskey", encodingaeskey);
		}
		json.put("callback", callbackJson);
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 增量更新成员信息请求内容封装
	 * @Params: [mediaId, url, token, encodingaeskey]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:40
	 */
	public static JSONObject getBatchSyncUser(String mediaId, String url, String token, String encodingaeskey) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(mediaId)) {
			json.put("media_id", mediaId);
		}
		JSONObject callbackJson = new JSONObject();
		if (StringUtils.isNotBlank(url)) {
			callbackJson.put("url", url);
		}
		if (StringUtils.isNotBlank(token)) {
			callbackJson.put("token", token);
		}
		if (StringUtils.isNotBlank(encodingaeskey)) {
			callbackJson.put("encodingaeskey", encodingaeskey);
		}
		json.put("callback", callbackJson);
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 全量覆盖成员信息请求内容封装
	 * @Params: [mediaId, url, token, encodingaeskey]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:41
	 */
	public static JSONObject getBatchReplaceUser(String mediaId, String url, String token, String encodingaeskey) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(mediaId)) {
			json.put("media_id", mediaId);
		}
		JSONObject callbackJson = new JSONObject();
		if (StringUtils.isNotBlank(url)) {
			callbackJson.put("url", url);
		}
		if (StringUtils.isNotBlank(token)) {
			callbackJson.put("token", token);
		}
		if (StringUtils.isNotBlank(encodingaeskey)) {
			callbackJson.put("encodingaeskey", encodingaeskey);
		}
		json.put("callback", callbackJson);
		return json;
	}

	/**
	 * @Author: liulh
	 * @Description: 全量覆盖部门请求内容封装
	 * @Params: [mediaId, url, token, encodingaeskey]
	 * @return: net.sf.json.JSONObject
	 * @Date: 2018/3/14 15:42
	 */
	public static JSONObject getBatchReplaceParty(String mediaId, String url, String token, String encodingaeskey) {
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(mediaId)) {
			json.put("media_id", mediaId);
		}
		JSONObject callbackJson = new JSONObject();
		if (StringUtils.isNotBlank(url)) {
			callbackJson.put("url", url);
		}
		if (StringUtils.isNotBlank(token)) {
			callbackJson.put("token", token);
		}
		if (StringUtils.isNotBlank(encodingaeskey)) {
			callbackJson.put("encodingaeskey", encodingaeskey);
		}
		json.put("callback", callbackJson);
		return json;
	}

}
