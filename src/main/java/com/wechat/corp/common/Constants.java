package com.wechat.corp.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: liulh
 * @Description: 常量接口
 * @Date: 2018/3/14 15:59
 * @Modified By:
 */
public class Constants {

	private transient static final Logger LOGGER = LoggerFactory.getLogger(Constants.class);

	public static final String CORP1 = "aialmCorp";
	public static final String CORP2 = "asiainfoCorp";
	public static final String CORP_ID = "CorpID";
	public static final String MEMEBERS_SECRET = "MemebersSecret";
	public static final String TEST_AGENTID = "TestAgentid";
	public static final String TEST_SECRET = "TestSecret";

	//不合法的access_token
	public static final String ILLEGAL_TOKEN = "40014";
	//access_token已过期
	public static final String OVERDUE_TOKEN = "42001";

	private static Map<String, List<Map<String, String>>> corpData = new HashMap<>();

	public static void init () {
		DocumentBuilderFactory a = DocumentBuilderFactory.newInstance();
		try {
			//创建DocumentBuilder对象
			DocumentBuilder b = a.newDocumentBuilder();
			//通过DocumentBuilder对象parse方法返回一个Document对象
			Document document = b.parse(Constants.class.getClassLoader().getResource("wechat_corp.xml").getFile());
			//通过Document对象的getElementsByTagName()返根节点
			Node rootCorp = document.getElementsByTagName("rootCorp").item(0);
			//解析rootCorp节点的子节点
			NodeList childlist = rootCorp.getChildNodes();
			for(int i = 0; i<childlist.getLength(); i++){
				//区分出text类型的node以及element类型的node
				if(childlist.item(i).getNodeType() == Node.ELEMENT_NODE){
					//获取每个企业微信具体参数信息
					String nodeName = childlist.item(i).getNodeName();
					//记录单个企业微信所有属性
					List<Map<String, String>> elementList = new ArrayList<>();
					//记录每个属性
					Map<String, String> element = new HashMap<>();
					NodeList corpList = document.getElementsByTagName(nodeName).item(0).getChildNodes();
					for(int m = 0; m<corpList.getLength(); m++){
						if (corpList.item(m).getNodeType() == Node.ELEMENT_NODE) {
							element.put(corpList.item(m).getNodeName(), corpList.item(m).getTextContent());
						}
					}
					elementList.add(element);
					corpData.put(nodeName, elementList);
				}
			}
		} catch (Exception e) {
			LOGGER.error("读取企业微信配置信息异常！", e);
		}
	}

	public static String getCorpElement(String key1, String key2) {
		String key = key1 + "." + key2;
		return getCorpElement(key);
	}

	public static String getCorpElement(String key) {
		if (corpData.isEmpty()) {
			init();
		}
		String value = null;
		String[] keys = key.split("\\.");
		List<Map<String, String>> list = corpData.get(keys[0]);
		if (list != null && !list.isEmpty()) {
			for (Map<String, String> stringMap : list) {
				if (stringMap.containsKey(keys[1])) {
					value = stringMap.get(keys[1]);
				}
			}
		}
		return value;
	}

	//OAuth2.0鉴权
	public final static String LOCAL_PAGE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	//获取accessToken
	public final static String URL_GETTOKEN = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={0}&corpsecret={1}";
	//鉴权根据user_tiket获取成员详细信息
	public final static String URL_USER_DETAIL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserdetail?access_token={0}&lang=zh_CN";
	//二次验证
	public final static String URL_USER_AUTHSUCC = "https://qyapi.weixin.qq.com/cgi-bin/user/authsucc?access_token={0}&userid={1}";
	//创建成员
	public final static String URL_USER_CREATE = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token={0}";
	//更新成员
	public final static String URL_USER_UPDATE = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token={0}";
	//删除微信成员
	public final static String URL_USER_DELETE = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token={0}&userid={1}";
	//批量删除微信成员
	public final static String URL_USER_BATCHDELETE = "https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token={0}";
	//获取微信成员信息
	public final static String URL_USER_GET = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token={0}&userid={1}";
	//获取部门成员
	public final static String URL_USER_SIMPLELIST = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token={0}&department_id={1}&fetch_child={2}";
	//获取部门成员详情
	public final static String URL_USER_LIST = "https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token={0}&department_id={1}&fetch_child={2}";
	//根据code获取成员信息
	public final static String URL_USER_GETUSERINFO = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token={0}&code={1}";
	//userId转换openId
	public final static String URL_USER_CONVERTTOOPENID = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid?access_token={0}";
	//openId转换userId
	public final static String URL_USER_CONVERTTOUSERID = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_userid?access_token={0}";
	//创建部门
	public final static String URL_DEPARTMENT_CREATE = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token={0}";
	//更新部门
	public final static String URL_DEPARTMENT_UPDATE = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token={0}";
	//删除部门
	public final static String URL_DEPARTMENT_DELETE = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token={0}&id={1}";
	//获取部门
	public final static String URL_DEPARTMENT_LIST = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token={0}&id={1}";
	//创建标签
	public final static String URL_TAG_CREATE = "https://qyapi.weixin.qq.com/cgi-bin/tag/create?access_token={0}";

	public final static String URL_TAG_UPDATE = "https://qyapi.weixin.qq.com/cgi-bin/tag/update?access_token={0}";

	public final static String URL_TAG_DELETE = "https://qyapi.weixin.qq.com/cgi-bin/tag/delete?access_token={0}&tagid={1}";

	public final static String URL_TAG_GET = "https://qyapi.weixin.qq.com/cgi-bin/tag/get?access_token={0}&tagid={1}";

	public final static String URL_TAG_ADDTAGUSERS = "https://qyapi.weixin.qq.com/cgi-bin/tag/addtagusers?access_token={0}";

	public final static String URL_TAG_DELTAGUSERS = "https://qyapi.weixin.qq.com/cgi-bin/tag/deltagusers?access_token={0}";

	public final static String URL_TAG_LIST = "https://qyapi.weixin.qq.com/cgi-bin/tag/list?access_token={0}";

	//创建菜单
	public final static String URL_MENU_CREATE = "https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token={0}&agentid={1}";
	//删除菜单
	public final static String URL_MENU_DELETE = "https://qyapi.weixin.qq.com/cgi-bin/menu/delete?access_token={0}&agentid={1}";
	//获取菜单
	public final static String URL_MENU_GET = "https://qyapi.weixin.qq.com/cgi-bin/menu/get?access_token={0}&agentid={1}";
	//消息发送
	public final static String URL_MESSAGE_SEND = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token={0}";
	//上传素材
	public final static String URL_MEDIA_UPLOAD = "https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token={0}&type={1}";
	//获取素材
	public final static String URL_MEDIA_GET = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token={0}&media_id={1}";

	public final static String URL_MEDIA_UPLOADIMG = "https://qyapi.weixin.qq.com/cgi-bin/media/uploadimg?access_token={0}";

	public final static String URL_MATERIAL_ADDMATERIAL = "https://qyapi.weixin.qq.com/cgi-bin/material/add_material?agentid={0}&type={1}&access_token={2}";

	public final static String URL_MATERIAL_ADDMPNEWS = "https://qyapi.weixin.qq.com/cgi-bin/material/add_mpnews?access_token={0}";

	public final static String URL_MATERIAL_GET = "https://qyapi.weixin.qq.com/cgi-bin/material/get?access_token={0}&media_id={1}&agentid={2}";

	public final static String URL_MATERIAL_DEL = "https://qyapi.weixin.qq.com/cgi-bin/material/del?access_token={0}&agentid={1}&media_id={2}";

	public final static String URL_MATERIAL_UPDATEMPNEWS = "https://qyapi.weixin.qq.com/cgi-bin/material/update_mpnews?access_token={0}";

	public final static String URL_MATERIAL_GETCOUNT = "https://qyapi.weixin.qq.com/cgi-bin/material/get_count?access_token={0}&agentid={1}";

	public final static String URL_MATERIAL_BATCHGET = "https://qyapi.weixin.qq.com/cgi-bin/material/batchget?access_token={0}";

	public final static String URL_AGENT_GET = "https://qyapi.weixin.qq.com/cgi-bin/agent/get?access_token={0}&agentid={1}";

	public final static String URL_AGENT_SET = "https://qyapi.weixin.qq.com/cgi-bin/agent/set?access_token={0}";

	public final static String URL_AGENT_LIST = "https://qyapi.weixin.qq.com/cgi-bin/agent/list?access_token={0}";

	public final static String RESP_ACCESS_TOKEN = "access_token";

	public final static String RESP_SUITE_ACCESS_TOKEN = "suite_access_token";

	public final static String RESP_EXPIRES_IN = "expires_in";

	public final static String RESP_ERRCODE = "errcode";

	public final static String RESP_ERRMSG = "errmsg";

	public final static String MSGTYPE_TEXT = "text";

	public final static String MSGTYPE_IMAGE = "image";

	public final static String MSGTYPE_VOICE = "voice";

	public final static String MSGTYPE_VIDEO = "video";

	public final static String MSGTYPE_FILE = "file";

	public final static String MSGTYPE_SHORTVIDEO = "shortvideo";

	public final static String MSGTYPE_LOCATION = "location";

	public final static String MSGTYPE_LINK = "link";

	public final static String MSGTYPE_EVENT = "event";

	public final static String MSGTYPE_NEWS = "news";

	public final static String MSGTYPE_MPNEWS = "mpnews";

	public final static String EVENT_SUBSCRIBE = "subscribe";

	public final static String EVENT_LOCATION = "LOCATION";

	public final static String EVENT_CLICK = "click";

	public final static String EVENT_VIEW = "view";

	public final static String EVENT_SCANCODE_PUSH = "scancode_push";

	public final static String EVENT_SCANCODE_WAITMSG = "scancode_waitmsg";

	public final static String EVENT_PIC_SYSPHOTO = "pic_sysphoto";

	public final static String EVENT_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";

	public final static String EVENT_PIC_WEIXIN = "pic_weixin";

	public final static String EVENT_LOCATION_SELECT = "location_select";

	public final static String EVENT_ENTER_AGENT = "enter_agent";

	public final static String EVENT_BATCH_JOB_RESULT = "batch_job_result";

	public final static String INFOTYPE_SUITE_TICKET = "suite_ticket";

	public final static String INFOTYPE_CHANGE_AUTH = "change_auth";

	public final static String INFOTYPE_CANCEL_AUTH = "cancel_auth";

	public final static String BATCH_TYPE_SYNC_USER = "sync_user";

	public final static String BATCH_TYPE_REPLACE_USER = "replace_user";

	public final static String BATCH_TYPE_INVITE_USER = "invite_user";

	public final static String BATCH_TYPE_REPLACE_PARTY = "replace_party";

	public final static String URL_BATCH_INVITEUSER = "https://qyapi.weixin.qq.com/cgi-bin/batch/inviteuser?access_token={0}";

	public final static String URL_BATCH_SYNCUSER = "https://qyapi.weixin.qq.com/cgi-bin/batch/syncuser?access_token={0}";

	public final static String URL_BATCH_REPLACEUSER = "https://qyapi.weixin.qq.com/cgi-bin/batch/replaceuser?access_token={0}";

	public final static String URL_BATCH_REPLACEPARTY = "https://qyapi.weixin.qq.com/cgi-bin/batch/replaceparty?access_token={0}";

	public final static String URL_BATCH_GETRESULT = "https://qyapi.weixin.qq.com/cgi-bin/batch/getresult?access_token={0}&jobid={1}";

	//第三方应用
	public final static String URL_GETSUITETOKEN = "https://qyapi.weixin.qq.com/cgi-bin/service/get_suite_token";

	public final static String URL_GETCORPTOKEN = "https://qyapi.weixin.qq.com/cgi-bin/service/get_corp_token?suite_access_token={0}";

	public final static String URL_SERVICE_GETPREAUTHCODE = "https://qyapi.weixin.qq.com/cgi-bin/service/get_pre_auth_code?suite_access_token={0}";

	public final static String URL_SERVICE_SETSESSIONINFO = "https://qyapi.weixin.qq.com/cgi-bin/service/set_session_info?suite_access_token={0}";

	public final static String URL_SERVICE_GETPERMANENTCODE = "https://qyapi.weixin.qq.com/cgi-bin/service/get_permanent_code?suite_access_token={0}";

	public final static String URL_SERVICE_GETAUTHINFO = "https://qyapi.weixin.qq.com/cgi-bin/service/get_auth_info?suite_access_token={0}";

	public final static String URL_GETCALLBACKIP = "https://qyapi.weixin.qq.com/cgi-bin/getcallbackip?access_token={0}";

	public final static String URL_INVITE_SEND = "https://qyapi.weixin.qq.com/cgi-bin/invite/send?access_token={0}";

}
