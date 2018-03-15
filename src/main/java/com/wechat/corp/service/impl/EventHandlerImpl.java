package com.wechat.corp.service.impl;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.callback.CallbackUtils;
import com.wechat.corp.cgi.CgiUtils;
import com.wechat.corp.cgi.Message;
import com.wechat.corp.connect.HttpClientFactory;
import com.wechat.corp.connect.WechatClientHCE;
import com.wechat.corp.service.interfaces.EventHandler;
import net.sf.json.JSONObject;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: liulh
 * @Description: 事件类型消息接收
 * @Date: 2018/3/14 16:02
 * @Modified By:
 */
public class EventHandlerImpl implements EventHandler {

	private transient static final Logger LOGGER = LoggerFactory.getLogger(EventHandlerImpl.class);

	/**
	 * @Author: liulh
	 * @Description: 关注事件
	 * @Params: [root]
	 * @return: java.lang.String
	 * @Date: 16:28 2018/3/14
	 */
	public String subscribe(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		LOGGER.info("ToUserName : " + toUserName + " FromUserName : " + fromUserName +
				" CreateTime : " + createTime + " MsgType : " + msgType + " Event : " + event +
				" AgentID : " + agentID);
		String retString = CallbackUtils.getTextRespString(fromUserName, toUserName, createTime, fromUserName);
		return retString;
	}

	public String location(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String latitude = CallbackUtils.getFirstTextContent(root, "Latitude");
		String longitude = CallbackUtils.getFirstTextContent(root, "Longitude");
		String precision = CallbackUtils.getFirstTextContent(root, "Precision");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		LOGGER.info("ToUserName : " + toUserName + " FromUserName : " + fromUserName +
				" CreateTime : " + createTime + " MsgType : " + msgType + " Event : " + event +
				" Latitude : " + latitude + " Longitude : " + longitude + " Precision : " + precision +
				" AgentID : " + agentID);
		try {
			WechatClientHCE wc = new WechatClientHCE("","");
			HttpClient httpClient = HttpClientFactory.createHttpClient();
			wc.setHttpClient(httpClient);
			LOGGER.info("发送text消息。");
			JSONObject json0 = CgiUtils.getMessageText(fromUserName, null, null, "0", "Latitude : " + latitude
					+ ", Longitude : " + longitude + ", Precision : " + precision, 0);
			LOGGER.info("text消息：" + Message.send(json0, wc, wc.getAccessToken()));
		} catch (WechatException e) {
			LOGGER.error("异常！" + e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("异常！" + e.getMessage(), e);
		} catch (KeyManagementException e) {
			LOGGER.error("异常！" + e.getMessage(), e);
		}
		return null;
	}

	public String click(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String eventKey = CallbackUtils.getFirstTextContent(root, "EventKey");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		LOGGER.info("ToUserName : " + toUserName + " FromUserName : " + fromUserName +
				" CreateTime : " + createTime + " MsgType : " + msgType + " Event : " + event +
				" EventKey : " + eventKey + " AgentID : " + agentID);
		String retString = CallbackUtils.getTextRespString(fromUserName, toUserName, createTime, "Event : " + event
				+ ", EventKey" + eventKey);
		LOGGER.info(retString);
		return retString;
	}

	public String view(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String eventKey = CallbackUtils.getFirstTextContent(root, "EventKey");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		LOGGER.info("ToUserName : " + toUserName + " FromUserName : " + fromUserName +
				" CreateTime : " + createTime + " MsgType : " + msgType + " Event : " + event +
				" EventKey : " + eventKey + " AgentID : " + agentID);
		return null;
	}

	public String scancodePush(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String eventKey = CallbackUtils.getFirstTextContent(root, "EventKey");
		String scanType = CallbackUtils.getFirstTextContent(root, "ScanCodeInfo", "ScanType");
		String scanResult = CallbackUtils.getFirstTextContent(root, "ScanCodeInfo", "scanResult");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		LOGGER.info("ToUserName : " + toUserName + " FromUserName : " + fromUserName +
				" CreateTime : " + createTime + " MsgType : " + msgType + " Event : " + event +
				" EventKey : " + eventKey + " ScanType : " + scanType + " ScanResult : " + scanResult + " AgentID : " + agentID);
		return null;
	}

	public String scancodeWaitmsg(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String eventKey = CallbackUtils.getFirstTextContent(root, "EventKey");
		String scanType = CallbackUtils.getFirstTextContent(root, "ScanCodeInfo", "ScanType");
		String scanResult = CallbackUtils.getFirstTextContent(root, "ScanCodeInfo", "scanResult");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		LOGGER.info("ToUserName : " + toUserName + " FromUserName : " + fromUserName +
				" CreateTime : " + createTime + " MsgType : " + msgType + " Event : " + event +
				" EventKey :" + eventKey + " ScanType : " + scanType + " ScanResult : " + scanResult + " AgentID : " + agentID);
		return null;
	}

	public String picSysphoto(Element root) {
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String eventKey = CallbackUtils.getFirstTextContent(root, "EventKey");
		LOGGER.info("Event : " + event + " EventKey : " + eventKey);
		return null;
	}

	public String picPhotoOrAlbum(Element root) {
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String eventKey = CallbackUtils.getFirstTextContent(root, "EventKey");
		LOGGER.info("Event : " + event + " EventKey : " + eventKey);
		return null;
	}

	public String picWeixin(Element root) {
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String eventKey = CallbackUtils.getFirstTextContent(root, "EventKey");
		LOGGER.info("Event : " + event + " EventKey : " + eventKey);
		return null;
	}

	public String locationSelect(Element root) {
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String eventKey = CallbackUtils.getFirstTextContent(root, "EventKey");
		LOGGER.info("Event : " + event + " EventKey : " + eventKey);
		return null;
	}

	public String enterAgent(Element root) {
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String eventKey = CallbackUtils.getFirstTextContent(root, "EventKey");
		LOGGER.info("Event : " + event + " EventKey : " + eventKey);
		return null;
	}

	public String batchJobResult(Element root) {
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String errCode = CallbackUtils.getFirstTextContent(root, "ErrCode");
		LOGGER.info("Event : " + event + " ErrCode : " + errCode);
		return null;
	}

}
