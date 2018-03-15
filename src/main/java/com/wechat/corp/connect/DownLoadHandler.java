package com.wechat.corp.connect;

import com.wechat.corp.bean.WechatException;

import java.io.InputStream;

/**
 * @Author: liulh
 * @Description: 回调式下载接口。
 * @Date: 2018/3/14 16:00
 * @Modified By:
 */
public interface DownLoadHandler {

	/**
	 * 根据contentType和fileName保存输入流中的内容，可抛出WechatException异常。
	 * 
	 * @param contentType
	 *            Http头中的Content-Type。
	 * @param fileName
	 *            Http头中的Content-disposition中的filename。
	 * @param is
	 *            下载的输入流。
	 * @throws WechatException
	 *             可以抛出的异常。
	 */
	public void handler(String contentType, String fileName, InputStream is) throws WechatException;

}
