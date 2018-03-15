package com.wechat.corp.service.impl;

import com.wechat.corp.bean.NewsItem;
import com.wechat.corp.callback.CallbackUtils;
import com.wechat.corp.service.interfaces.MsgHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import java.util.List;

/**
 * @Author: liulh
 * @Description: 普通类型消息接收
 * @Date: 2018/3/14 16:39
 * @Modified By:
 */
public class MsgHandlerImpl implements MsgHandler {
	private transient static final Logger LOGGER = LoggerFactory.getLogger(MsgHandlerImpl.class);

	public String text(Element root, String content) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		if(StringUtils.isBlank(content)){
			content = CallbackUtils.getFirstTextContent(root, "Content");
		}
		String msgId = CallbackUtils.getFirstTextContent(root, "MsgId");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		LOGGER.info("ToUserName : " + toUserName + " FromUserName : " + fromUserName +
					" CreateTime : " + createTime + " MsgType : " + msgType + " Content : " + content +
					" MsgId : " + msgId + " AgentID : " + agentID);
		String retString = CallbackUtils.getTextRespString(fromUserName, toUserName, createTime, content);
		return retString;
	}
	
	public String news(Element root, String content, List<NewsItem> newsItems) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		if(StringUtils.isBlank(content)){
			content = CallbackUtils.getFirstTextContent(root, "Content");
		}
		String msgId = CallbackUtils.getFirstTextContent(root, "MsgId");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		LOGGER.info("ToUserName : " + toUserName + " FromUserName : " + fromUserName +
				" CreateTime : " + createTime + " MsgType : " + msgType + " Content : " + content +
				" MsgId : " + msgId + " AgentID : " + agentID);
		String retString = CallbackUtils.getNewsRespString(toUserName, fromUserName, createTime, String.valueOf(newsItems.size()), newsItems);
		return retString;
	}

	public String image(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String picUrl = CallbackUtils.getFirstTextContent(root, "PicUrl");
		String mediaId = CallbackUtils.getFirstTextContent(root, "MediaId");
		String msgId = CallbackUtils.getFirstTextContent(root, "MsgId");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		LOGGER.info("ToUserName : " + toUserName + " FromUserName : " + fromUserName +
				" CreateTime : " + createTime + " MsgType : " + msgType + " PicUrl : " + picUrl +
				" MediaId : " + mediaId + " MsgId : " + msgId + " AgentID : " + agentID);
		String retString = CallbackUtils.getImageRespString(fromUserName, toUserName, createTime, mediaId);
		return retString;
	}

	public String voice(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String mediaId = CallbackUtils.getFirstTextContent(root, "MediaId");
		String format = CallbackUtils.getFirstTextContent(root, "Format");
		String msgId = CallbackUtils.getFirstTextContent(root, "MsgId");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		LOGGER.info("ToUserName : " + toUserName + " FromUserName : " + fromUserName +
				" CreateTime : " + createTime + " MsgType : " + msgType + " Format : " + format +
				" MediaId : " + mediaId + " MsgId : " + msgId + " AgentID : " + agentID);
		String retString = CallbackUtils.getVoiceRespString(fromUserName, toUserName, createTime, mediaId);
		return retString;
	}

	public String video(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String mediaId = CallbackUtils.getFirstTextContent(root, "MediaId");
		String thumbMediaId = CallbackUtils.getFirstTextContent(root, "ThumbMediaId");
		String msgId = CallbackUtils.getFirstTextContent(root, "MsgId");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		LOGGER.info("ToUserName : " + toUserName + " FromUserName : " + fromUserName +
				" CreateTime : " + createTime + " MsgType : " + msgType + " ThumbMediaId : " + thumbMediaId +
				" MediaId : " + mediaId + " MsgId : " + msgId + " AgentID : " + agentID);
		String retString = CallbackUtils.getVideoRespString(fromUserName, toUserName, createTime, thumbMediaId, "test",
				"这是一个测试！");
		return retString;
	}

	public String shortvideo(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String mediaId = CallbackUtils.getFirstTextContent(root, "MediaId");
		String thumbMediaId = CallbackUtils.getFirstTextContent(root, "ThumbMediaId");
		String msgId = CallbackUtils.getFirstTextContent(root, "MsgId");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		LOGGER.info("ToUserName : " + toUserName + " FromUserName : " + fromUserName +
				" CreateTime : " + createTime + " MsgType : " + msgType + " ThumbMediaId : " + thumbMediaId +
				" MediaId : " + mediaId + " MsgId : " + msgId + " AgentID : " + agentID);
		String retString = CallbackUtils.getVideoRespString(fromUserName, toUserName, createTime, thumbMediaId, "test",
				"这是一个测试!");
		return retString;
	}

	public String location(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String location_X = CallbackUtils.getFirstTextContent(root, "Location_X");
		String location_Y = CallbackUtils.getFirstTextContent(root, "Location_Y");
		String scale = CallbackUtils.getFirstTextContent(root, "Scale");
		String label = CallbackUtils.getFirstTextContent(root, "Label");
		String msgId = CallbackUtils.getFirstTextContent(root, "MsgId");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		LOGGER.info("ToUserName : " + toUserName + " FromUserName : " + fromUserName +
				" CreateTime : " + createTime + " MsgType : " + msgType + " Location_X : " + location_X +
				" Location_Y : " + location_Y + " Scale : " + scale + "Label : " + label +
				" MsgId : " + msgId + " AgentID : " + agentID);
		String retString = CallbackUtils.getTextRespString(fromUserName, toUserName, createTime, "Label : " + label
				+ ", Scale : " + scale + ", Location_X : " + location_X + ", Location_Y : " + location_Y);
		return retString;
	}

	public String link(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String title = CallbackUtils.getFirstTextContent(root, "Title");
		String description = CallbackUtils.getFirstTextContent(root, "Description");
		String picUrl = CallbackUtils.getFirstTextContent(root, "PicUrl");
		String msgId = CallbackUtils.getFirstTextContent(root, "MsgId");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		LOGGER.info("ToUserName : " + toUserName + " FromUserName : " + fromUserName +
				" CreateTime : " + createTime + " MsgType : " + msgType + "Title : " + title +
				"Description : " + description + "PicUrl : " + picUrl + " MsgId : " + msgId + " AgentID : " + agentID);
		String retString = CallbackUtils.getTextRespString(fromUserName, toUserName, createTime, "Title : " + title
				+ ", Description : " + description + ", PicUrl : " + picUrl);
		return retString;
	}

}
