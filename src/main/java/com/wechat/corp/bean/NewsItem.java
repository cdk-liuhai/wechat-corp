package com.wechat.corp.bean;

/**
 * @Author: liulh
 * @Description: 图文消息实体类
 * @Date: 2018/3/14 11:48
 * @Modified By:
 */
public class NewsItem {

	private String title;

	private String description;

	private String url;

	private String picurl;

	public NewsItem() {
		super();
	}

	public NewsItem(String title, String description, String url, String picurl) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
		this.picurl = picurl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

}
