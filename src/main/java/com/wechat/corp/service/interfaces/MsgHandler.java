package com.wechat.corp.service.interfaces;

import com.wechat.corp.bean.NewsItem;
import org.w3c.dom.Element;

import java.util.List;

public interface MsgHandler {

	public String text(Element root, String content);
	
	public String news(Element root, String content, List<NewsItem> newsItems);

	public String image(Element root);

	public String voice(Element root);

	public String video(Element root);

	public String shortvideo(Element root);

	public String location(Element root);

	public String link(Element root);

}
