package com.wechat.corp.callback;

import com.wechat.corp.bean.NewsItem;
import com.wechat.corp.common.WXCorpConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.text.MessageFormat;
import java.util.List;

/**
 * @Author: liulh
 * @Description: 消息接收与回复消息内容操作
 * @Date: 2018/3/14 11:48
 * @Modified By:
 */
public abstract class CallbackUtils {

	/**
	 * @Author: liulh
	 * @Description: 获取消息节点内容
	 * @Params: [root, tagName]
	 * @return: java.lang.String
	 * @Date: 2018/3/14 11:51
	 */
	public static String getFirstTextContent(Element root, String tagName) {
		NodeList nodeList = root.getElementsByTagName(tagName);
		if (nodeList.getLength() > 0) {
			return nodeList.item(0).getTextContent();
		}
		return null;
	}

	/**
	 * @Author: liulh
	 * @Description: 获取消息节点内容列表内的具体数据
	 * @Params: [root, tagName0, tagName1]
	 * @return: java.lang.String
	 * @Date: 2018/3/14 11:53
	 */
	public static String getFirstTextContent(Element root, String tagName0, String tagName1) {
		NodeList nodeList = root.getElementsByTagName(tagName0);
		if (nodeList.getLength() > 0) {
			Node node = nodeList.item(0);
			Document document = node.getOwnerDocument();
			Element element = document.getDocumentElement();
			return getFirstTextContent(element, tagName1);
		}
		return null;
	}

	/**
	 * @Author: liulh
	 * @Description: 封装文本类型返回消息
	 * @Params: [toUserName, fromUserName, createTime, content]
	 * @return: java.lang.String
	 * @Date: 2018/3/14 11:54
	 */
	public static String getTextRespString(String toUserName, String fromUserName, String createTime, String content) {
		String textFormatString = "<xml>\n" + "<ToUserName><![CDATA[{0}]]></ToUserName>\n"
				+ "<FromUserName><![CDATA[{1}]]></FromUserName>\n" + "<CreateTime>{2}</CreateTime>\n"
				+ "<MsgType><![CDATA[{3}]]></MsgType>\n" + "<Content><![CDATA[{4}]]></Content>\n" + "</xml>";
		return MessageFormat.format(textFormatString, toUserName, fromUserName, createTime, WXCorpConstants.MSGTYPE_TEXT,
				content);
	}

	/**
	 * @Author: liulh
	 * @Description: 封装图片类型返回消息
	 * @Params: [toUserName, fromUserName, createTime, mediaId]
	 * @return: java.lang.String
	 * @Date: 2018/3/14 11:55
	 */
	public static String getImageRespString(String toUserName, String fromUserName, String createTime, String mediaId) {
		String textFormatString = "<xml>\n" + "<ToUserName><![CDATA[{0}]]></ToUserName>\n"
				+ "<FromUserName><![CDATA[{1}]]></FromUserName>\n" + "<CreateTime>{2}</CreateTime>\n"
				+ "<MsgType><![CDATA[{3}]]></MsgType>\n" + "<Image>\n" + "<MediaId><![CDATA[{4}]]></MediaId>\n"
				+ "</Image>\n" + "</xml>";
		return MessageFormat.format(textFormatString, toUserName, fromUserName, createTime, WXCorpConstants.MSGTYPE_IMAGE,
				mediaId);
	}

	/**
	 * @Author: liulh
	 * @Description: 封装语音类型返回消息
	 * @Params: [toUserName, fromUserName, createTime, mediaId]
	 * @return: java.lang.String
	 * @Date: 2018/3/14 11:55
	 */
	public static String getVoiceRespString(String toUserName, String fromUserName, String createTime, String mediaId) {
		String textFormatString = "<xml>\n" + "<ToUserName><![CDATA[{0}]]></ToUserName>\n"
				+ "<FromUserName><![CDATA[{1}]]></FromUserName>\n" + "<CreateTime>{2}</CreateTime>\n"
				+ "<MsgType><![CDATA[{3}]]></MsgType>\n" + "<Voice>\n" + "<MediaId><![CDATA[{4}]]></MediaId>\n"
				+ "</Voice>\n" + "</xml>";
		return MessageFormat.format(textFormatString, toUserName, fromUserName, createTime, WXCorpConstants.MSGTYPE_VOICE,
				mediaId);
	}

	/**
	 * @Author: liulh
	 * @Description: 封装视频类型返回消息
	 * @Params: [toUserName, fromUserName, createTime, mediaId, title, description]
	 * @return: java.lang.String
	 * @Date: 2018/3/14 11:56
	 */
	public static String getVideoRespString(String toUserName, String fromUserName, String createTime, String mediaId,
                                            String title, String description) {
		String textFormatString = "<xml>\n" + "<ToUserName><![CDATA[{0}]]></ToUserName>\n"
				+ "<FromUserName><![CDATA[{1}]]></FromUserName>\n" + "<CreateTime>{2}</CreateTime>\n"
				+ "<MsgType><![CDATA[{3}]]></MsgType>\n" + "<Video>\n" + "<MediaId><![CDATA[{4}]]></MediaId>\n"
				+ "<Title><![CDATA[{5}]]></Title>\n" + "<Description><![CDATA[{6}]]></Description>\n" + "</Video>\n"
				+ "</xml>";
		return MessageFormat.format(textFormatString, toUserName, fromUserName, createTime, WXCorpConstants.MSGTYPE_VIDEO,
				mediaId, title, description);
	}

	/**
	 * @Author: liulh
	 * @Description: 封装图文类型返回消息
	 * @Params: [toUserName, fromUserName, createTime, articleCount, newsItems]
	 * @return: java.lang.String
	 * @Date: 2018/3/14 11:57
	 */
	public static String getNewsRespString(String toUserName, String fromUserName, String createTime,
                                           String articleCount, List<NewsItem> newsItems) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>\n");
		sb.append("<ToUserName><![CDATA[");
		sb.append(toUserName);
		sb.append("]]></ToUserName>\n");
		sb.append("<FromUserName><![CDATA[");
		sb.append(fromUserName);
		sb.append("]]></FromUserName>\n");
		sb.append("<CreateTime>");
		sb.append(createTime);
		sb.append("</CreateTime>\n");
		sb.append("<MsgType><![CDATA[");
		sb.append(WXCorpConstants.MSGTYPE_NEWS);
		sb.append("]]></MsgType>\n");
		sb.append("<ArticleCount>");
		sb.append(articleCount);
		sb.append("</ArticleCount>\n");
		sb.append("<Articles>\n");
		for (NewsItem newsItem : newsItems) {
			sb.append("<item>\n");
			sb.append("<Title><![CDATA[");
			sb.append(newsItem.getTitle());
			sb.append("]]></Title>\n");
			sb.append("<Description><![CDATA[");
			sb.append(newsItem.getDescription());
			sb.append("]]></Description>\n");
			sb.append("<PicUrl><![CDATA[");
			sb.append(newsItem.getPicurl());
			sb.append("]]></PicUrl>\n");
			sb.append("<Url><![CDATA[");
			sb.append(newsItem.getUrl());
			sb.append("]]></Url>\n");
			sb.append("</item>\n");
		}
		sb.append("</Articles>\n");
		sb.append("</xml>");
		String ret = sb.toString();
		return ret;
	}

}
