package com.wechat.corp.bean;

/**
 * @Author: liulh
 * @Description: 微信异常信息
 * @Date: 2018/3/14 11:48
 * @Modified By:
 */
public class WechatException extends Exception {

	private static final long serialVersionUID = -9106285374166292416L;

	public WechatException() {
		super();
	}

	public WechatException(String message, Throwable cause) {
		super(message, cause);
	}

	public WechatException(String message) {
		super(message);
	}

	public WechatException(Throwable cause) {
		super(cause);
	}

}
