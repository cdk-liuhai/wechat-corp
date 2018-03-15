package com.wechat.corp.service.interfaces;

import org.w3c.dom.Element;

public interface InfoHandler {

	public String suiteTicket(Element root);

	public String changeAuth(Element root);

	public String cancelAuth(Element root);

}
