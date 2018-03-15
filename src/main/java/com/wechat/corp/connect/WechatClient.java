package com.wechat.corp.connect;

import com.wechat.corp.bean.WechatException;

import java.io.File;
import java.io.OutputStream;


public interface WechatClient {

	public String getAccessToken() throws WechatException;

	public String getAccessToken(boolean newOne) throws WechatException;

	public String get(String uri) throws WechatException;

	public String post(String uri, String msg) throws WechatException;

	public String upload(String uri, File file) throws WechatException;

	public String download(String uri, OutputStream os) throws WechatException;

	public String download(String uri, DownLoadHandler handler) throws WechatException;

	/**
	 * @throws WechatException
	 * @Title: upLoad   
	 * @Description: 上传素材
	 * @param: @param uploadUrl
	 * @param: @param file
	 * @param: @return     
	 * @return: String    
	 * @throws  
	 */
	public String upLoad(String uploadUrl, File file) throws WechatException;

}
