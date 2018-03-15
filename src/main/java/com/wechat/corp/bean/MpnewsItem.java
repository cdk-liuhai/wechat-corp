package com.wechat.corp.bean;

/**
 * @Author: liulh
 * @Description: mp图文消息实体类
 * @Date: 2018/3/14 11:47
 * @Modified By:
 */
public class MpnewsItem {

	private String title;

	private String thumbMediaId;

	private String author;

	private String contentSourceUrl;

	private String content;

	private String digest;

	private String showCoverPic;

	public MpnewsItem() {
		super();
	}

	public MpnewsItem(String title, String thumbMediaId, String author, String contentSourceUrl, String content,
                      String digest, String showCoverPic) {
		super();
		this.title = title;
		this.thumbMediaId = thumbMediaId;
		this.author = author;
		this.contentSourceUrl = contentSourceUrl;
		this.content = content;
		this.digest = digest;
		this.showCoverPic = showCoverPic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContentSourceUrl() {
		return contentSourceUrl;
	}

	public void setContentSourceUrl(String contentSourceUrl) {
		this.contentSourceUrl = contentSourceUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getShowCoverPic() {
		return showCoverPic;
	}

	public void setShowCoverPic(String showCoverPic) {
		this.showCoverPic = showCoverPic;
	}

}
